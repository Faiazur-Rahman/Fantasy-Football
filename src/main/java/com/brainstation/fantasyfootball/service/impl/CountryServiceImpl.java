package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.model.entity.Country;
import com.brainstation.fantasyfootball.repository.CountryRepository;
import com.brainstation.fantasyfootball.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ServiceResponse<?> serviceResponse;
    @Override
    public List<Country> getAllCountry() {
        return countryRepository.findAll();
    }

    @Override
    public void addCountry(Country country) {
         countryRepository.save(country);
    }

    @Override
    public ServiceResponse<?> deleteCountry(Long id) {
        boolean exists = countryRepository.existsById(id);
        if(exists){
            serviceResponse.setSuccessMsg("Successfully Deleted!!");
            countryRepository.deleteById(id);
        } else {
            serviceResponse.setHasError(true);
            serviceResponse.setErrorMsg("Does not find This country!!");
        }
        return serviceResponse;
    }

    @Override
    public Page<Country> getCountries(int page, int size) {
        Page<Country> countries = countryRepository.findAll(PageRequest.of(page, size));
        return countries;
    }
    @Override
    public Optional<Country> getCountryById(Long id) {
        Optional<Country> country = countryRepository.findById(id);
        return country;
    }
}
