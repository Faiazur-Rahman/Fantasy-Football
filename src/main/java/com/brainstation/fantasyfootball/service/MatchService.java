package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.model.entity.Match;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface MatchService {
    void addMatch(Match match);
    ServiceResponse<?> deleteMatch(Long id);
    List<Match> getAllMatch();

    Optional<Match> getMatch(Long id);
    List<Match> getMatchById(Long id);
}
