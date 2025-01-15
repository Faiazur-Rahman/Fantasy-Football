package com.brainstation.fantasyfootball.repository;

import com.brainstation.fantasyfootball.model.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MatchRepository extends JpaRepository<Match, Long> {
    List<Match> findMatchByRoundId(Long id);

}
