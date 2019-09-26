package com.acorsetti.core.api.impl;

import com.acorsetti.core.api.APICountryRetriever;
import com.acorsetti.core.api.RemoteDataRetriever;
import com.acorsetti.core.api.json.JSONCountryResponse;
import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.model.dto.CountryDto;
import com.acorsetti.core.model.jpa.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
public class APICountryRetrieverImpl implements APICountryRetriever {

    @Autowired
    private Environment env;

    @Autowired
    private RemoteDataRetriever<Country, JSONCountryResponse, CountryDto> remoteDataRetriever;

    @Override
    public APIResponse<Country> allCountries() {
        String url = env.getProperty("allCountries");
        return this.remoteDataRetriever.retrieve(url, JSONCountryResponse.class, Country.class);
    }
}
