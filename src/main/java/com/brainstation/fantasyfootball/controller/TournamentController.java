package com.brainstation.fantasyfootball.controller;


import com.brainstation.fantasyfootball.model.entity.Event;
import com.brainstation.fantasyfootball.model.entity.Tournament;
import com.brainstation.fantasyfootball.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @PostMapping("/create-tournament")
    public void createTournament(@RequestBody Tournament tournament){
        tournamentService.createTournament(tournament);
    }

    @GetMapping("/all-event/{tournamentId}")
    public List<Event> getAllEventsOfATournament(@PathVariable(value = "tournamentId") Long tournamentId){
        return tournamentService.getAllEventsOfATournament(tournamentId);
    }

}
