package com.acorsetti.model.jpa;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.keys.BetCompositeKey;
import com.acorsetti.model.odds.OddsValue;

import javax.persistence.*;

@Entity
@IdClass(BetCompositeKey.class)
public class Bet {

    @Id
    private String algoId;

    @Id
    private String fixtureId;

    @Id
    @Enumerated(EnumType.STRING)
    private MarketValue market;

    private Double profit;
    private Double amount;

    private OddsValue odds;

    public Bet() {
    }

    public Bet(String algoId, String fixtureId, MarketValue market, Double amount, OddsValue odds, Double profit) {
        this.algoId = algoId;
        this.fixtureId = fixtureId;
        this.market = market;
        this.amount = amount;
        this.odds = odds;
        this.profit = profit;
    }

    public MarketValue getMarket(){
        return market;
    }

    public String getAlgoId() {
        return algoId;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public Double getProfit() {
        return profit;
    }

    public Double getAmount() {
        return amount;
    }

    public OddsValue getOdds() {
        return odds;
    }

    public void setProfit(Double profit) {
        this.profit = profit;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "algoId='" + algoId + '\'' +
                ", fixtureId='" + fixtureId + '\'' +
                ", market=" + market +
                ", amount=" + amount +
                ", odds=" + odds +
                ", profit=" + profit +
                '}';
    }
}
