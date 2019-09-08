package com.acorsetti.api;

import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Represents the remote response containing the number of elements of type E returned
 * @param <E>, the entity type of the elements obtained remotely (i.e. Fixture, Country, etc.)
 */
public class APIResponse<E> {

    private int results;
    private HttpStatus response;
    private List<E> entityList;

    public APIResponse(HttpStatus response, int results, List<E> entityList) {
        this.response = response;
        this.results = results;
        this.entityList = entityList;
    }

    public HttpStatus getResponse() {
        return response;
    }
    public int getResults() {
        return results;
    }
    public List<E> getBody(){ return entityList; }
}
