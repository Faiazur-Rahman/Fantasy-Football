package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.model.entity.Round;
import com.brainstation.fantasyfootball.repository.RoundRepository;
import com.brainstation.fantasyfootball.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoundServiceImpl implements RoundService {
    @Autowired
    private RoundRepository roundRepository;
    @Autowired
    private ServiceResponse<?> serviceResponse;
    @Override
    public void addRound(Round round) {
        roundRepository.save(round);
    }
    @Override
    public ServiceResponse<?> deleteRound(Long id) {
        boolean roundExists=roundRepository.existsById(id);
        if(roundExists) {
            roundRepository.deleteById(id);
            serviceResponse.setSuccessMsg("Successfully Deleted!");
        }
        else{
            serviceResponse.setHasError(true);
            serviceResponse.setErrorMsg("Round Id not Found!");
        }
        return serviceResponse;
    }
    @Override
    public List<Round> getAllRound() {
        return roundRepository.getAllRound();
    }

    @Override
    public Page<Round> getRounds(int page, int size) {
        Page<Round> rounds = roundRepository.findAll(PageRequest.of(page, size));
        return rounds;
    }

    @Override
    public Optional<Round> getRoundsByDate(String date) {
        return Optional.ofNullable(roundRepository.getRoundByRoundDate(date));
    }
}
