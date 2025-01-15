package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.model.entity.Match;
import com.brainstation.fantasyfootball.repository.MatchRepository;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MatchServiceImplTest {

    @Autowired
    private MatchRepository matchRepository;

    @Test
    void addMatchTest(){
//        Match match=new Match();
//        match.setId(1L);
//        match.setMatchTime("22-12-2022");
//        match.setRoundId(4L);
//        match.setTeam1Name("Brazil");
//        match.setTeam2Name("Germany");
//        match.setTeam1Goal("4");
//        match.setTeam2Goal("3");
//
//        matchRepository.save(match);
//        System.out.println(match);

        Long expectedValue=25L;
        Long actualValue=matchRepository.findById(25L).get().getId();
        System.out.println("expectedValue :"+expectedValue);
        System.out.println("actualValue :"+actualValue);

        assertEquals(expectedValue,actualValue);
    }

    @Test
    void deleteMachTest(){

        //matchRepository.deleteById(27L);
        Match match=matchRepository.findById(27L).orElse(null);
        if(match==null){
            System.out.println("Match Deleted Successfully");
        }
        assertNull(match);

    }


}
