package com.acorsetti.api.response;

import org.springframework.http.HttpStatus;

import java.util.List;

public abstract class APIResponse<T> {
    private int results;
    private HttpStatus response;

    APIResponse(HttpStatus response, int results) {
        this.response = response;
        this.results = results;
    }

    public HttpStatus getResponse() {
        return response;
    }
    public int getResults() {
        return results;
    }
    public abstract List<T> getBody();
}
