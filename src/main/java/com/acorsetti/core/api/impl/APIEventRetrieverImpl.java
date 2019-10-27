package com.acorsetti.core.api.impl;

import com.acorsetti.core.api.APIEventRetriever;
import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.api.RemoteDataRetriever;
import com.acorsetti.core.api.json.JSONEventResponse;
import com.acorsetti.core.model.dto.EventDto;
import com.acorsetti.core.model.jpa.Event;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
@PropertySource("classpath:application.properties")
public class APIEventRetrieverImpl implements APIEventRetriever {

    @Autowired
    private Environment environment;

    @Autowired
    private RemoteDataRetriever<Event, JSONEventResponse, EventDto> remoteDataRetriever;

    @Override
    public APIResponse<Event> byFixtureId(String fixtureId) {
        String endpoint = this.environment.getProperty("eventsByFixture");
        endpoint = Objects.requireNonNull(endpoint).replace("<fixtureId>", fixtureId);
        APIResponse<Event> apiResponse = this.remoteDataRetriever.retrieve(endpoint, JSONEventResponse.class, Event.class);
        List<Event> eventList = apiResponse.getBody();
        eventList.forEach( event -> event.setFixtureId(fixtureId) );
        return apiResponse;
    }
}
