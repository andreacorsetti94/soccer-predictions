package com.acorsetti.model.eval;

import com.acorsetti.model.enums.MarketType;
import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.enums.PickResult;

public class MarketOutcome {

    private MarketType marketType;
    private MarketValue marketValue;
    private PickResult result;

    public MarketOutcome(MarketType marketType, MarketValue marketValue, PickResult result) {
        this.marketType = marketType;
        this.marketValue = marketValue;
        this.result = result;
    }

    public MarketOutcome(MarketType marketType, MarketValue marketValue) {
        this.marketType = marketType;
        this.marketValue = marketValue;
    }

    public MarketType getMarketType() {
        return marketType;
    }


    public MarketValue getMarketValue() {
        return marketValue;
    }

    public PickResult getResult() {
        return result;
    }

    public void setResult(PickResult result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MarketOutcome{" +
                "marketType=" + marketType +
                ", marketValue=" + marketValue +
                ", result=" + result +
                '}';
    }
}
