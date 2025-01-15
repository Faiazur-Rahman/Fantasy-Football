package com.brainstation.fantasyfootball.service;

import com.brainstation.fantasyfootball.model.entity.Point;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPointService {

    void updatePoint(Long id, String pointCode,Integer pointScore);
    void addPoint(String pointCode,Integer pointScore);
    List<Point> getPoints();
    Integer getScore(String pointCode);

}
