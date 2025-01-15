package com.brainstation.fantasyfootball.controller;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.mapper.MatchMapper;
import com.brainstation.fantasyfootball.model.entity.Country;
import com.brainstation.fantasyfootball.model.entity.Match;
import com.brainstation.fantasyfootball.service.AttributePassingService;
import com.brainstation.fantasyfootball.service.CountryService;
import com.brainstation.fantasyfootball.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/match")
public class MatchController {
    @Autowired
    private MatchService matchService;
    @Autowired
    private MatchMapper matchMapper;
    @Autowired
    private CountryService countryService;


    @Autowired
    private AttributePassingService attributePassingService;



    @GetMapping("/matchForm/{id}")
    public String matchForm(@PathVariable Long id, Model model, HttpServletRequest request){
        model.addAttribute("round_key", id);
        List<Match> matchById = matchService.getMatchById(id);
        model.addAttribute("allMatch",matchById);
        List<Country> countries = countryService.getAllCountry();
        model.addAttribute("countries",countries);
        model.addAttribute("name",attributePassingService.getLoggedINUserAndImage(request).get(0));
        model.addAttribute("image",attributePassingService.getLoggedINUserAndImage(request).get(1));
        return "admin/match";
    }
    @PostMapping("/saveMatch")
    public String saveMatch(@RequestParam Map<String,String> requestParams , RedirectAttributes redirectAttributes){
        Match match = matchMapper.toMatch(requestParams);
        matchService.addMatch(match);
        redirectAttributes.addFlashAttribute("message","Match Added successfully!!");
        Long id = match.getRoundId();
        return "redirect:/match/matchForm/"+id;
    }
    @GetMapping("/deleteMatch/{id}")
    public String deleteMatch(@PathVariable Long id,  RedirectAttributes redirectAttributes){
        Optional<Match> match = matchService.getMatch(id);
        Long roundId = match.get().getRoundId();
        ServiceResponse<?> response = matchService.deleteMatch(id);
        redirectAttributes.addFlashAttribute("message",response.getSuccessMsg());
        return "redirect:/match/matchForm/"+roundId;
    }
    @GetMapping("/updateMatch/{id}")
    public String updateMatch(@PathVariable Long id, Model model){
        Optional<Match> match = matchService.getMatch(id);
        if(match.isPresent()){
            model.addAttribute("match",match);
        }else {
            return "redirect:/match/matchForm/"+id;
        }
        return "admin/update_match";
    }
    @PostMapping("/saveUpdateMatch")
    public String saveUpdateMatch(@ModelAttribute("match") Match match, RedirectAttributes redirectAttributes){
        matchService.addMatch(match);
        redirectAttributes.addFlashAttribute("message","Successfully Updated!");
        Long roundId = match.getRoundId();
        return "redirect:/match/matchForm/"+roundId;
    }

}
