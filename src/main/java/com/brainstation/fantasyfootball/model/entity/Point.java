package com.brainstation.fantasyfootball.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pointID;

    private String pointCode;

    private Integer pointScore;

   @ManyToOne()
    @JoinColumn(
                name= "match_point_id",
                referencedColumnName = "matchPointId"
    )
    private PlayerMatchPoint playerMatchPoint;

}
