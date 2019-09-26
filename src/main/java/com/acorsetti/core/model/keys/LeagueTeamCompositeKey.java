package com.acorsetti.core.model.keys;

import java.io.Serializable;
import java.util.Objects;

public class LeagueTeamCompositeKey implements Serializable {

    private String leagueId;
    private String teamId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueTeamCompositeKey that = (LeagueTeamCompositeKey) o;
        return Objects.equals(leagueId, that.leagueId) &&
                Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(leagueId, teamId);
    }
}
