package com.brainstation.fantasyfootball.repository;

import com.brainstation.fantasyfootball.model.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITournamentRepository extends JpaRepository<Tournament, Long> {

}
