package com.acorsetti.core.service;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.MatchPick;

import java.util.List;

public interface MatchPickService {

    List<MatchPick> listAllPicks();
    MatchPick byFixtureAndMarket(String fixtureId, MarketValue marketValue);
    List<MatchPick> picksWithOddsBetween(double lowerBound, double upperBound);
    List<MatchPick> openPicks();
    List<MatchPick> openValuablePicks();

    void savePicks(List<MatchPick> matchPicks);
    List<MatchPick> updateMatchPicksResult(List<MatchPick> matchPicks);
    List<MatchPick> generateNewPicks(List<Fixture> fixtureList);
}
