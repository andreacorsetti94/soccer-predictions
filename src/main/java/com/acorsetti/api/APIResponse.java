package com.acorsetti.api;

import org.springframework.http.HttpStatus;

import java.util.List;

public class APIResponse<T> {
    private int results;
    private HttpStatus response;
    private List<T> entityList;

    public APIResponse(HttpStatus response, int results, List<T> entityList) {
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
    public List<T> getBody(){ return entityList; }
}
