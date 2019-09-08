package com.acorsetti.api.impl;

import com.acorsetti.api.APIFixtureRetriever;
import com.acorsetti.api.APIResponse;
import com.acorsetti.api.RemoteDataRetriever;
import com.acorsetti.api.json.JSONFixtureResponse;
import com.acorsetti.model.dto.FixtureDto;
import com.acorsetti.model.jpa.Fixture;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Objects;

@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
@PropertySource("classpath:application.properties")
public class APIFixtureRetrieverImpl implements APIFixtureRetriever {
    private static final Logger logger = Logger.getLogger(APICountryRetrieverImpl.class);

    @Autowired
    private Environment environment;

    @Autowired
    private RemoteDataRetriever<Fixture, JSONFixtureResponse, FixtureDto> remoteDataRetriever;

    @Override
    public APIResponse<Fixture> fixturesByDay(LocalDate localDate) {
        String url = this.environment.getProperty("fixturesByDay");
        String format = this.environment.getProperty("fixture.date.format");
        if ( format == null ){
            logger.error("couldnt read application property: fixture.date.format. Cannot proceed.");
            return new APIResponse<>(HttpStatus.SERVICE_UNAVAILABLE, 0, Collections.emptyList());
        }

        String date = localDate.format(DateTimeFormatter.ofPattern(format));
        url = Objects.requireNonNull(url).replace("<date>", date);
        return this.remoteDataRetriever.retrieve(url, JSONFixtureResponse.class, Fixture.class);
    }
}
