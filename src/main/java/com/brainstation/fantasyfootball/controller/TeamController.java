package com.brainstation.fantasyfootball.controller;/*
 * @created 25/10/2022 -TeamController
 * @author  Anupam Das
 *
 */

import com.brainstation.fantasyfootball.model.dto.CreateTeamDTO;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.model.entity.User;
import com.brainstation.fantasyfootball.repository.PlayerRepository;
import com.brainstation.fantasyfootball.repository.UserRepository;
import com.brainstation.fantasyfootball.service.ITeamService;
import com.brainstation.fantasyfootball.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/team")
public class TeamController {




    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerRepository playerRepository;


    @Qualifier("teamServiceImpl")
    @Autowired
    private ITeamService teamService;

    @GetMapping("/")
    private String team(){
        return "team/player_search";
    }

    @GetMapping("/myTeam")
    public List<Player> showTeamPlayer(){
//        TODO: Service
        return null;
    }



    @GetMapping("/createTeam/{eventId}")
    public String createTeamPage(@PathVariable(value = "eventId") Long eventId, Model model){
        model.addAttribute("eventId",eventId);
        model.addAttribute("players",playerService.getAllPlayer());
        return "team/player_search";
    }

    @GetMapping("/updateTeam/{eventId}")
    public String updateTeam(@PathVariable(value = "eventId") Long eventId, HttpServletRequest request) {
        Principal loggedInUser = request.getUserPrincipal();
        teamService.updateTeam(eventId,loggedInUser);
        return "redirect:/event/"+eventId;
    }

    List<Player> players = new ArrayList<>();
    @GetMapping("/{eventId}/addPlayer/{playerId}/{position}")
    public String addPlayer(HttpServletRequest request,@PathVariable(value = "eventId") Long eventId, @PathVariable(value = "playerId") Long playerId, @PathVariable("position") String position, Model model){
        Player player = playerRepository.findById(playerId).orElse(new Player());
//        Principal user = request.getUserPrincipal();
//        User user1 = userRepository.findByUsername(user.getName());
//        TempTableForNotSavedTeam team = notSavedTeamRepository.findByEventIdAndUserId(eventId,user1.getId())
//                                        .orElse(new TempTableForNotSavedTeam());
//        if (position.contains("goal")){
//            team.setP1(playerId);
//        } else if (position=="defender1") {
//            team.setP2(playerId);
//        } else if (position=="defender2") {
//            team.setP3(playerId);
//        } else if (position=="defender3") {
//            team.setP4(playerId);
//        }else if (position=="defender4") {
//            team.setP5(playerId);
//        }else if (position=="defender5") {
//            team.setP6(playerId);
//        } else if (position=="mid1") {
//            team.setP7(playerId);
//        } else if (position=="mid2") {
//            team.setP8(playerId);
//        } else if (position=="mid3") {
//            team.setP9(playerId);
//        } else if (position=="mid4") {
//            team.setP10(playerId);
//        } else if (position=="mid5") {
//            team.setP11(playerId);
//        }
//        team.setEventId(eventId);
//        team.setUserId(user1.getId());
//        notSavedTeamRepository.save(team);
        Map<String, Player> playerMapPosition = new HashMap<>();
        playerMapPosition.put(position,player);
        model.addAttribute("playerMapPosition",playerMapPosition);
        return "redirect:/event/"+eventId;
    }

}
