package com.brainstation.fantasyfootball.repository;

import com.brainstation.fantasyfootball.model.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    Event findEventByEventCode(String code);
    List<Event> findEventsByTournamentId(Long id);
    boolean existsEventByEventCode(String eventCode);

    List<Event> findEventsByEventNameContaining(String name);

}
