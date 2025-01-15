package com.brainstation.fantasyfootball.controller;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.model.dto.EventDTO;
import com.brainstation.fantasyfootball.model.dto.JoinAnEventDTO;
import com.brainstation.fantasyfootball.model.entity.*;
import com.brainstation.fantasyfootball.repository.*;
import com.brainstation.fantasyfootball.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

/*
 * author: Sadik Hassan
 * created: 11:17 am, 20/10/2022
 */

@Controller
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private EventService eventService;
    @Qualifier("teamServiceImpl")
    @Autowired
    private ITeamService iTeamService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private RoundService roundService;
    @Autowired
    private PlayerService playerService;

    @Autowired
    private UserRepository userRepository;
    private final  Map<String,List<Event>> eventsMap= new HashMap<>();

    @GetMapping("/")
    public String eventPage(Model model, HttpServletRequest request,RedirectAttributes redirectAttributes){
        List<Event> eventList = eventService.getAllEvents();
        if(eventsMap.isEmpty()){
            model.addAttribute("events",eventList);
            model.addAttribute("eventDetails", eventService.getEventDetails(eventList));
        } else {
            model.addAttribute("events",eventsMap.get("events"));
            model.addAttribute("eventDetails", eventService.getEventDetails(eventsMap.get("events")));
            eventsMap.clear();
        }

        Principal user = request.getUserPrincipal();
        model.addAttribute("joined",eventService.getJoinedInfo(user));

        model.addAttribute("eventDTO", new EventDTO());
        return "user/event/event-page";
    }

    @PostMapping("/create-event")
    public String createEvent(@ModelAttribute EventDTO event, Model model, HttpServletRequest request,RedirectAttributes redirectAttributes){
        Principal user = request.getUserPrincipal();
        ServiceResponse<?> serviceResponse=eventService.createEvent(event,user);
        if(serviceResponse.isHasError()){
            redirectAttributes.addFlashAttribute("danger_message",serviceResponse.getErrorMsg());
            return "redirect:/event/";
        }
        else {
            redirectAttributes.addFlashAttribute("success_message",serviceResponse.getSuccessMsg());
            return "redirect:/event/";
        }


    }
    @PostMapping("/join-event")
    public String joinAnEvent(@RequestParam("eventCode") String eventCode, HttpServletRequest request, RedirectAttributes redirectAttributes){
        Principal user = request.getUserPrincipal();
        ServiceResponse<?> serviceResponse=eventService.joinAnEvent(eventCode,user);
        if(serviceResponse.isHasError() && !serviceResponse.isExist()){
            redirectAttributes.addFlashAttribute("danger_message",serviceResponse.getErrorMsg());
            return "redirect:/event/";
        }
        else{
            redirectAttributes.addFlashAttribute("success_message",serviceResponse.getSuccessMsg());
            Event event = eventRepository.findEventByEventCode(eventCode);
            return "redirect:/event/"+event.getId();
        }
    }
    @GetMapping("/{eventId}")
    public String enterInThisEvent(@PathVariable(value = "eventId") Long eventId,HttpServletRequest request, Model model,RedirectAttributes redirectAttributes){
        Principal loggedInUser = request.getUserPrincipal();
        ServiceResponse<?> serviceResponse=eventService.enterInThisEvent(eventId,loggedInUser);
        if(serviceResponse.isHasError() && !serviceResponse.isExist() && !serviceResponse.isAuthorization()){
            redirectAttributes.addFlashAttribute("danger_message",serviceResponse.getErrorMsg());
            return "redirect:/event/";
        }
        else if(serviceResponse.isHasError() && serviceResponse.isExist() && !serviceResponse.isAuthorization()){
            redirectAttributes.addFlashAttribute("danger_message",serviceResponse.getErrorMsg());
            return "redirect:/event/";
        }

        model.addAttribute("userList", eventService.userRankList(eventId));
        User user = userRepository.findByUsername(loggedInUser.getName());
        List<Player> teamPlayers = iTeamService.getUserTeam(eventId, loggedInUser);
        Boolean isTeamExists = iTeamService.isTeamExists(eventId, loggedInUser);
        model.addAttribute("teamPoint", 0);
        if (isTeamExists) {
            Team team = iTeamService.getTeam(eventId, loggedInUser);
            Integer teamPoint = iTeamService.calculatePoints(team, teamPlayers);
            model.addAttribute("teamPoint", teamPoint);
            model.addAttribute("formation",iTeamService.getFormation(team));
            model.addAttribute("teamExists",true);
        }
        else{
//            model.addAttribute("formation","4-3-3");
            model.addAttribute("teamExists",false);
        }
        String eventName = eventRepository.findById(eventId).get().getEventName();
        String eventCode = eventRepository.findById(eventId).get().getEventCode();
        model.addAttribute("eventName", eventName);
        model.addAttribute("eventCode", eventCode);
        model.addAttribute("userTeam", teamPlayers);
        model.addAttribute("event", eventRepository.findById(eventId).get());
        model.addAttribute("eventId",eventId);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date day = new Date();
        String dateTime = formatter.format(day);
        String date = dateTime.substring(0, 10);
        Optional<Round> round = roundService.getRoundsByDate(date);
        if (round.isPresent()) {
            model.addAttribute("round", round);
            List<Match> matches = matchService.getMatchById(round.get().getId());
            model.addAttribute("matches", matches);
        }
        List<EventUserPoint> ranking = eventService.getUserRanking(eventId);
        Optional<EventUserPoint> myPoint = eventService.getEventUserByEventIdUsername(eventId, loggedInUser.getName());
        if(myPoint.isPresent()){
            model.addAttribute("myPoint",myPoint.get().getPoint());
        } else {
            model.addAttribute("myPoint",0);
        }
        model.addAttribute("userBalance",eventService.getEventUserPoint(eventId,loggedInUser));
        model.addAttribute("ranking", ranking);
        model.addAttribute("myUsername",loggedInUser.getName());
        return "user/event/event-details";

    }

    @GetMapping("/{eventId}/leave")
    public String leaveThisEvent(@PathVariable(value = "eventId") Long eventId,HttpServletRequest request, Model model,RedirectAttributes redirectAttributes){
        Principal user = request.getUserPrincipal();
        ServiceResponse<?> serviceResponse=eventService.leaveThisEvent(eventId,user);
        if(!serviceResponse.isExist() && serviceResponse.isHasError()){
            redirectAttributes.addFlashAttribute("danger_message",serviceResponse.getErrorMsg());
        }
        else if(serviceResponse.isExist() && serviceResponse.isHasError() && !serviceResponse.isAuthorization()){
            redirectAttributes.addFlashAttribute("danger_message",serviceResponse.getErrorMsg());
        }
        else{
            redirectAttributes.addFlashAttribute("success_message",serviceResponse.getSuccessMsg());
        }
        model.addAttribute("leaveMessage",eventService.leaveThisEvent(eventId,user));
        return "redirect:/event/";
    }
    @PostMapping("/search")
    public String searchEvent(@RequestParam("eventName") String name,RedirectAttributes redirectAttributes){
        Optional<List<Event>> events = eventService.searchEvent(name);
        if(!events.get().isEmpty()){
            eventsMap.put("events",events.get());
            return "redirect:/event/";
        } else{
            redirectAttributes.addFlashAttribute("danger_message","No Event Found!");
            return "redirect:/event/";
        }
    }

//    @PostMapping("/{eventId}/getFormation")
//    public String getFormation(@RequestParam Map<String, String> formation, RedirectAttributes model,@PathVariable Long eventId){
//        String form = formation.get("formation");
//        model.addFlashAttribute("formation",form);
//        return "redirect:/event/"+eventId;
//    }

}
