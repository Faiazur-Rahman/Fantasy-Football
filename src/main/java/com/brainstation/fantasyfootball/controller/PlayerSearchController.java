package com.brainstation.fantasyfootball.controller;/*
 * @created 26/10/2022 -3:30 PM
 * @author  Anupam Das
 *
 */


import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerSearchController {

    @Autowired
    private PlayerRepository playerRepository;

    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query){

        List<Player> playerList = this.playerRepository.findPlayersByFirstnameContaining(query);

        return ResponseEntity.ok(playerList);


    }

}
