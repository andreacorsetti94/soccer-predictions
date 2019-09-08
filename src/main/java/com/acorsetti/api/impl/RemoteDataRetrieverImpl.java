package com.acorsetti.api.impl;

import com.acorsetti.api.APIResponse;
import com.acorsetti.api.RemoteDataRetriever;
import com.acorsetti.api.json.JsonResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Retrieves data from a remote endpoint.
 * @param <E>, represents the entity type (Fixture, Country, etc.)
 * @param <R>, represents the specific jsonResponse type (JSONCountryResponse, etc.)
 * @param <D>, represents the DTO type (FixtureDTO, etc.)
 */
@Service
public class RemoteDataRetrieverImpl<E,R extends JsonResponse, D> implements RemoteDataRetriever<E,R,D> {

    private static final Logger logger = Logger.getLogger(APICountryRetrieverImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpEntity<String> httpEntity;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @SuppressWarnings("unchecked")
    public APIResponse<E> retrieve(String endPoint, Class<R> jsonResponseClass, Class<E> modelClass){

        ResponseEntity<JsonNode> responseEntity;
        try{
            responseEntity = this.restTemplate.exchange(endPoint, HttpMethod.GET, httpEntity, JsonNode.class);
        }
        catch (HttpServerErrorException ex){
            logger.error("HttpServerErrorException for this call: " + endPoint + " Exception: " + ex.toString(),ex);
            return new APIResponse<>(ex.getStatusCode(), 0, Collections.emptyList());
        }
        catch ( Exception ex1 ){
            logger.error("Generic Exception for this call: " + endPoint + " Exception: " + ex1.toString(),ex1);
            return new APIResponse<>(HttpStatus.SERVICE_UNAVAILABLE, 0, Collections.emptyList());
        }

        HttpStatus statusCode = responseEntity.getStatusCode();
        if ( statusCode != HttpStatus.OK ){
            logger.warn("HttpStatus for this call: " + endPoint + " was: " + responseEntity.getStatusCode());
            return new APIResponse<>(statusCode, 0, Collections.emptyList());
        }

        JsonNode body = responseEntity.getBody();
        if ( body == null ){
            logger.warn("For this call: " + endPoint + " a null response entity body has been returned.");
            return new APIResponse<>(statusCode, 0, Collections.emptyList());
        }

        //map JsonNode instance to concrete json response entity
        R jsonResponse = objectMapper.convertValue(body, jsonResponseClass);
        List<D> dtoList = jsonResponse.getDataList();

        //map json response entity to model
        List<E> entityList = new ArrayList<>();
        dtoList.forEach( dto -> entityList.add( this.modelMapper.map(dto, modelClass)));

        return new APIResponse<>(statusCode, jsonResponse.getResults(), entityList);
    }
}
