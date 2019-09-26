package com.acorsetti.core.api.json;

import java.util.List;

/**
 * Represents the json response containing dto elements
 * @param <D> DTO type (i.e. FixtureDTO, CountryDTO, etc.)
 */
public class JsonResponse<D> {
    private int results;
    private List<D> dataList;

    public JsonResponse() {
    }

    public JsonResponse(int results, List<D> dataList) {
        this.results = results;
        this.dataList = dataList;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public List<D> getDataList() {
        return dataList;
    }

    public void setDataList(List<D> dataList) {
        this.dataList = dataList;
    }
}
