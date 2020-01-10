package com.acorsetti.core.newodds;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.odds.OddsValue;

public class OddEntity {
    private String fixtureId;
    private MarketValue marketValue;
    private OddsValue avgVal;
    private OddsValue maxVal;

    public OddEntity(String fixtureId, MarketValue marketValue, OddsValue avgVal, OddsValue maxVal) {
        this.fixtureId = fixtureId;
        this.marketValue = marketValue;
        this.avgVal = avgVal;
        this.maxVal = maxVal;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public MarketValue getMarketValue() {
        return marketValue;
    }

    public OddsValue getAvgVal() {
        return avgVal;
    }

    public OddsValue getMaxVal() {
        return maxVal;
    }

    @Override
    public String toString() {
        return "OddEntity{" +
                "fixtureId='" + fixtureId + '\'' +
                ", marketValue=" + marketValue +
                ", avgVal=" + avgVal +
                ", maxVal=" + maxVal +
                '}';
    }
}
