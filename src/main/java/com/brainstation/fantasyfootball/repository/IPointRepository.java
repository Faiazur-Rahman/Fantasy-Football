package com.brainstation.fantasyfootball.repository;/*
 * author: Sadik Hassan
 * created: 12:33 pm, 20/10/2022
 */

import com.brainstation.fantasyfootball.model.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IPointRepository extends JpaRepository<Point, Long> {
    List<Point> findAll();

    @Query(value = "select point_score from point p where p.point_code =?1",
                        nativeQuery = true)
    Integer findByPointCode(String pointCode);
    Point findByPointID(Long id);

}
