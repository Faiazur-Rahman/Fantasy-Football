package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.model.entity.Match;
import com.brainstation.fantasyfootball.repository.MatchRepository;
import com.brainstation.fantasyfootball.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatchServiceImpl implements MatchService {
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private ServiceResponse<?> serviceResponse;
    @Override
    public void addMatch(Match match) {
        matchRepository.save(match);
    }
    @Override
    public ServiceResponse<?> deleteMatch(Long id) {
        try {
            matchRepository.deleteById(id);
            serviceResponse.setSuccessMsg("Successfully deleted!!");
        } catch (Exception e){
            e.printStackTrace();
            serviceResponse.setErrorMsg("Id not found");
            serviceResponse.setHasError(true);
        }
        return serviceResponse;
    }
    @Override
    public List<Match> getAllMatch() {
        return matchRepository.findAll();
    }

    @Override
    public Optional<Match> getMatch(Long id) {
        return matchRepository.findById(id);
    }

    @Override
    public List<Match> getMatchById(Long id) {
        return matchRepository.findMatchByRoundId(id);
    }

}
