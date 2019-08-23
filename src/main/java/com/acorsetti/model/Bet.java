package com.acorsetti.model;

import com.acorsetti.model.keys.BetCompositeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(BetCompositeKey.class)
public class Bet {

    @Id
    private String algoId;

    @Id
    private String fixtureId;

    @Id
    private Markets.MarketValue market;

    private Double profit;
    private Double amount;
    private Double odds;

    public Bet() {
    }

    public Bet(String algoId, String fixtureId, Markets.MarketValue market, Double amount, Double odds, Double profit) {
        this.algoId = algoId;
        this.fixtureId = fixtureId;
        this.market = market;
        this.amount = amount;
        this.odds = odds;
        this.profit = profit;
    }

    public Markets.MarketValue getMarket(){
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

    public Double getOdds() {
        return odds;
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
