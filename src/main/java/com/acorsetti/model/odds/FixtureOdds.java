package com.acorsetti.model.odds;

import java.util.List;

public class FixtureOdds {

    private String fixtureId;
    private List<MarketOdds> marketOdds;

    public FixtureOdds(String fixtureId, List<MarketOdds> marketOdds) {
        this.fixtureId = fixtureId;
        this.marketOdds = marketOdds;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public List<MarketOdds> getMarketOdds() {
        return marketOdds;
    }

    @Override
    public String toString() {
        return "FixtureOdds{" +
                "fixtureId='" + fixtureId + '\'' +
                ", marketOdds=" + marketOdds +
                '}';
    }
}
