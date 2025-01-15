package com.brainstation.fantasyfootball.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "tournament", fetch = FetchType.LAZY)
    Set<Event> events = new HashSet<>();
    public void addEvent(Event event){
        events.add(event);
    }
}
