package com.brainstation.fantasyfootball.repository;
/*
 * author: Sadik Hassan
 * created: 12:30 pm, 20/10/2022
 */

import com.brainstation.fantasyfootball.common.PlayerPositionType;
import com.brainstation.fantasyfootball.model.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Long> {

    @Query("SELECT player from Player player where concat(player.firstname,player.lastname,player.nickname,player.positionType,player.country,player.value,player.status,player.playerPoints)like %?1%")
    List<Player> searchByAll(String keyword);

    @Query("SELECT p.playerPoints FROM Player p WHERE p.id = ?1")
    Integer findPlayerPointsByPlayerId(Long id);

    List<Player> findPlayersByFirstnameContaining(String query);

    Player findByFirstname(String firstname);

    Player findByLastname(String lastname);

    Player findByNickname(String nickname);

    List<Player> findPlayersByPositionType(PlayerPositionType position);
    List<Player> findPlayersByCountryAndPositionTypeOrderByValueDesc(String country,PlayerPositionType position);
    @Transactional
    @Modifying
    @Query(value = "update player p set p.player_points=0 where p.player_points>0",nativeQuery = true)
    void updatePoints();

}
