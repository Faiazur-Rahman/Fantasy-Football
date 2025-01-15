package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.model.entity.EventUserPoint;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.model.entity.PlayerMatchPoint;
import com.brainstation.fantasyfootball.model.entity.Team;
import com.brainstation.fantasyfootball.repository.IEventUserPointRepository;
import com.brainstation.fantasyfootball.repository.IPlayerMatchPointRepository;
import com.brainstation.fantasyfootball.repository.PlayerRepository;
import com.brainstation.fantasyfootball.service.EventService;
import com.brainstation.fantasyfootball.service.IPlayerMatchPointService;
import com.brainstation.fantasyfootball.service.IPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Service
public class PlayerMatchPointServiceImpl implements IPlayerMatchPointService {
    private final IPlayerMatchPointRepository playerMatchPointRepository;
    private final IPointService pointService;
    private final PlayerRepository playerRepository;
    @Autowired
    private TeamServiceImpl teamService;
    @Autowired
    private EventService eventService;
    @Autowired
    private IEventUserPointRepository eventUserPointRepository;

    public PlayerMatchPointServiceImpl(IPlayerMatchPointRepository playerMatchPointRepository, PointServiceImpl pointService, PlayerRepository playerRepository) {
        this.playerMatchPointRepository = playerMatchPointRepository;
        this.pointService = pointService;
        this.playerRepository = playerRepository;
    }

    @Override
    public Integer calculatePlayerPoints(Long playerId, Integer goalScored, Integer assist, Integer matchPlayed, Integer cleanSheet , Integer yellowCard, Integer redCard, Integer manOfTheMatch){
        Integer playerMatchPointScore = 0;
        Integer gsScore =pointService.getScore("GS");
        Integer asScore =pointService.getScore("AS");
        Integer mpScore =pointService.getScore("MP");
        Integer csScore =pointService.getScore("CS");
        Integer ycScore =pointService.getScore("YC");
        Integer rcScore =pointService.getScore("RC");
        Integer momScore =pointService.getScore("MOM");

        playerMatchPointScore = (goalScored*gsScore)+(assist*asScore)+(matchPlayed*mpScore)+(cleanSheet*csScore)+(yellowCard*ycScore)+(redCard*rcScore)+(manOfTheMatch*momScore);
        boolean isExist = playerMatchPointRepository.existsByPlayerId(playerId);
        if(isExist) {
            PlayerMatchPoint playerMatchPoint = playerMatchPointRepository.findByPlayerId(playerId);
            playerMatchPoint.setPlayerMatchPoint(playerMatchPointScore);
            playerMatchPointRepository.save(playerMatchPoint);
        }
        else {
            PlayerMatchPoint playerMatchPoint = new PlayerMatchPoint();
            Player player = playerRepository.findById(playerId).get();
            playerMatchPoint.setPlayer(player);
            playerMatchPoint.setPlayerMatchPoint(playerMatchPointScore);
            playerMatchPointRepository.save(playerMatchPoint);
        }
        return playerMatchPointScore;
    }

    @Override
    public Integer getPoint(Long playerId) {
        PlayerMatchPoint playerMatchPoint = playerMatchPointRepository.findByPlayerId(playerId);
        return playerMatchPoint.getPlayerMatchPoint();
    }

    @Override
    public void updatePoint(Map<Long, Integer> userList) {
        for(var user: userList.entrySet()){
            Long playerId = user.getKey();
            Integer currentPoint = user.getValue();
            List<Team> teams = teamService.getAllTeams();
            for(Team team: teams){
                Long eventId = team.getEventId();
                Long userId = team.getUserId();
                Optional<EventUserPoint> eventUserPoint = eventService.getEventUserByEventIdUserId(eventId, userId);
                if(Objects.equals(team.getPlayer1(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
                else if(Objects.equals(team.getPlayer2(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
                else if(Objects.equals(team.getPlayer3(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
                else if(Objects.equals(team.getPlayer4(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
                else if(Objects.equals(team.getPlayer5(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
                else if(Objects.equals(team.getPlayer6(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
                else if(Objects.equals(team.getPlayer7(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
                else if(Objects.equals(team.getPlayer8(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
                else if(Objects.equals(team.getPlayer9(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
                else if(Objects.equals(team.getPlayer10(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
                else if(Objects.equals(team.getPlayer11(), playerId)){
                    if(eventUserPoint.isPresent()){
                        getPlayerPoint(eventUserPoint,currentPoint);
                    }
                }
            }
        }
    }

    public void getPlayerPoint(Optional<EventUserPoint> eventUserPoint, Integer currentPoint){
        Long prevPoint = eventUserPoint.get().getPoint();
        prevPoint = prevPoint + currentPoint;
        eventUserPoint.get().setPoint(prevPoint);
        eventUserPointRepository.save(eventUserPoint.get());
    }

}
