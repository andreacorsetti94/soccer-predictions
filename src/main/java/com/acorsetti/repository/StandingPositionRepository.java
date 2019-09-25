package com.acorsetti.repository;

import com.acorsetti.model.jpa.StandingPosition;
import com.acorsetti.model.keys.StandingPositionCompositeKey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface StandingPositionRepository extends PagingAndSortingRepository<StandingPosition, StandingPositionCompositeKey> {

    List<StandingPosition> findByLeagueIdOrderByPositionAsc(String leagueId);

}
