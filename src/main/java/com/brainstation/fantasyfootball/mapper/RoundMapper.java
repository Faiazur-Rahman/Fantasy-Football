package com.brainstation.fantasyfootball.mapper;

import com.brainstation.fantasyfootball.model.entity.Round;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class RoundMapper {
    public Round toRound(Map<String,String> request){
        Round round = new Round();
        long tournamentId = Long.parseLong(request.get("tournament"));
        String date = request.get("roundDate");
        String year =date.substring(0,4);
        String month =date.substring(5,7)+'-';
        String day =date.substring(8,10)+'-';
        round.setRoundDate(day+month+year);
        round.setRoundName(request.get("roundName"));
        round.setTournament_key(tournamentId);
        return round;
    }
}
