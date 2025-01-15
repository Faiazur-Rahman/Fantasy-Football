package com.brainstation.fantasyfootball.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "event_user_id", referencedColumnName = "id")
//    private EventUserPoint eventUserPointTeam;
    private Long userId;
    private Long eventId;

    private Long player1;
    private Long player2;
    private Long player3;
    private Long player4;
    private Long player5;
    private Long player6;
    private Long player7;
    private Long player8;
    private Long player9;
    private Long player10;
    private Long player11;

    private Integer teamPoints;
    private String formation;
}
