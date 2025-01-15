package com.brainstation.fantasyfootball.model.entity;

import com.brainstation.fantasyfootball.common.PlayerPositionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*
 * author: Sadik Hassan
 * created: 12:30 pm, 20/10/2022
 */
@Entity
@Data
@Table(name = "player")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "nick_name")
    private String nickname;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private PlayerPositionType positionType;

    @Column(name = "value")
    private Double value;

    @Column(name = "country")
    private String country;

    @Column(name = "status")
    private String status;

    @Column(name="picture")
    private String picture;

    @Column(name = "player_points")
    private Integer playerPoints;
}