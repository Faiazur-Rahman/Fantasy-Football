package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.common.PlayerPositionType;
import com.brainstation.fantasyfootball.model.entity.Player;
import com.brainstation.fantasyfootball.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


/**
 * @author Rumman
 * @created 24/10/2022 - 12:35 PM
 * @project BS23-fantasy-football
 */
@SpringBootTest
class PlayerServiceImplTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private PlayerServiceImpl playerService;


    @Test
    void savePlayerTest() {


//        Player player=new Player();
//        player.setId(10L);
//        player.setFirstname("Lionel");
//        player.setLastname("Messi");
//        player.setNickname("LM10");
//        player.setPositionType(PlayerPositionType.FW);
//        player.setValue(15.0);
//        player.setCountry("Argentina");
//        player.setStatus("Active");
//        player.setPicture("https://img.olympicchannel.com/images/image/private/f_auto/t_1-1_300/primary/wfrhxc0kh2vvq77sonki");
//
//        playerRepository.save(player);

        Long expectedValue=10L;
        Long actualValue=playerRepository.findById(10L).get().getId();
        System.out.println("expected value :"+expectedValue);
        System.out.println("actualValue :"+actualValue);

        assertEquals(expectedValue,actualValue);
    }

    @Test
    void getPlayerByIdTest(){
        Long expectedValue=3L;
        Long actualValue=playerRepository.findById(3L).get().getId();

        assertEquals(expectedValue,actualValue);
    }

    @Test
    void deletePlayerByIdTest(){
        //playerRepository.deleteById(6L);

        Player player = playerRepository.findById(6L).orElse(null);


        assertNull(player);
    }

//    @Test
//    void getAllPlayerTest(){
//
//        // insert all details from db
//        Object[] expectedValue=new Object[2];
//
//        expectedValue[0]=new Player(3L,"Anupam","Das","Anu7", PlayerPositionType.FW,8.0,"Germany","Active");
//        expectedValue[1]=new Player(4L,"Shahinur","Beg","Beg11", PlayerPositionType.GK,5.0,"England","Active");
//
//
//        Object[] actualValue=playerRepository.findAll().toArray();
//
//        assertArrayEquals(expectedValue,actualValue);
//    }

//    @Test
//    void findPlayerTest(){
//
//        // insert all details from db
//        Object[] expectedValue=new Object[2];
//
//        expectedValue[0]=new Player(3L,"Anupam","Das","Anu7", PlayerPositionType.FW,8.0,"Germany","Active");
//        expectedValue[1]=new Player(4L,"Shahinur","Beg","Beg11", PlayerPositionType.GK,5.0,"England","Active");
//
//        Object[] actualValue=playerRepository.searchByAll("tive").toArray();
//
//        assertArrayEquals(expectedValue,actualValue);
//
//    }

//    @Test
//    public void savePoint(){
//        playerService.savePoint(1L);
//
//    }
}