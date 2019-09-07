package com.acorsetti.api.impl;

import com.acorsetti.api.APICountryRetriever;
import com.acorsetti.api.json.JSONCountryResponse;
import com.acorsetti.api.response.CountryResponse;
import com.acorsetti.model.dto.CountryDto;
import com.acorsetti.model.jpa.Country;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;

@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
public class APICountryRetrieverImpl implements APICountryRetriever {

    private static final Logger logger = Logger.getLogger(APICountryRetrieverImpl.class);

    @Autowired
    private HttpEntity<String> httpEntity;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private Environment env;

    @Override
    public CountryResponse allCountriesByAPI() {

        String url = env.getProperty("allCountries");
        ResponseEntity<JsonNode> responseEntity;
        try{
            responseEntity = this.responseEntity(restTemplate, httpEntity, url);
        }
        catch (HttpServerErrorException ex){
            logger.error("HttpServerErrorException for this call: " + url + " Exception: " + ex.toString(),ex);
            return new CountryResponse(ex.getStatusCode(), 0, Collections.emptyList());
        }
        catch ( Exception ex1 ){
            logger.error("Generic Exception for this call: " + url + " Exception: " + ex1.toString(),ex1);
            return new CountryResponse(HttpStatus.SERVICE_UNAVAILABLE, 0, Collections.emptyList());
        }

        HttpStatus statusCode = responseEntity.getStatusCode();
        if ( statusCode != HttpStatus.OK ){
            logger.warn("HttpStatus for this call: " + url + " was: " + responseEntity.getStatusCode());
            return new CountryResponse(statusCode, 0, Collections.emptyList());
        }

        JsonNode body = responseEntity.getBody();
        if ( body == null ){
            logger.warn("For this call: " + url + " a null response entity body has been returned.");
            return new CountryResponse(statusCode, 0, Collections.emptyList());
        }

        //map JsonNode instance to concrete json response entity
        JSONCountryResponse jsonCountryResponse = objectMapper.convertValue(body, JSONCountryResponse.class);
        List<CountryDto> dtoCountries = jsonCountryResponse.getCountryDtos();
        Type countryType = new TypeToken<List<Country>>(){}.getType();

        //map json response entity to model
        List<Country> countryList = modelMapper.map(dtoCountries, countryType);
        return new CountryResponse(statusCode, jsonCountryResponse.getResults(), countryList);
    }
}
