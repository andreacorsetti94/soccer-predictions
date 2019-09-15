package com.acorsetti.api.impl;

import com.acorsetti.api.APIResponse;
import com.acorsetti.api.APISeasonRetriever;
import com.acorsetti.api.RemoteDataRetriever;
import com.acorsetti.api.json.JSONSeasonResponse;
import com.acorsetti.model.dto.SeasonDto;
import com.acorsetti.model.jpa.Season;
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
