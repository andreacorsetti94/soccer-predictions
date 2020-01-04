package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.Fixture;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface FixtureRepository extends PagingAndSortingRepository<Fixture,String> {

    @Query("FROM Fixture WHERE DATE(eventDate) = DATE(?1) ORDER BY eventDate ASC")
    List<Fixture> findByEventDate(LocalDate day);

    Fixture findByFixtureId(String id);

    @Query("FROM Fixture where homeTeamId = ?1 OR awayTeamId = ?1 ORDER BY eventDate DESC")
    List<Fixture> findByTeamIdOrderByEventDateDesc(String teamId);

    @Query("FROM Fixture WHERE DATE(eventDate) >= DATE(?1) AND DATE(eventDate) <= DATE(?2) ORDER BY eventDate ASC")
    List<Fixture> findByEventDateBetween(LocalDate before, LocalDate after);

    @Query("FROM Fixture WHERE leagueId = ?1 AND homeTeamId = ?2 AND awayTeamId = ?3")
    List<Fixture> findByLeagueIdAndHomeIdAndAwayId(String leagueId, String homeId, String awayId);
}
