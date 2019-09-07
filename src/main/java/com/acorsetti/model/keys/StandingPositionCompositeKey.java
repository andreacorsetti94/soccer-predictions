package com.acorsetti.model.keys;

import java.io.Serializable;
import java.util.Objects;

public class StandingPositionCompositeKey implements Serializable {

    private String leagueId;
    private int rank;
    private String teamName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StandingPositionCompositeKey that = (StandingPositionCompositeKey) o;
        return rank == that.rank &&
                Objects.equals(leagueId, that.leagueId) &&
                Objects.equals(teamName, that.teamName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leagueId, rank, teamName);
    }
}
