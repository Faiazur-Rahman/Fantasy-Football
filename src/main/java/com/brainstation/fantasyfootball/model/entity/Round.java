package com.brainstation.fantasyfootball.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roundDate;
    private String roundName;
    private Long tournament_key;
}
