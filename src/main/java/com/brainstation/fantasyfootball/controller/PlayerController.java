package com.brainstation.fantasyfootball.controller;

import com.brainstation.fantasyfootball.model.dto.PlayerResponse;
import com.brainstation.fantasyfootball.model.entity.Country;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.repository.PlayerRepository;
import com.brainstation.fantasyfootball.service.AttributePassingService;
import com.brainstation.fantasyfootball.service.CountryService;
import com.brainstation.fantasyfootball.service.impl.PlayerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

/*
 * author: Sadik Hassan
 * created: 11:17 am, 20/10/2022
 */
@Controller
public class PlayerController {
    @Autowired
    private PlayerServiceImpl playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private AttributePassingService attributePassingService;

    @GetMapping("/players")
    public String getAllPage(Model model) {
        return getAllPlayer(model, 1, "nickname", "asc");
    }

    @GetMapping("/players/{pageNumber}")
    public String getAllPlayer(Model model, @PathVariable("pageNumber") int currentPage,
                               @Param("sortField") String sortField, @Param("sortDir") String sortDir) {
        Page<Player> page = playerService.findPage(currentPage, sortField, sortDir);
        int totalPage = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Player> players = page.getContent();
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("player_view", players);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        String reverse = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverse", reverse);
        return "user/player_view";
    }

    @GetMapping("/getallplayer")
    public String getAllPages(Model model, HttpServletRequest request) {
        if(!attributePassingService.getLoggedINUserAndImage(request).get(0).isEmpty())
            model.addAttribute("name",attributePassingService.getLoggedINUserAndImage(request).get(0));
        if(!attributePassingService.getLoggedINUserAndImage(request).get(1).isEmpty())
            model.addAttribute("image",attributePassingService.getLoggedINUserAndImage(request).get(1));

        return getOnePage(model, 1, "nickname", "asc",request);
    }

    @GetMapping("/getallplayer/{pageNumber}")
    public String getOnePage(Model model, @PathVariable("pageNumber") int currentPage,
                             @Param("sortField") String sortField, @Param("sortDir") String sortDir,HttpServletRequest request) {
        Page<Player> page = playerService.findPage(currentPage, sortField, sortDir);

        int totalPage = page.getTotalPages();
        long totalItems = page.getTotalElements();
        List<Player> players = page.getContent();

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalPage", totalPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("player_2", players);
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        String reverse = sortDir.equals("asc") ? "desc" : "asc";
        model.addAttribute("reverse", reverse);
        model.addAttribute("name",attributePassingService.getLoggedINUserAndImage(request).get(0));
        model.addAttribute("image",attributePassingService.getLoggedINUserAndImage(request).get(1));
        return "admin/player_2";
    }

    @GetMapping("/players/search")
    public String search(Model model, String keyword) {
        List<Player> players;
        players = keyword == null ? playerService.getAllPlayer() : playerService.findPlayer(keyword);
        model.addAttribute("players", players);
        return "admin/players";
    }

    @GetMapping("/players/Usersearch")
    public String searching(Model model, String keyword) {
        List<Player> players;
        players = keyword == null ? playerService.getAllPlayer() : playerService.findPlayer(keyword);
        model.addAttribute("players", players);
        return "user/search_view";
    }

    @GetMapping("/add_player")
    public String add(Model model, HttpServletRequest request) {
        Player player = new Player();
        List<Country> countries = countryService.getAllCountry();
        model.addAttribute("player", player);
        model.addAttribute("countires", countries);
        if(!attributePassingService.getLoggedINUserAndImage(request).get(0).isEmpty())
            model.addAttribute("name",attributePassingService.getLoggedINUserAndImage(request).get(0));
        if(!attributePassingService.getLoggedINUserAndImage(request).get(1).isEmpty())
            model.addAttribute("image",attributePassingService.getLoggedINUserAndImage(request).get(1));
        return "admin/add_player";
    }

    @RequestMapping(value = "/getallplayer", method = RequestMethod.POST)
    public String savePlayer(@ModelAttribute("player") Player player, RedirectAttributes redirectAttributes) {
        Player player1 = playerRepository.findByNickname(player.getNickname());
        if (player.getId() != null) {
            playerService.save(player);
            redirectAttributes.addFlashAttribute("green_message", "Player : " + player.getNickname() + " Updated Successfully ");
            return "redirect:/getallplayer";
        } else if (!ObjectUtils.isEmpty(player1) && player1.getCountry().equals(player.getCountry())) {
            redirectAttributes.addFlashAttribute("red_message", "Player : " + player.getNickname() + " Already Exists !");
            return "redirect:/getallplayer";
        } else {
            playerService.save(player);
            redirectAttributes.addFlashAttribute("green_message", "Player : " + player.getNickname() + " Added Successfully ");
            return "redirect:/getallplayer";
        }
    }

    @RequestMapping("/getallplayer/edit/{id}")
    public ModelAndView edit(@PathVariable(name = "id") long id, Model model, HttpServletRequest request,RedirectAttributes redirectAttributes) {
        boolean playerExits=playerRepository.existsById(id);
        if(!playerExits){
            redirectAttributes.addFlashAttribute("red_message","Sorry Player not exist in the database");
            ModelAndView modelAndView=new ModelAndView("redirect:/getallplayer");
            return modelAndView;
        }
        ModelAndView modelAndView = new ModelAndView("admin/player_edit");
        List<Country> countries = countryService.getAllCountry();
        Player player = playerService.getPlayerDetailsById(id);
        modelAndView.addObject("players", player);
        modelAndView.addObject("countires", countries);
        if(!attributePassingService.getLoggedINUserAndImage(request).get(0).isEmpty())
            model.addAttribute("name",attributePassingService.getLoggedINUserAndImage(request).get(0));
        if(!attributePassingService.getLoggedINUserAndImage(request).get(1).isEmpty())
            model.addAttribute("image",attributePassingService.getLoggedINUserAndImage(request).get(1));

        return modelAndView;
    }

    @RequestMapping("/getallplayer/delete/{id}")
    public String deletePlayer(@PathVariable(name = "id") int id, RedirectAttributes redirectAttributes) {
        boolean playerExists = playerRepository.existsById((long) id);
        if (playerExists) {
            redirectAttributes.addFlashAttribute("red_message", playerRepository.findById((long) id).get().getNickname() + " deleted from database !");
            playerService.delete(id);
            return "redirect:/getallplayer";
        } else {
            redirectAttributes.addFlashAttribute("red_message", " Player not found in the database ");
            return "redirect:/getallplayer";
        }

    }
}


