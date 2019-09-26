package com.acorsetti.core.api.impl;

import com.acorsetti.core.api.APIOddsRetriever;
import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.api.json.JSONOddsResponse;
import com.acorsetti.core.model.dto.OddsDto;
import com.acorsetti.core.model.odds.FixtureOdds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
@PropertySource("classpath:application.properties")
public class APIOddsRetrieverImpl implements APIOddsRetriever {

    @Autowired
    private Environment environment;

    @Autowired
    private RemoteDataRetrieverImpl<FixtureOdds, JSONOddsResponse, OddsDto> remoteDataRetriever;

    @Override
    public APIResponse<FixtureOdds> oddsByFixture(String fixtureId) {
        String endpoint = this.environment.getProperty("oddsByFixture").replace("<fixtureId>", fixtureId);
        APIResponse<FixtureOdds> apiResponse = this.remoteDataRetriever.retrieve(endpoint, JSONOddsResponse.class, FixtureOdds.class);
        apiResponse.getBody().forEach( fixtureOdd -> fixtureOdd.setFixtureId(fixtureId));
        return apiResponse;
    }
}
