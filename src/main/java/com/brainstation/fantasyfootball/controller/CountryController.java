package com.brainstation.fantasyfootball.controller;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.mapper.CountryMapper;
import com.brainstation.fantasyfootball.model.entity.Country;
import com.brainstation.fantasyfootball.model.entity.Round;
import com.brainstation.fantasyfootball.model.entity.Tournament;
import com.brainstation.fantasyfootball.service.AttributePassingService;
import com.brainstation.fantasyfootball.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/country")
public class CountryController {
    @Autowired
    private CountryService countryService;
    @Autowired
    private CountryMapper countryMapper;
    @Autowired
    private AttributePassingService attributePassingService;
    @GetMapping("/getCountry")
    public String getAllCountry(Model model, HttpServletRequest request){
        int page = 0;
        int size = 10;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }
        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        Page<Country> countries = countryService.getCountries(page, size);

//        List<Country> countries = countryService.getAllCountry();
        model.addAttribute("countries",countries);
        model.addAttribute("name",attributePassingService.getLoggedINUserAndImage(request).get(0));
        model.addAttribute("image",attributePassingService.getLoggedINUserAndImage(request).get(1));
        return "admin/country";
    }
    @PostMapping("/addCountry")
    public String addCountry(@RequestParam Map<String,String> request, RedirectAttributes attributes){
        Country country = countryMapper.toCountry(request);
        if(request.get("id")!=null){
            country.setId(Long.valueOf(request.get("id")));
            attributes.addFlashAttribute("message","Successfully Updated!");
        } else{
            attributes.addFlashAttribute("message","Successfully Added!");
        }
        countryService.addCountry(country);
        return "redirect:/country/getCountry";
    }
    @GetMapping("/deleteCountry/{id}")
    public String deleteCountry(@PathVariable Long id,RedirectAttributes redirectAttributes){
        ServiceResponse<?> response = countryService.deleteCountry(id);
        if(response.isHasError()){
            redirectAttributes.addFlashAttribute("message",response.getErrorMsg());
        } else {
            redirectAttributes.addFlashAttribute("message",response.getSuccessMsg());
        }
        return "redirect:/country/getCountry";
    }
    @GetMapping("/updateCountry/{id}")
    public String updateCountry(@PathVariable Long id,RedirectAttributes redirectAttributes,Model model, HttpServletRequest request){
        Optional<Country> country = countryService.getCountryById(id);
        if(country.isPresent()){
            model.addAttribute("country",country.get());
        }
        if(!attributePassingService.getLoggedINUserAndImage(request).get(0).isEmpty())
           model.addAttribute("name",attributePassingService.getLoggedINUserAndImage(request).get(0));
        if(!attributePassingService.getLoggedINUserAndImage(request).get(1).isEmpty())
            model.addAttribute("image",attributePassingService.getLoggedINUserAndImage(request).get(1));
        return "admin/updateCountry";
    }
}
