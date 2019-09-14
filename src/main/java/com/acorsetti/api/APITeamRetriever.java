package com.acorsetti.api;

import com.acorsetti.model.jpa.LeagueTeam;
import com.acorsetti.model.jpa.Team;

public interface APITeamRetriever {
    APIResponse<Team> byId(String teamId);
    APIResponse<LeagueTeam> byLeagueId(String leagueId);
}
