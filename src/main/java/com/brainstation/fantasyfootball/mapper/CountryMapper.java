package com.brainstation.fantasyfootball.mapper;

import com.brainstation.fantasyfootball.model.entity.Country;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class CountryMapper {
    public Country toCountry(Map<String,String> request){
        Country country = new Country();
        country.setShortName(request.get("shortName"));
        country.setFullName(request.get("fullName"));
        return country;
    }
}
