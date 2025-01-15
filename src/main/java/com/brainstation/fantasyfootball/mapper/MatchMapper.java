package com.brainstation.fantasyfootball.mapper;

import com.brainstation.fantasyfootball.model.entity.Match;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MatchMapper {
    public Match toMatch(Map<String,String> request){
        Match match = new Match();
        long roundId = Long.parseLong(request.get("round_key"));
        match.setRoundId(roundId);
        match.setTeam1Name(request.get("team1"));
        match.setTeam2Name(request.get("team2"));
        match.setTeam1Goal(request.get("team1_goal"));
        match.setTeam2Goal(request.get("team2_goal"));
        String time = request.get("match_time");
        String hour = time.substring(0,2);
        String minute = time.substring(3,5);
        int hour1 = Integer.parseInt(hour);
        if(hour1 > 12){
            match.setMatchTime((hour1-12)+":"+minute+" PM");
        } else {
            match.setMatchTime(time + " AM");
        }
        return  match;
    }
}
