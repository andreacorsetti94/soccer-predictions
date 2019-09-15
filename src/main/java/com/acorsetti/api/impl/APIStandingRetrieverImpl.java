package com.acorsetti.api.impl;

import com.acorsetti.api.APIResponse;
import com.acorsetti.api.APIStandingsRetriever;
import com.acorsetti.api.RemoteDataRetriever;
import com.acorsetti.api.json.JSONStandingResponse;
import com.acorsetti.model.dto.StandingDto;
import com.acorsetti.model.jpa.StandingPosition;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
@PropertySource("classpath:application.properties")
public class APIStandingRetrieverImpl implements APIStandingsRetriever {

    @Autowired
    private Environment environment;

    @Autowired
    private RemoteDataRetriever<StandingPosition, JSONStandingResponse, StandingDto> remoteDataRetriever;


    @Override
    public APIResponse<StandingPosition> standingsByLeague(String leagueId) {
        String endpoint = this.environment.getProperty("standingsByLeague").replace("<leagueId>", leagueId);
        APIResponse<StandingPosition> apiResponse = this.remoteDataRetriever.retrieve(endpoint, JSONStandingResponse.class, StandingPosition.class);
        apiResponse.getBody().forEach( sp -> sp.setLeagueId(leagueId));
        return apiResponse;
    }
}
