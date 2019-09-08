package com.acorsetti.api.impl;

import com.acorsetti.api.APIFixtureRetriever;
import com.acorsetti.api.APIResponse;
import com.acorsetti.api.RestTemplateService;
import com.acorsetti.api.json.JSONFixtureResponse;
import com.acorsetti.model.dto.FixtureDto;
import com.acorsetti.model.jpa.Fixture;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
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
    private RestTemplateService<JsonNode> restTemplateService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

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

        ResponseEntity<JsonNode> responseEntity;
        try{
            responseEntity = this.restTemplateService.makeGetRestCall(url, JsonNode.class);
        }
        catch (HttpServerErrorException ex){
            logger.error("HttpServerErrorException for this call: " + url + " Exception: " + ex.toString(),ex);
            return new APIResponse<>(ex.getStatusCode(), 0, Collections.emptyList());
        }
        catch ( Exception ex1 ){
            logger.error("Generic Exception for this call: " + url + " Exception: " + ex1.toString(),ex1);
            return new APIResponse<>(HttpStatus.SERVICE_UNAVAILABLE, 0, Collections.emptyList());
        }
        HttpStatus statusCode = responseEntity.getStatusCode();
        if ( statusCode != HttpStatus.OK ){
            logger.warn("HttpStatus for this call: " + url + " was: " + responseEntity.getStatusCode());
            return new APIResponse<>(statusCode, 0, Collections.emptyList());
        }

        JsonNode body = responseEntity.getBody();
        if ( body == null ){
            logger.warn("For this call: " + url + " a null response entity body has been returned.");
            return new APIResponse<>(statusCode, 0, Collections.emptyList());
        }

        JSONFixtureResponse jsonFixtureResponse = this.objectMapper.convertValue(body, JSONFixtureResponse.class);
        List<FixtureDto> fixtureDtoList = jsonFixtureResponse.getFixtureDtoList();
        int results = jsonFixtureResponse.getResults();
        Type type = new TypeToken<List<Fixture>>(){}.getType();

        List<Fixture> fixtures = this.modelMapper.map(fixtureDtoList, type);
        return new APIResponse<>(statusCode, results, fixtures);
    }
}
