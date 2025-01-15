package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.model.entity.Point;
import com.brainstation.fantasyfootball.repository.IPointRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Rumman
 * @created 24/10/2022 - 6:50 PM
 * @project BS23-fantasy-football
 */

@SpringBootTest
class PointServiceImplTest {

    @Autowired
    private IPointRepository iPointRepository;

//    @Test
//    void addPointTest() {
//        Point point=new Point();
//        point.setPointCode("MOM");
//        point.setPointScore(3);
//        iPointRepository.save(point);
//
//        String expectedValue="MOM";
//        String actualValue=iPointRepository.findByPointCode("MOM").getPointCode();
//        assertEquals(expectedValue,actualValue);
//    }

    @Test
    void updatePointTest(){

        Point point=iPointRepository.findByPointID(4L);
        point.setPointCode("ASSIST");
        point.setPointScore(5);
        iPointRepository.save(point);

        int expectedValue=5;
        int actualValue=iPointRepository.findByPointID(4L).getPointScore();

        assertEquals(expectedValue,actualValue);

    }


}