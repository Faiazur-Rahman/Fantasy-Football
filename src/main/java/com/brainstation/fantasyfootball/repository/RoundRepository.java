package com.brainstation.fantasyfootball.repository;
import com.brainstation.fantasyfootball.model.entity.Round;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoundRepository extends JpaRepository<Round, Long> {
    @Query(value = "select * from round order by round_name asc",nativeQuery = true)
    List<Round> getAllRound();
    Round getRoundByRoundDate(String date);

}
