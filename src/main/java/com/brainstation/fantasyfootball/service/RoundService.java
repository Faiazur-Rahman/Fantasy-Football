package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.common.ServiceResponse;
import com.brainstation.fantasyfootball.model.entity.Round;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface RoundService {
    void addRound(Round round);
    ServiceResponse<?> deleteRound(Long id);
    List<Round> getAllRound();
    Page<Round> getRounds(int page, int size);
    Optional<Round> getRoundsByDate(String date);

}
