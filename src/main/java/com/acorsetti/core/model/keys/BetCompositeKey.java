package com.acorsetti.core.model.keys;

import com.acorsetti.core.model.enums.MarketValue;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;

public class BetCompositeKey implements Serializable {

    private String algoId;
    private String fixtureId;

    @Enumerated(EnumType.STRING)
    private MarketValue market;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BetCompositeKey that = (BetCompositeKey) o;
        return Objects.equals(algoId, that.algoId) &&
                Objects.equals(fixtureId, that.fixtureId) &&
                market == that.market;
    }

    @Override
    public int hashCode() {
        return Objects.hash(algoId, fixtureId, market);
    }
}
