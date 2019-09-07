package com.acorsetti.model.keys;

import com.acorsetti.model.enums.MarketValue;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Objects;


public class MatchPickCompositeKey implements Serializable {

    private String fixtureId;

    @Enumerated(EnumType.STRING)
    private MarketValue market;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchPickCompositeKey that = (MatchPickCompositeKey) o;
        return Objects.equals(fixtureId, that.fixtureId) &&
                market == that.market;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixtureId, market);
    }
}
