package com.brainstation.fantasyfootball.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Integer startRound;
    private Long eventCreator;
    private String eventName;
    private String eventDescription;
    @Column(unique = true)
    private String eventCode;
    @ManyToOne
    @JoinColumn(name = "tournament_id")
    @JsonIgnore
    private Tournament tournament;

}
