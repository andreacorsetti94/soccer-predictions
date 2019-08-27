package com.acorsetti.model;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.enums.PickResult;
import com.acorsetti.model.keys.MatchPickCompositeKey;
import com.acorsetti.utils.OddsUtils;

import javax.persistence.*;
import java.util.Objects;

@Entity
@IdClass(MatchPickCompositeKey.class)
public class MatchPick {

    @Id
    private String fixtureId;

    @Id
    @Enumerated(EnumType.STRING)
    private MarketValue market;

    private double odds;
    private double chance;
    private double pickValue;

    @Enumerated(EnumType.STRING)
    private PickResult pickResult;

    public MatchPick() {
    }

    public MatchPick(String fixtureId, MarketValue market, double odds, double chance) {
        this.fixtureId = fixtureId;
        this.market = market;
        this.odds = odds;
        this.chance = chance;
        this.pickValue = OddsUtils.expectedValue(this.odds, this.chance);
        this.pickResult = PickResult.TO_BE_DEFINED;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public MarketValue getMarket() {
        return market;
    }

    public double getOdds() {
        return odds;
    }

    public double getChance() {
        return chance;
    }

    public double getPickValue() {
        return pickValue;
    }

    public PickResult getPickResult() {
        return pickResult;
    }

    public void setPickResult(PickResult pickResult) {
        this.pickResult = pickResult;
    }

    @Override
    public String toString() {
        return "MatchPick{" +
                "fixtureId=" + fixtureId +
                ", market=" + market +
                ", odds=" + odds +
                ", chance=" + chance +
                ", pickValue=" + pickValue +
                ", pickResult=" + pickResult +
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
