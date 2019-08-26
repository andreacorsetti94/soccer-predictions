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
    private Markets.MarketValue marketValue;

    private double oddValue;
    private double chance;
    private double expectedValue;
    private MarketResults.Result result;

    public MatchPick(String fixtureId, Markets.MarketValue marketValue, double oddValue, double chance) {
        this.fixtureId = fixtureId;
        this.marketValue = marketValue;
        this.oddValue = oddValue;
        this.chance = chance;
        this.expectedValue = OddsUtils.expectedValue(this.oddValue, this.chance);
        this.result = MarketResults.Result.TO_BE_DEFINED;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public Markets.MarketValue getMarketValue() {
        return marketValue;
    }

    public double getOddValue() {
        return oddValue;
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
                ", marketValue=" + marketValue +
                ", oddValue=" + oddValue +
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
                marketValue == matchPick.marketValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixtureId, marketValue);
    }

}
