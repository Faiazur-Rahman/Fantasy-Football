package com.brainstation.fantasyfootball.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String matchTime;
    private String team1Name;
    private String team2Name;
    private String team1Goal;
    private String team2Goal;
    private Long roundId;
}
