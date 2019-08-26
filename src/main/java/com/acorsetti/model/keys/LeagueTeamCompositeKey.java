package com.acorsetti.model.keys;

import com.acorsetti.model.League;
import com.acorsetti.model.Team;

import java.io.Serializable;

public class LeagueTeamCompositeKey implements Serializable {

    private League leagueId;
    private Team teamId;
}
