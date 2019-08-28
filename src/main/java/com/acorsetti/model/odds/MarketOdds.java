package com.acorsetti.model.odds;

import com.acorsetti.model.enums.MarketType;
import com.acorsetti.model.enums.MarketValue;

public class MarketOdds {

    private MarketType marketType;
    private MarketValue marketValue;
    private OddsValue oddsValue;

    public MarketOdds(MarketType marketType, MarketValue marketValue, OddsValue oddsValue) {
        this.marketType = marketType;
        this.marketValue = marketValue;
        this.oddsValue = oddsValue;
    }

    public MarketType getMarketType() {
        return marketType;
    }

    public MarketValue getMarketValue() {
        return marketValue;
    }

    public OddsValue getOddsValue() {
        return oddsValue;
    }

    @Override
    public String toString() {
        return "MarketOdds{" +
                "marketType=" + marketType +
                ", marketValue=" + marketValue +
                ", oddsValue=" + oddsValue +
                '}';
    }
}
