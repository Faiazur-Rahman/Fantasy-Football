package com.brainstation.fantasyfootball.repository;

import com.brainstation.fantasyfootball.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ITeamRepository extends JpaRepository<Team,Long> {
    Team findTeamByEventIdAndUserId(Long eventId, Long userId);
    boolean existsTeamByEventIdAndUserId(Long eventId,Long userId);
}
