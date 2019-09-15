package com.acorsetti.model.dto;

import com.acorsetti.model.odds.MarketOdds;

import java.util.List;

public class OddsDto {
    private String fixtureId;
    private List<MarketOdds> marketOdds;

    public OddsDto(String fixtureId, List<MarketOdds> marketOdds) {
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

}
