package com.acorsetti.core.api.impl;

import com.acorsetti.core.api.APILiveMatchStatRetriever;
import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.api.RemoteDataRetriever;
import com.acorsetti.core.api.json.JSONLiveMatchStatsResponse;
import com.acorsetti.core.live.MatchStatistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
public class APILiveMatchStatRetrieverImpl implements APILiveMatchStatRetriever {

    @Autowired
    private Environment env;

    @Autowired
    private RemoteDataRetriever<MatchStatistics, JSONLiveMatchStatsResponse, MatchStatistics> remoteDataRetriever;

    @Override
    public APIResponse<MatchStatistics> statsByFixtureId(String fixtureId) {
        String url = this.env.getProperty("liveStats").replace("<fixtureId>",fixtureId);
        APIResponse<MatchStatistics> apiResponse = this.remoteDataRetriever.retrieve(url, JSONLiveMatchStatsResponse.class, MatchStatistics.class);
        apiResponse.getBody().forEach( ms -> ms.setFixtureId(fixtureId));
        return apiResponse;
    }
}
