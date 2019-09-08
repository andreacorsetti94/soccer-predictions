package com.acorsetti.api.json;

import com.acorsetti.model.dto.CountryDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONCountryResponse extends JsonResponse<CountryDto>{

    public JSONCountryResponse(){}

    public JSONCountryResponse(int results, List<CountryDto> countryDtos) {
        super(results,countryDtos);
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpackNested(Map<String,Object> countryMap) {
        Map<String,String> countries = (Map<String,String>) countryMap.get("countries");
        super.setDataList(new ArrayList<>());
        countries.forEach( (k,v) -> super.getDataList().add(new CountryDto(k,v)) );
        super.setResults(super.getDataList().size());
    }
}
