package com.brainstation.fantasyfootball.service.impl;
/*
 * author: Sadik Hassan
 * created: 6:47 pm, 20/10/2022
 */

import com.brainstation.fantasyfootball.common.PlayerPositionType;
import com.brainstation.fantasyfootball.model.entity.Match;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.model.entity.PlayerMatchPoint;
import com.brainstation.fantasyfootball.model.entity.Round;
import com.brainstation.fantasyfootball.repository.IPlayerMatchPointRepository;
import com.brainstation.fantasyfootball.repository.PlayerRepository;
import com.brainstation.fantasyfootball.service.MatchService;
import com.brainstation.fantasyfootball.service.PlayerService;
import com.brainstation.fantasyfootball.service.RoundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    private final IPlayerMatchPointRepository playerMatchPointRepository;
    @Autowired
    private RoundService roundService;

    @Autowired
    private MatchService matchService;

    public PlayerServiceImpl(PlayerRepository playerRepository, IPlayerMatchPointRepository playerMatchPointRepository) {
        this.playerRepository = playerRepository;
        this.playerMatchPointRepository = playerMatchPointRepository;
    }

    public List<Player> getAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
    public List<Player> findPlayer(String keyword) {
        return playerRepository.searchByAll(keyword);
    }
    public Page<Player>findPage(int pageNumber, String sortField,String sortDir){
        Sort sort=Sort.by(sortField);
       // if (sortDir==null)sortDir="asc";
        sort=sortDir.equals("asc") ? sort.ascending():sort.descending();
        Pageable pageable=PageRequest.of(pageNumber-1,10,sort);
        return playerRepository.findAll(pageable);
    }

    @Override
    public void savePoint(Long id) {
        boolean isExist = playerMatchPointRepository.existsByPlayerId(id);
        if(isExist){
            PlayerMatchPoint playerMatchPoint = playerMatchPointRepository.findByPlayerId(id);
            Player player = playerRepository.findById(id).get();
            player.setPlayerPoints(playerMatchPoint.getPlayerMatchPoint());
            playerRepository.save(player);
        }
    }

    public void save(Player player){
        playerRepository.save(player);
    }
    public Player getPlayerDetailsById(long id){
        return playerRepository.findById(id).get();
    }
    public void delete(long id){
        playerRepository.deleteById(id);
    }
    @Override
    public List<Player> getPlayerByCountry(PlayerPositionType position){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date day = new Date();
        String dateTime = formatter.format(day);
        String date = dateTime.substring(0, 10);
        Optional<Round> round = roundService.getRoundsByDate(date);
        List<String> countries = new ArrayList<>();
        if (round.isPresent()) {
            List<Match> matches = matchService.getMatchById(round.get().getId());
            for (var match : matches) {
                countries.add(match.getTeam1Name());
                countries.add(match.getTeam2Name());
            }
        }
        List<Player> playerList = new ArrayList<>();
        for(var country: countries){
            List<Player> players = playerRepository.findPlayersByCountryAndPositionTypeOrderByValueDesc(country,position);
            for(var p2: players){
                if(players.isEmpty()){
                    players.add(p2);
                } else {
                    boolean isPresent = false;
                    for(var p1: players){
                        if(p1.getValue()<=p2.getValue()){
                            playerList.add(p2);
                            isPresent = true;
                            break;
                        }
                    }
                    if(!isPresent)
                        playerList.add(p2);
                }
            }
        }
        return playerList;
    }

    @Override
    public void updatePlayerPoints() {
        playerRepository.updatePoints();
    }

    public List<Player> getPlayersByPosition(PlayerPositionType position){
        return playerRepository.findPlayersByPositionType(position);
    }

}
