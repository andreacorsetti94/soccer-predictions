package com.acorsetti.core.newodds;

import com.acorsetti.core.api.APIResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Service
public class APIOddsRetriever {
    private static final Logger logger = Logger.getLogger(APIOddsRetriever.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpEntity<String> httpEntity;

    @Autowired
    private ObjectMapper objectMapper;

    public APIResponse<BookmakerOdds> retrieve(String endpoint){

        ResponseEntity<JsonNode> responseEntity;
        try{
            responseEntity = this.restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity, JsonNode.class);
        }
        catch (HttpServerErrorException ex){
            logger.error("HttpServerErrorException for this call: " + endpoint + " Exception: " + ex.toString(),ex);
            return new APIResponse<>(ex.getStatusCode(), 0, Collections.emptyList());
        }
        catch ( Exception ex1 ){
            logger.error("Generic Exception for this call: " + endpoint + " Exception: " + ex1.toString(),ex1);
            return new APIResponse<>(HttpStatus.SERVICE_UNAVAILABLE, 0, Collections.emptyList());
        }

        HttpStatus statusCode = responseEntity.getStatusCode();
        if ( statusCode != HttpStatus.OK ){
            logger.warn("HttpStatus for this call: " + endpoint + " was: " + responseEntity.getStatusCode());
            return new APIResponse<>(statusCode, 0, Collections.emptyList());
        }

        JsonNode body = responseEntity.getBody();
        if ( body == null ){
            logger.warn("For this call: " + endpoint + " a null response entity body has been returned.");
            return new APIResponse<>(statusCode, 0, Collections.emptyList());
        }
        BookmakerOddsJsonNode bookmakerOddsJsonNode = this.objectMapper.convertValue(body, BookmakerOddsJsonNode.class);
        List<BookmakerOdds> bookmakerOddsList = bookmakerOddsJsonNode.getBookmakerOdds();
        return new APIResponse<>(HttpStatus.OK, bookmakerOddsList.size(), bookmakerOddsList);
    }
}
