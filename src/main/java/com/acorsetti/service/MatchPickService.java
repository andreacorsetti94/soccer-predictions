package com.acorsetti.service;

import com.acorsetti.model.Markets;
import com.acorsetti.model.MatchPick;

import java.util.List;

public interface MatchPickService {

    List<MatchPick> listAllPicks();
    MatchPick byFixtureAndMarket(String fixtureId, Markets.MarketValue marketValue);
    List<MatchPick> picksWithOddsBetween(double lowerBound, double upperBound);
    List<MatchPick> openPicks();
    List<MatchPick> openValuablePicks();
}
