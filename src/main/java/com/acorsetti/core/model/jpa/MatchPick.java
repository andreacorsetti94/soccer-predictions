package com.acorsetti.core.model.jpa;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.enums.PickResult;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.PickValue;
import com.acorsetti.core.model.keys.MatchPickCompositeKey;
import com.acorsetti.core.model.odds.OddsValue;

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

    private OddsValue odds;

    private Chance chance;
    private PickValue pickValue;

    @Enumerated(EnumType.STRING)
    private PickResult pickResult;

    public MatchPick() {
    }

    public MatchPick(String fixtureId, MarketValue market, OddsValue odds, Chance chance, PickValue pickValue) {
        this.fixtureId = fixtureId;
        this.market = market;
        this.odds = odds;
        this.chance = chance;
        this.pickValue = pickValue;
        this.pickResult = PickResult.TO_BE_DEFINED;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public MarketValue getMarket() {
        return market;
    }

    public OddsValue getOdds() {
        return odds;
    }

    public Chance getChance() {
        return chance;
    }

    public PickValue getPickValue() {
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
                ", market=" + market.getRepresentation() +
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
                market == matchPick.market &&
                Objects.equals(odds, matchPick.odds) &&
                Objects.equals(chance, matchPick.chance) &&
                Objects.equals(pickValue, matchPick.pickValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixtureId, market, odds, chance, pickValue);
    }
}
