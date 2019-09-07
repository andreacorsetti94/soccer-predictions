package com.acorsetti.api.json;

import com.acorsetti.model.dto.CountryDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONCountryResponse {

    private int results;
    private List<CountryDto> countryDtos;

    public JSONCountryResponse(){}

    public JSONCountryResponse(int results, List<CountryDto> countryDtos) {
        this.results = results;
        this.countryDtos = countryDtos;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public void setCountryDtos(List<CountryDto> countryDtos) {
        this.countryDtos = countryDtos;
    }

    public int getResults() {
        return results;
    }

    public List<CountryDto> getCountryDtos() {
        return countryDtos;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpackNested(Map<String,Object> countryMap) {
        Map<String,String> countries = (Map<String,String>) countryMap.get("countries");
        this.countryDtos = new ArrayList<>();
        countries.forEach( (k,v) -> this.countryDtos.add(new CountryDto(k,v)) );
        this.results = this.countryDtos.size();
    }
}
