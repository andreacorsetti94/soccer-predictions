package com.acorsetti.core.model.eval;

import java.util.List;

public class FixtureOutcomes {

    private String fixtureId;
    private List<MarketOutcome> marketOutcomes;

    public FixtureOutcomes(String fixtureId, List<MarketOutcome> marketOutcomes) {
        this.fixtureId = fixtureId;
        this.marketOutcomes = marketOutcomes;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public List<MarketOutcome> getMarketOutcomes() {
        return marketOutcomes;
    }

    @Override
    public String toString() {
        return "FixtureOutcomes{" +
                "fixtureId='" + fixtureId + '\'' +
                ", marketOutcomes=" + marketOutcomes +
                '}';
    }
}
