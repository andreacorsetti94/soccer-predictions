package com.acorsetti.core.repository;

import com.acorsetti.core.model.jpa.StandingPosition;
import com.acorsetti.core.model.keys.StandingPositionCompositeKey;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StandingPositionRepository extends PagingAndSortingRepository<StandingPosition, StandingPositionCompositeKey> {

    List<StandingPosition> findByLeagueIdOrderByPositionAsc(String leagueId);

}
