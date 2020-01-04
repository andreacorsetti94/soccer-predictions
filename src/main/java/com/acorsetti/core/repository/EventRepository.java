package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.Event;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface EventRepository extends PagingAndSortingRepository<Event, Long> {

    Event findByEventId(String id);
    List<Event> findByFixtureId(String fixtureId);

    @Query("FROM Event WHERE eventType = ?1")
    List<Event> findAllByEventType(String eventType);

    @Query("FROM Event WHERE eventType = ?1 AND elapsed >= ?2 AND elapsed <= ?3")
    List<Event> findAllByEventTypeAndElapsedBetween(String eventType, int pre, int post);

    @Query("FROM Event WHERE eventType = ?1 AND fixtureId = ?2")
    List<Event> findAllByEventTypeAndFixtureId(String eventType, String fixtureId);

    @Query("FROM Event WHERE eventType = ?1 AND fixtureId = ?2 AND elapsed = ?3 AND teamId = ?4 AND detail = ?5")
    List<Event> findAllByEventTypeAndFixtureIdAndElapsedAndTeamIdAndDetail(String eventType, String fixtureId,
             int elapsed, String teamId, String detail);

    @Query("FROM Event WHERE eventType = 'Goal' AND detail != 'Missed Penalty' AND elapsed >= ?1 AND elapsed <= ?2")
    List<Event> findAllGoalsAndElapsedBetween(int pre, int post);

    @Query("FROM Event WHERE eventType = 'Goal' AND detail != 'Missed Penalty' AND fixtureId = ?1")
    List<Event> findAllGoalsAndFixtureId(String fixtureId);
}
