package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.repository.IPlayerMatchPointRepository;
import com.brainstation.fantasyfootball.repository.IPointRepository;
import com.brainstation.fantasyfootball.service.IPlayerMatchPointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PlayerMatchPointServiceImplTest {

    @Autowired
    PlayerMatchPointServiceImpl playerMatchPointService;


//    @Test
//    public  void calculate(){
//        playerMatchPointService.calculatePlayerPoints(4L,1,0,1,0,0,0,0);
//    }
//
    @Test
    public void getPoint(){
        Integer actualValue = playerMatchPointService.getPoint(4L);
        Integer expectedValue = 6;
        assertEquals(actualValue,expectedValue);
    }

}