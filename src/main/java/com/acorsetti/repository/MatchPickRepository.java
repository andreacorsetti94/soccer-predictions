package com.acorsetti.repository;

import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.keys.MatchPickCompositeKey;
import com.acorsetti.model.odds.OddsValue;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface MatchPickRepository extends PagingAndSortingRepository<MatchPick, MatchPickCompositeKey> {

    List<MatchPick> findAll();
    MatchPick findByFixtureIdAndMarket(String fixtureId, MarketValue marketValue);
    List<MatchPick> findByOddsBetween(OddsValue lowerBound, OddsValue upperBound);

    @Query("FROM MatchPick WHERE pickResult IS NULL OR pickResult = 'TO_BE_DEFINED'")
    List<MatchPick> findOpenPicks();

    @Query("FROM MatchPick WHERE (pickResult IS NULL OR pickResult = 'TO_BE_DEFINED') AND PickValue > 0")
    List<MatchPick> findValuableOpenPicks();

}
