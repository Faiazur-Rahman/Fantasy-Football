package com.brainstation.fantasyfootball.service;
/*
 * author: Sadik Hassan
 * created: 4:05 pm, 20/10/2022
 */

import com.brainstation.fantasyfootball.common.PlayerPositionType;
import com.brainstation.fantasyfootball.model.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public interface PlayerService {
     List<Player> findPlayer(String keyword);

     void savePoint(Long id);

     List<Player> getAllPlayer();
     List<Player> getPlayersByPosition(PlayerPositionType position);
     List<Player> getPlayerByCountry(PlayerPositionType position);

     void updatePlayerPoints();

}
