package com.acorsetti.model;

import com.acorsetti.model.keys.MatchPickCompositeKey;
import com.acorsetti.utils.OddsUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.util.Objects;

@Entity
@IdClass(MatchPickCompositeKey.class)
public class MatchPick {

    @Id
    private String fixtureId;

    @Id
    private Markets.MarketValue market;

    private double odds;
    private double chance;
    private double expectedValue;
    private MarketResults.Result result;

    public MatchPick(String fixtureId, Markets.MarketValue market, double odds, double chance) {
        this.fixtureId = fixtureId;
        this.market = market;
        this.odds = odds;
        this.chance = chance;
        this.expectedValue = OddsUtils.expectedValue(this.odds, this.chance);
        this.result = MarketResults.Result.TO_BE_DEFINED;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public Markets.MarketValue getMarket() {
        return market;
    }

    public double getOdds() {
        return odds;
    }

    public double getChance() {
        return chance;
    }

    public double getExpectedValue() {
        return expectedValue;
    }

    public MarketResults.Result getResult() {
        return result;
    }

    public void setResult(MarketResults.Result result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MatchPick{" +
                "fixtureId=" + fixtureId +
                ", market=" + market +
                ", odds=" + odds +
                ", chance=" + chance +
                ", expectedValue=" + expectedValue +
                ", result=" + result +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchPick matchPick = (MatchPick) o;
        return Objects.equals(fixtureId, matchPick.fixtureId) &&
                market == matchPick.market;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixtureId, market);
    }

}
