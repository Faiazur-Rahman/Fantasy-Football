package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.model.entity.PlayerMatchPoint;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface IPlayerMatchPointService {

    Integer calculatePlayerPoints(Long playerId, Integer GS, Integer AS, Integer MP, Integer CS, Integer YC, Integer RC, Integer MOM);

    Integer getPoint(Long playerId);

    void updatePoint(Map<Long,Integer> userList);
}
