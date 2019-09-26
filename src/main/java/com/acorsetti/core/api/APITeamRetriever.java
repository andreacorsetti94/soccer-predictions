package com.acorsetti.core.api;

import com.acorsetti.core.model.jpa.LeagueTeam;
import com.acorsetti.core.model.jpa.Team;

public interface APITeamRetriever {
    APIResponse<Team> byId(String teamId);
    APIResponse<LeagueTeam> byLeagueId(String leagueId);
}
