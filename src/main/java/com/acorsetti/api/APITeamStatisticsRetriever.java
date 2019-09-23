package com.acorsetti.api;

import com.acorsetti.model.eval.TeamStatistics;

public interface APITeamStatisticsRetriever {
    APIResponse<TeamStatistics> getStatsForLeagueAndTeam(String leagueId, String teamId);
}
