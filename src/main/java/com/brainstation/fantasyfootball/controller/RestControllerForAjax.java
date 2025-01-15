package com.brainstation.fantasyfootball.controller;

import com.brainstation.fantasyfootball.common.PlayerPositionType;
import com.brainstation.fantasyfootball.model.dto.CreateTeamDTO;
import com.brainstation.fantasyfootball.model.dto.PlayerIdAndPosition;
import com.brainstation.fantasyfootball.model.dto.PlayerPosition;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.repository.PlayerRepository;
import com.brainstation.fantasyfootball.service.ITeamService;
import com.brainstation.fantasyfootball.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RestControllerForAjax {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private PlayerRepository playerRepository;

    @Qualifier("teamServiceImpl")
    @Autowired
    private ITeamService iTeamService;

    List<Pair<String,Long>> players = new ArrayList<>();

    @PostMapping("/event/{eventId}/getPositionWisePlayer")
    public ResponseEntity<Object> getPositionWisePlayer(@RequestBody PlayerPosition playerPosition){

        if (playerPosition.getPosition().contains("defender")){
            List<Player> defenders = new ArrayList<>();
            playerService.getPlayerByCountry(PlayerPositionType.DF)
                    .forEach(
                            player -> {
                                List<String> playerPositionList = playerPosition.getPlayerList();
                                if (checkIfThePlayerAlreadyExists(playerPositionList,player))
                                    defenders.add(player);
                            }
                    );
            return new ResponseEntity<>(defenders, HttpStatus.OK);
        } else if (playerPosition.getPosition().contains("goal")) {
            List<Player> goalKeepers = new ArrayList<>();
            playerService.getPlayerByCountry(PlayerPositionType.GK)
                    .forEach(
                            player -> {
                                List<String> playerPositionList = playerPosition.getPlayerList();
                                if (checkIfThePlayerAlreadyExists(playerPositionList,player))
                                    goalKeepers.add(player);
                            }
                    );
            return new ResponseEntity<>(goalKeepers, HttpStatus.OK);
        } else if (playerPosition.getPosition().contains("mid")) {
            List<Player> midFielders = new ArrayList<>();
            playerService.getPlayerByCountry(PlayerPositionType.MF)
                    .forEach(
                            player -> {
                                List<String> playerPositionList = playerPosition.getPlayerList();
                                if (checkIfThePlayerAlreadyExists(playerPositionList,player))
                                    midFielders.add(player);
                            }
                    );
            return new ResponseEntity<>(midFielders, HttpStatus.OK);
        }
        List<Player> forwards = new ArrayList<>();
        playerService.getPlayerByCountry(PlayerPositionType.FW)
                .forEach(
                        player -> {
                            List<String> playerPositionList = playerPosition.getPlayerList();
                            if (checkIfThePlayerAlreadyExists(playerPositionList,player))
                                forwards.add(player);
                        }
                );
        return new ResponseEntity<>(forwards, HttpStatus.OK);
    }

    private boolean checkIfThePlayerAlreadyExists(List<String> playerPositionList,Player player){
        boolean ok = true;
        for (int i=0;i<playerPositionList.size();i++){
            Long id=Long.parseLong(playerPositionList.get(i));
            if (Objects.equals(player.getId(), id))
                ok = false;
        }
        return ok;
    }

    @PostMapping("/getPlayer")
    public ResponseEntity<Object> getPlayer(@RequestBody PlayerIdAndPosition playerId){
        Long id = Long.parseLong(playerId.getId());
        return new ResponseEntity<>(playerRepository.findById(id).get(),HttpStatus.OK);
    }
    @PostMapping("/team/createTeam/{eventId}")
    public void createTeam(HttpServletRequest request, @RequestBody CreateTeamDTO createTeamDTO, @PathVariable(value = "eventId") Long eventId){
        Principal user = request.getUserPrincipal();
        List<Long> playerXI = createTeamDTO.getSelectedPlayerId()
                .stream()
                .map(Long::parseLong)
                .collect(Collectors.toList());
        iTeamService.teamFormation(playerXI,eventId,user,createTeamDTO);
    }
}
