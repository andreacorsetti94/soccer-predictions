package com.acorsetti.api.impl;

import com.acorsetti.api.APIResponse;
import com.acorsetti.api.APITeamStatisticsRetriever;
import com.acorsetti.api.json.JSONTeamStatisticsResponse;
import com.acorsetti.model.dto.TeamStatisticsDto;
import com.acorsetti.model.eval.TeamStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
@PropertySource("classpath:application.properties")
public class APITeamStatisticsRetrieverImpl implements APITeamStatisticsRetriever {

    @Autowired
    private Environment environment;

    @Autowired
    private RemoteDataRetrieverImpl<TeamStatistics, JSONTeamStatisticsResponse, TeamStatisticsDto> remoteDataRetriever;

    @Override
    public APIResponse<TeamStatistics> getStatsForLeagueAndTeam(String leagueId, String teamId) {
        String endpoint = this.environment.getProperty("statsByLeague").replace("<leagueId>", leagueId).replace("<teamId>",teamId);
        return this.remoteDataRetriever.retrieve(endpoint, JSONTeamStatisticsResponse.class, TeamStatistics.class);
    }
}
