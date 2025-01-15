package com.brainstation.fantasyfootball.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlayerMatchPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long matchPointId;

    private Integer playerMatchPoint;

    @OneToOne()
    @JoinColumn(
            name="player_id",
            referencedColumnName = "id"
    )
    private Player player;
}
