package com.acorsetti.repository;

import com.acorsetti.model.StandingPosition;
import com.acorsetti.model.keys.StandingPositionCompositeKey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StandingPositionRepository extends PagingAndSortingRepository<StandingPosition, StandingPositionCompositeKey> {

    List<StandingPosition> findByLeagueIdOrderByRankAsc(String leagueId);
}
