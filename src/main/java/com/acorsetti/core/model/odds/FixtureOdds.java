package com.acorsetti.core.model.odds;

import java.util.List;

public class FixtureOdds {

    private String fixtureId;
    private List<MarketOdds> marketOdds;

    public FixtureOdds() {
    }

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

    public void setFixtureId(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    public void setMarketOdds(List<MarketOdds> marketOdds) {
        this.marketOdds = marketOdds;
    }

    @Override
    public String toString() {
        return "FixtureOdds{" +
                "fixtureId='" + fixtureId + '\'' +
                ", marketOdds=" + marketOdds +
                '}';
    }
}
