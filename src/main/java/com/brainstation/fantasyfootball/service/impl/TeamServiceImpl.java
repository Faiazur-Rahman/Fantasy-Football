package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.common.PlayerPositionType;
import com.brainstation.fantasyfootball.model.dto.CreateTeamDTO;
import com.brainstation.fantasyfootball.model.entity.EventUserPoint;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.model.entity.Round;
import com.brainstation.fantasyfootball.model.entity.Team;
import com.brainstation.fantasyfootball.model.entity.User;
import com.brainstation.fantasyfootball.repository.IEventUserPointRepository;
import com.brainstation.fantasyfootball.repository.ITeamRepository;
import com.brainstation.fantasyfootball.repository.PlayerRepository;
import com.brainstation.fantasyfootball.repository.UserRepository;
import com.brainstation.fantasyfootball.service.ITeamService;
import com.brainstation.fantasyfootball.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TeamServiceImpl implements ITeamService {

    @Autowired
    private ITeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IEventUserPointRepository eventUserPointRepository;

    @Autowired
    private RoundService roundService;


    @Override
    public void teamFormation( List<Long> playerXI, Long eventId, Principal loggedInUser, CreateTeamDTO createTeamDTO){
        Integer defender,selected_defender=0;
        Integer midfielder,selected_midfielder=0;
        Integer forward,selected_forward=0;
        Integer goalkeeper,selected_goalkeeper=0;
        String formation = createTeamDTO.getFormation();
        defender=formation.charAt(0)-'0';
        midfielder=formation.charAt(2)-'0';
        forward=formation.charAt(4)-'0';
        goalkeeper=1;

        HashSet<Long> totalPlayer=new HashSet<>();

        for(Long id: playerXI){
            Player player=playerRepository.findById(id).get();

            totalPlayer.add(id);

            if(player.getPositionType()== PlayerPositionType.GK) selected_goalkeeper++;
            else if(player.getPositionType()== PlayerPositionType.DF) selected_defender++;
            else if(player.getPositionType()== PlayerPositionType.MF) selected_midfielder++;
            else selected_forward++;

        }

        String formation1 = selected_defender.toString() + '-' + selected_midfielder.toString() + '-' + selected_forward.toString();
        System.out.println(formation1);
//        if(selected_goalkeeper==goalkeeper && selected_defender==defender &&
//                midfielder==selected_midfielder && selected_forward==forward && totalPlayer.size()==11){
        Team team = new Team();
        List<String> selectedPositions = createTeamDTO.getSelectedPosition();
        if (formation1.equals("4-3-3")) {
            for (int i = 0; i < selectedPositions.size(); i++) {
                if (selectedPositions.get(i).equals("goalKeeper")) {
                    team.setPlayer1(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender1")) {
                    team.setPlayer2(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender2")) {
                    team.setPlayer3(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender3")) {
                    team.setPlayer4(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender4")) {
                    team.setPlayer5(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid1")) {
                    team.setPlayer6(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid2")) {
                    team.setPlayer7(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid3")) {
                    team.setPlayer8(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("forward1")) {
                    team.setPlayer9(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("forward2")) {
                    team.setPlayer10(playerXI.get(i));
                } else {
                    team.setPlayer11(playerXI.get(i));
                }
            }
        }
        else if (formation1.equals("4-4-2")) {
            for (int i = 0; i < selectedPositions.size(); i++) {
                if (selectedPositions.get(i).equals("goalKeeper")) {
                    team.setPlayer1(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender1")) {
                    team.setPlayer2(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender2")) {
                    team.setPlayer3(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender3")) {
                    team.setPlayer4(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender4")) {
                    team.setPlayer5(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid1")) {
                    team.setPlayer6(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid2")) {
                    team.setPlayer7(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid3")) {
                    team.setPlayer8(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid4")) {
                    team.setPlayer9(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("forward1")) {
                    team.setPlayer10(playerXI.get(i));
                } else {
                    team.setPlayer11(playerXI.get(i));
                }
            }
        }else if (formation1.equals("3-4-3")) {
            for (int i = 0; i < selectedPositions.size(); i++) {
                if (selectedPositions.get(i).equals("goalKeeper")) {
                    team.setPlayer1(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender1")) {
                    team.setPlayer2(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender2")) {
                    team.setPlayer3(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("defender3")) {
                    team.setPlayer4(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid1")) {
                    team.setPlayer5(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid2")) {
                    team.setPlayer6(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid3")) {
                    team.setPlayer7(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("mid4")) {
                    team.setPlayer8(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("forward1")) {
                    team.setPlayer9(playerXI.get(i));
                } else if (selectedPositions.get(i).equals("forward2")) {
                    team.setPlayer10(playerXI.get(i));
                } else {
                    team.setPlayer11(playerXI.get(i));
                }
            }
        }
        User user = userRepository.findByUsername(loggedInUser.getName());
        team.setEventId(eventId);
        team.setUserId(user.getId());
        EventUserPoint eventUserPoint = eventUserPointRepository.getEventUserPointByEventIdAndUserId(eventId, user.getId());
        eventUserPoint.setUserBalance(Long.parseLong(createTeamDTO.getUpdatedPoint()));

        team.setFormation(formation1);
        teamRepository.save(team);
//        }

    }

    @Override
    public void saveTeamPoints(Long id) {
        boolean isExist = teamRepository.existsById(id);
        Integer teamPoints=0;
        if(isExist){
            Optional<Team> team1 = teamRepository.findById(id);
            Team team = team1.get();
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer1());
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer2());
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer3());
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer4());
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer5());
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer6());
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer7());
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer8());
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer9());
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer10());
            teamPoints = teamPoints+ playerRepository.findPlayerPointsByPlayerId(team.getPlayer11());

            team.setTeamPoints(teamPoints);

            teamRepository.save(team);
        }
    }

    @Override
    public void updateTeam(Long eventId, Principal loggedInUser){
        User user = userRepository.findByUsername(loggedInUser.getName());
        Team team = teamRepository.findTeamByEventIdAndUserId(eventId, user.getId());
        teamRepository.delete(team);
        EventUserPoint eventUserPoint = eventUserPointRepository.getEventUserPointByEventIdAndUserId(eventId, user.getId());
        eventUserPoint.setUserBalance(100l);
        eventUserPointRepository.save(eventUserPoint);
    }

    @Override
    public List<Player> getUserTeam(Long eventId, Principal loggedInUser){
        User user = userRepository.findByUsername(loggedInUser.getName());
        List<Player> playerList = new ArrayList<>();
        if (teamRepository.existsTeamByEventIdAndUserId(eventId, user.getId())) {
            Team team = teamRepository.findTeamByEventIdAndUserId(eventId, user.getId());
            playerRepository.findById(team.getPlayer1()).ifPresent(playerList::add);
            playerRepository.findById(team.getPlayer2()).ifPresent(playerList::add);
            playerRepository.findById(team.getPlayer3()).ifPresent(playerList::add);
            playerRepository.findById(team.getPlayer4()).ifPresent(playerList::add);
            playerRepository.findById(team.getPlayer5()).ifPresent(playerList::add);
            playerRepository.findById(team.getPlayer6()).ifPresent(playerList::add);
            playerRepository.findById(team.getPlayer7()).ifPresent(playerList::add);
            playerRepository.findById(team.getPlayer8()).ifPresent(playerList::add);
            playerRepository.findById(team.getPlayer9()).ifPresent(playerList::add);
            playerRepository.findById(team.getPlayer10()).ifPresent(playerList::add);
            playerRepository.findById(team.getPlayer11()).ifPresent(playerList::add);
        }
        return playerList;
    }

    public Team getTeam(Long eventId, Principal loggedInUser){
        User user = userRepository.findByUsername(loggedInUser.getName());
        Team team = teamRepository.findTeamByEventIdAndUserId(eventId, user.getId());
        return team;
    }
    public Integer calculatePoints(Team team, List<Player> teamPlayers){
        Integer teamPoint = 0;
        for(Player player:teamPlayers){
            teamPoint = teamPoint + player.getPlayerPoints();
        }
        team.setTeamPoints(teamPoint);
        teamRepository.save(team);
        return teamPoint;
    }

    public boolean isTeamExists(Long eventId, Principal loggedInUser){
        User user = userRepository.findByUsername(loggedInUser.getName());
        if (teamRepository.existsTeamByEventIdAndUserId(eventId, user.getId())) {
            return true;
        }
        else{
            return false;
        }
    }

    public List<Team> getAllTeams(){
        return teamRepository.findAll();
    }
    public String getFormation(Team team){
        return team.getFormation();
    }

}
