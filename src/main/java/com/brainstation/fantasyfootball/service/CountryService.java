package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.model.entity.Country;
import com.brainstation.fantasyfootball.model.entity.Round;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CountryService {
    List<Country> getAllCountry();
    void addCountry(Country country);
    ServiceResponse<?> deleteCountry(Long id);


    Optional<Country> getCountryById(Long id);

    Page<Country> getCountries(int page, int size);
}
