package com.acorsetti.core.api;

import com.acorsetti.core.model.eval.TeamStatistics;

public interface APITeamStatisticsRetriever {
    APIResponse<TeamStatistics> getStatsForLeagueAndTeam(String leagueId, String teamId);
}
