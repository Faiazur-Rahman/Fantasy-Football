package com.brainstation.fantasyfootball.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

/**
 * @author Shahinur Beg
 * created date: 10/19/2022
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
    private String password;
    private boolean enabled;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "U_Id")
    private List<UserRole> roles;
}
