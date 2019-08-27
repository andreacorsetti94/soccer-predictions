package com.acorsetti.repository;

import com.acorsetti.model.jpa.Fixture;
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
}
