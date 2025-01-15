package com.brainstation.fantasyfootball.service.impl;


import com.brainstation.fantasyfootball.common.PlayerPositionType;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.model.entity.Team;
import com.brainstation.fantasyfootball.repository.ITeamRepository;
import com.brainstation.fantasyfootball.repository.PlayerRepository;
import com.brainstation.fantasyfootball.service.ITeamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class TeamServiceImplTest {
    @Autowired
    private ITeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

//    @Qualifier("teamServiceImpl")
//    @Autowired
//    private ITeamService teamService;
//
//    @Test
//    public void calculatePoints(){
//        Boolean isTeamExists = teamService.isTeamExists(21L, 1L);
//        if (isTeamExists) {
//            List<Player> teamPlayers = teamService.getUserTeam(21L, 1L);
//            Team team = teamService.getTeam(21L, 1L);
//            Integer teamPoint = teamService.calculatePoints(team, teamPlayers);
//        }
//    }



}