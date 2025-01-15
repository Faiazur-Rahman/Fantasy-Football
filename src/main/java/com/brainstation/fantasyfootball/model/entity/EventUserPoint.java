package com.brainstation.fantasyfootball.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "event_user_point")
public class EventUserPoint {
    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "event_id")
    private Long eventId;
    @Column(name = "user_id")
    private Long userId;
    private Long point;
    private Long userBalance;
    private String username;
//
//    @OneToOne(mappedBy = "eventUserPointTeam")
//    private Team team;
}
