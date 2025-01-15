package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.model.dto.CreateTeamDTO;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.model.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;
import java.security.Principal;
import java.util.Map;

@Service
public interface ITeamService {
    void teamFormation( List<Long> playerXI,Long eventId, Principal loggedInUser, CreateTeamDTO createTeamDTO);

    void saveTeamPoints(Long id);
    List<Player> getUserTeam(Long eventId, Principal loggedInUser);

    Team getTeam(Long eventId, Principal loggedInUser);
    Integer calculatePoints(Team team, List<Player> playerList);

    boolean isTeamExists(Long eventId, Principal loggedInUser);
    void updateTeam(Long eventId, Principal loggedInUser);
    String getFormation(Team team);
}
