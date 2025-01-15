package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.model.entity.Event;
import com.brainstation.fantasyfootball.model.entity.Tournament;
import com.brainstation.fantasyfootball.repository.EventRepository;
import com.brainstation.fantasyfootball.repository.ITournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {
    @Autowired
    ITournamentRepository tournamentRepository;
    @Autowired
    EventRepository eventRepository;

    public void createTournament(Tournament tournament){
        tournamentRepository.save(tournament);
    }
    public List<Event> getAllEventsOfATournament(Long tournamentId){
        return eventRepository.findEventsByTournamentId(tournamentId);
    }

    public List<Tournament> getAllTournament(){
        return tournamentRepository.findAll();
    }
}
