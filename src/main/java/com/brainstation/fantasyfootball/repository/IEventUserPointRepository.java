package com.brainstation.fantasyfootball.repository;

import com.brainstation.fantasyfootball.model.entity.EventUserPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEventUserPointRepository extends JpaRepository<EventUserPoint, Long> {
    List<EventUserPoint> getEventUserPointByEventIdOrderByPointDesc(Long id);
    EventUserPoint getEventUserPointByEventIdAndUserId(Long eventId, Long userId);
    List<EventUserPoint> getAllByEventId(Long id);
    boolean existsEventUserPointByEventIdAndUserId(Long eventId, Long userId);
    List<EventUserPoint> getAllByUserId(Long id);
    List<EventUserPoint> getEventUserPointByUsername(String username);
    @Query(value = "select * from event_user_point where event_id = ?1 and user_id = ?2",nativeQuery = true)
    Optional<EventUserPoint> getEventUserPoint(Long eventId, Long userId);
    @Query(value = "select * from event_user_point where event_id = ?1 and username = ?2",nativeQuery = true)
    Optional<EventUserPoint> getEventUserPointByUsername(Long eventId, String username);
}
