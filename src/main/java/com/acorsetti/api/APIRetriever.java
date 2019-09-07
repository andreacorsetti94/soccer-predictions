package com.acorsetti.api;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public interface APIRetriever {
    default ResponseEntity<JsonNode> responseEntity(RestTemplate restTemplate,
                                             HttpEntity<String> httpEntity, String url){
        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, JsonNode.class );
    }
}
