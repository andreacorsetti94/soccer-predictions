package com.acorsetti.core.api.impl;

import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.api.APISeasonRetriever;
import com.acorsetti.core.api.RemoteDataRetriever;
import com.acorsetti.core.api.json.JSONSeasonResponse;
import com.acorsetti.core.model.dto.SeasonDto;
import com.acorsetti.core.model.jpa.Season;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
@PropertySource("classpath:application.properties")
public class APISeasonRetrieverImpl implements APISeasonRetriever {

    @Autowired
    private Environment environment;

    @Autowired
    private RemoteDataRetriever<Season, JSONSeasonResponse, SeasonDto> remoteDataRetriever;

    @Override
    public APIResponse<Season> allSeasons() {
        String endpoint = this.environment.getProperty("allSeasons");
        return this.remoteDataRetriever.retrieve(endpoint, JSONSeasonResponse.class, Season.class);
    }
}
