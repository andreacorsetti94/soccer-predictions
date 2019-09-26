package com.acorsetti.core.model.jpa;

import com.acorsetti.core.model.keys.LeagueTeamCompositeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(LeagueTeamCompositeKey.class)
public class LeagueTeam {

    @Id
    private String leagueId;

    @Id
    private String teamId;

    public LeagueTeam(){}

    public LeagueTeam(String leagueId, String teamId) {
        this.leagueId = leagueId;
        this.teamId = teamId;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "LeagueTeam{" +
                "leagueId='" + leagueId + '\'' +
                ", teamId='" + teamId + '\'' +
                '}';
    }
}
