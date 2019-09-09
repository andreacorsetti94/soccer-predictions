package com.acorsetti.api.impl;

import com.acorsetti.api.APILeagueRetriever;
import com.acorsetti.api.APIResponse;
import com.acorsetti.api.RemoteDataRetriever;
import com.acorsetti.api.json.JSONLeagueResponse;
import com.acorsetti.model.dto.LeagueDto;
import com.acorsetti.model.jpa.League;
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
public class APILeagueRetrieverImpl implements APILeagueRetriever {

    private static final Logger logger = Logger.getLogger(APILeagueRetrieverImpl.class);

    @Autowired
    private Environment environment;

    @Autowired
    private RemoteDataRetriever<League, JSONLeagueResponse, LeagueDto> remoteDataRetriever;

    @Override
    public APIResponse<League> allLeagues() {
        String endpoint = this.environment.getProperty("allLeagues");
        return this.remoteDataRetriever.retrieve(endpoint, JSONLeagueResponse.class, League.class);
    }

    @Override
    public APIResponse<League> byId(String id) {
        String endpoint = this.environment.getProperty("leaguesById").replace("<leagueId>", id);
        return this.remoteDataRetriever.retrieve(endpoint, JSONLeagueResponse.class, League.class);

    }

    @Override
    public APIResponse<League> byYear(String year) {
        String endpoint = this.environment.getProperty("leaguesByYear").replace("<year>", year);
        return this.remoteDataRetriever.retrieve(endpoint, JSONLeagueResponse.class, League.class);
    }

    @Override
    public APIResponse<League> byCountry(String country) {
        String endpoint = this.environment.getProperty("leaguesByCountry").replace("<country>", country);
        return this.remoteDataRetriever.retrieve(endpoint, JSONLeagueResponse.class, League.class);
    }

    @Override
    public APIResponse<League> byCountryAndYear(String country, String year) {
        String endpoint = this.environment.getProperty("leaguesByCountryAndYear")
                .replace("<country>", country)
                .replace("<year>", year);
        return this.remoteDataRetriever.retrieve(endpoint, JSONLeagueResponse.class, League.class);
    }
}
