package com.brainstation.fantasyfootball.controller;

import com.brainstation.fantasyfootball.model.entity.EventUserPoint;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.model.entity.Team;
import com.brainstation.fantasyfootball.repository.IEventUserPointRepository;
import com.brainstation.fantasyfootball.service.EventService;
import com.brainstation.fantasyfootball.service.AttributePassingService;
import com.brainstation.fantasyfootball.service.IPlayerMatchPointService;
import com.brainstation.fantasyfootball.service.PlayerService;
import com.brainstation.fantasyfootball.service.impl.PlayerMatchPointServiceImpl;
import com.brainstation.fantasyfootball.service.impl.PlayerServiceImpl;
import com.brainstation.fantasyfootball.service.impl.TeamServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/points")
public class PlayerMatchPointController {

    private final Map<Long, Integer> userList = new HashMap<>();

    @Qualifier("playerMatchPointServiceImpl")
    @Autowired
    private IPlayerMatchPointService playerMatchPointService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private AttributePassingService attributePassingService;

    @RequestMapping("/updatePoint/{id}")
    public ModelAndView updatePoint(@PathVariable(name = "id")long id , Model model, HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("admin/updatePoint");
        model.addAttribute("name",attributePassingService.getLoggedINUserAndImage(request).get(0));
        model.addAttribute("image",attributePassingService.getLoggedINUserAndImage(request).get(1));
        modelAndView.addObject("playerId", id);
        return modelAndView;
    }

    @PostMapping("/calculate")
    public String calculatePoints(@RequestParam Map<String,String> request){
        Long playerId = Long.valueOf(request.get("playerId"));
        Integer goalScored = Integer.valueOf(request.get("goalScored"));
        Integer assist = Integer.valueOf(request.get("assist"));
        Integer matchPlayed = Integer.valueOf(request.get("matchPlayed"));
        Integer cleanSheet = Integer.valueOf(request.get("cleanSheet"));
        Integer yellowCard = Integer.valueOf(request.get("yellowCard"));
        Integer redCard = Integer.valueOf(request.get("redCard"));
        Integer manOfTheMatch = Integer.valueOf(request.get("manOfTheMatch"));

        Integer point = playerMatchPointService.calculatePlayerPoints(playerId, goalScored, assist, matchPlayed, cleanSheet, yellowCard, redCard, manOfTheMatch);
        userList.put(playerId,point);

        playerService.savePoint(playerId);

       return "redirect:/getallplayer";
    }

    @RequestMapping("/show")
    public Integer getPoints(){
        return playerMatchPointService.getPoint(1L);
    }

    @GetMapping("/update")
    public String updatePoint(RedirectAttributes redirectAttributes){
        playerMatchPointService.updatePoint(userList);
        userList.clear();
        redirectAttributes.addFlashAttribute("message", "Successfully Updated Player Points!");
        return "redirect:/getallplayer";
    }
    @GetMapping("/reset")
    public String resetPoint(RedirectAttributes redirectAttributes){
        playerService.updatePlayerPoints();
        redirectAttributes.addFlashAttribute("message", "Successfully Reset Player Points!");
        return "redirect:/getallplayer";
    }
}
