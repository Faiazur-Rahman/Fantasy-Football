package com.brainstation.fantasyfootball.repository;/*
 * author: Sadik Hassan
 * created: 12:33 pm, 20/10/2022
 */

import com.brainstation.fantasyfootball.model.entity.PlayerMatchPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPlayerMatchPointRepository extends JpaRepository<PlayerMatchPoint, Long> {

   @Query(
         value="SELECT * FROM player_match_point mp WHERE mp.player_id = ?1",
         nativeQuery = true
   )
   PlayerMatchPoint findByPlayerId (Long id);

   @Override
   boolean existsById(Long id);

    boolean existsByPlayerId(Long id);
}
