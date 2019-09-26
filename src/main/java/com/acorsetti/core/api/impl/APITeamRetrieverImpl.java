package com.acorsetti.core.api.impl;

import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.api.APITeamRetriever;
import com.acorsetti.core.api.RemoteDataRetriever;
import com.acorsetti.core.api.json.JSONTeamResponse;
import com.acorsetti.core.model.dto.TeamDto;
import com.acorsetti.core.model.jpa.LeagueTeam;
import com.acorsetti.core.model.jpa.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
@PropertySource("classpath:application.properties")
public class APITeamRetrieverImpl implements APITeamRetriever {

    @Autowired
    private Environment environment;

    @Autowired
    private RemoteDataRetriever<Team, JSONTeamResponse, TeamDto> teamRemoteDataRetriever;

    @Override
    public APIResponse<Team> byId(String teamId) {
        String endpoint = this.environment.getProperty("teamsById").replace("<teamId>",teamId);
        return this.teamRemoteDataRetriever.retrieve(endpoint, JSONTeamResponse.class, Team.class);
    }

    @Override
    public APIResponse<LeagueTeam> byLeagueId(String leagueId) {
        String endpoint = this.environment.getProperty("teamsByLeagueId").replace("<leagueId>", leagueId);
        APIResponse<Team> teamAPIResponse = this.teamRemoteDataRetriever.retrieve(endpoint, JSONTeamResponse.class, Team.class);
        List<LeagueTeam> leagueTeamList = new ArrayList<>();
        teamAPIResponse.getBody().forEach( team -> leagueTeamList.add(new LeagueTeam(leagueId, team.getTeamId())));
        return new APIResponse<>(teamAPIResponse.getResponse(), teamAPIResponse.getResults(), leagueTeamList);
    }
}
