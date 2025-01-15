package com.brainstation.fantasyfootball.service.impl;

import com.brainstation.fantasyfootball.model.entity.Point;
import com.brainstation.fantasyfootball.repository.IPointRepository;
import com.brainstation.fantasyfootball.service.IPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements IPointService {
     @Autowired
     private IPointRepository pointRepository;

    @Override
    public void updatePoint(Long id, String pointCode, Integer pointScore) {
        Point point = pointRepository.findByPointID(id);
        point.setPointCode(pointCode);
        point.setPointScore(pointScore);
        pointRepository.save(point);
    }

    @Override
    public void addPoint(String pointCode, Integer pointScore) {
            Point point = new Point();
            point.setPointCode(pointCode);
            point.setPointScore(pointScore);
            pointRepository.save(point);
    }

    @Override
    public List<Point> getPoints() {
       List<Point> points = pointRepository.findAll();
       return points;
    }

    @Override
    public Integer getScore( String pointCode) {
        Integer point= pointRepository.findByPointCode(pointCode);
        return point;
    }
}
