package com.brainstation.fantasyfootball.controller;

import com.brainstation.fantasyfootball.model.dto.UserDto;
import com.brainstation.fantasyfootball.model.entity.*;
import com.brainstation.fantasyfootball.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserImageService userImageService;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private RoundService roundService;
    @Autowired
    private MatchService matchService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private EventService eventService;

    @GetMapping("/profilePage")
    public String profile(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        UserDto user = userService.getUserByUsername(username);
        Optional<UserImage> image = userImageService.getImage(username);
        if(image.isPresent()){
            byte[] data = Base64.getEncoder().encode(image.get().getData());
            String base64Encoded = new String(data, "UTF-8");
            model.addAttribute("image",base64Encoded);
        }
        model.addAttribute("user",user);
        Optional<List<EventUserPoint>> eventAndPoint = Optional.ofNullable(eventService.getUserEventAndPoint(username));
        if(eventAndPoint.isPresent()){
            List<String> eventName = new ArrayList<>();
            for(var event: eventAndPoint.get()){
                Optional<Event> eventById = eventService.getEventById(event.getEventId());
                if(eventById.isPresent())
                    event.setUsername(eventById.get().getEventName());
            }
            model.addAttribute("eventPoint",eventAndPoint.get());
        }
        return "common/profile";
    }
    @PostMapping("/updateFirstName")
    public String updateFirstName(@RequestParam("name") String name, HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        User user = userService.getUser(username);
        user.setFirstName(name);
        userService.addUser(user);
        return "redirect:/common/profilePage";
    }
    @PostMapping("/updateLastName")
    public String updateLastName(@RequestParam("name") String name,HttpServletRequest request ){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        User user = userService.getUser(username);
        user.setLastName(name);
        userService.addUser(user);
        return "redirect:/common/profilePage";
    }
    @PostMapping ("/updatePassword")
    public String updatePassword(@RequestParam Map<String,String> password, HttpServletRequest request, RedirectAttributes redirectAttributes){
        Principal principal = request.getUserPrincipal();
        String username = principal.getName();
        User user = userService.getUser(username);
        String oldPassword = password.get("oldpassword");
        boolean matches = passwordEncoder.matches(oldPassword, user.getPassword());
        if(!matches){
            redirectAttributes.addFlashAttribute("msg","Old Password is not correct!");
            return "redirect:/common/profilePage";
        }
        String currentPassword = password.get("password");
        user.setPassword(passwordEncoder.encode(currentPassword));
        userService.addUser(user);
        redirectAttributes.addFlashAttribute("msg","Password Changed Successfully!");
        return "redirect:/common/profilePage";
    }
    @GetMapping("/round_match")
    public String roundMatch(Model model){
        List<Tournament> allTournament = tournamentService.getAllTournament();
        List<Round> allRound = roundService.getAllRound();
        List<Match> allMatch = matchService.getAllMatch();
        model.addAttribute("tournaments",allTournament);
        model.addAttribute("rounds",allRound);
        model.addAttribute("matches",allMatch);
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String dateTime = formatter.format(date);
        String day = dateTime.substring(0,10);
        model.addAttribute("date",day);

        return "user/RoundMatch";
    }
    @GetMapping("/match")
    public String matchPage(){
        return "user/match/matches";
    }
}
