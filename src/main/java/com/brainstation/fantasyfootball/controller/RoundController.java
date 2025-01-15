package com.brainstation.fantasyfootball.controller;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.mapper.RoundMapper;
import com.brainstation.fantasyfootball.model.entity.Round;
import com.brainstation.fantasyfootball.model.entity.Tournament;
import com.brainstation.fantasyfootball.repository.RoundRepository;
import com.brainstation.fantasyfootball.service.AttributePassingService;
import com.brainstation.fantasyfootball.service.RoundService;
import com.brainstation.fantasyfootball.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/round")
public class RoundController {
    @Autowired
    private RoundService roundService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private RoundMapper roundMapper;

    @Autowired
    private RoundRepository roundRepository;

    @Autowired
    private AttributePassingService attributePassingService;
    @GetMapping("/allRounds")
    public String roundPage(HttpServletRequest request, Model model){
        int page = 0;
        int size = 10;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }
        List<Tournament> tournaments = tournamentService.getAllTournament();
        model.addAttribute("tournaments", tournaments);
        Page<Round> rounds = roundService.getRounds(page, size);
        model.addAttribute("rounds",rounds);
        if(!attributePassingService.getLoggedINUserAndImage(request).get(0).isEmpty())
            model.addAttribute("name",attributePassingService.getLoggedINUserAndImage(request).get(0));
        if(!attributePassingService.getLoggedINUserAndImage(request).get(1).isEmpty())
            model.addAttribute("image",attributePassingService.getLoggedINUserAndImage(request).get(1));

        return "admin/round";
    }
    @PostMapping("/saveRound")
    public String addRound(@RequestParam Map<String,String> requestParams , RedirectAttributes redirectAttributes){
        Round round = roundMapper.toRound(requestParams);
        roundService.addRound(round);
        redirectAttributes.addFlashAttribute("message","Round Save Successfully!!");
        return "redirect:/round/allRounds";
    }
    @GetMapping("/deleteRound/{id}")
    public String deleteRound(@PathVariable Long id, RedirectAttributes redirectAttributes){

        ServiceResponse<?> response = roundService.deleteRound(id);
        if(response.isHasError()){
            redirectAttributes.addFlashAttribute("message",response.getErrorMsg());
        }
        else{
            redirectAttributes.addFlashAttribute("message",response.getSuccessMsg());
        }

        return "redirect:/round/allRounds";
    }

}
