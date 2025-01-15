package com.brainstation.fantasyfootball.controller;


import com.brainstation.fantasyfootball.model.entity.Country;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.model.entity.User;
import com.brainstation.fantasyfootball.model.entity.UserImage;
import com.brainstation.fantasyfootball.repository.CountryRepository;
import com.brainstation.fantasyfootball.repository.EventRepository;
import com.brainstation.fantasyfootball.repository.PlayerRepository;
import com.brainstation.fantasyfootball.repository.UserRepository;
import com.brainstation.fantasyfootball.service.AttributePassingService;
import com.brainstation.fantasyfootball.service.UserImageService;
import com.brainstation.fantasyfootball.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserImageService userImageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CountryRepository countryRepository;


    @Autowired
    private AttributePassingService attributePassingService;

    @GetMapping("/admin_page")
    public String adminHomePage(Model model,HttpServletRequest request, RedirectAttributes redirectAttributes){
        Principal principal = request.getUserPrincipal();
        String name = principal.getName();
        Optional<UserImage> userImage = userImageService.getImage(name);
        int totalPlayer = playerRepository.findAll().size();
        int totalUser = userRepository.findAll().size();
        int totalEvent = eventRepository.findAll().size();
        int totalCountry= countryRepository.findAll().size();


        if(userImage.isPresent()){
            byte[] dataImage = Base64.getEncoder().encode(userImage.get().getData());
            String baseEncodedData = null;
            try {
                baseEncodedData = new String(dataImage,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
            model.addAttribute("image",baseEncodedData);
//            System.out.println(baseEncodedData);
        }
        model.addAttribute("name",name);
        model.addAttribute("totalPlayer",totalPlayer);
        model.addAttribute("totalUser",totalUser);
        model.addAttribute("totalEvent",totalEvent);
        model.addAttribute("totalCountry",totalCountry);
        return "admin/adminHome";
    }
    @GetMapping("/showAllUser")
    public ModelAndView showAllUser(Model model, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("admin/show_user_list");
        int page = 0;
        int size = 10;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        Page<User> users = userService.getUsers(page, size);

        mav.addObject("users",users);
        if(!attributePassingService.getLoggedINUserAndImage(request).get(0).isEmpty())
            model.addAttribute("name",attributePassingService.getLoggedINUserAndImage(request).get(0));
        if(!attributePassingService.getLoggedINUserAndImage(request).get(1).isEmpty())
            model.addAttribute("image",attributePassingService.getLoggedINUserAndImage(request).get(1));
        return mav;
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long userId) {
        if(userRepository.existsById(userId)) {
            userService.deleteById(userId);
        }
        return "redirect:/admin/showAllUser";
    }

}
