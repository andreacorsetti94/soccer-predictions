package com.acorsetti.core.api.json;

import com.acorsetti.core.model.dto.SeasonDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONSeasonResponse extends JsonResponse<SeasonDto> {
    public JSONSeasonResponse() {
    }

    public JSONSeasonResponse(int results, List<SeasonDto> dataList) {
        super(results, dataList);
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpackNested(Map<String,Object> seasonMap){
        Map<String,String> values = (Map<String, String>) seasonMap.get("seasons");

        super.setDataList(new ArrayList<>());
        values.forEach( (key,val) -> {
            super.getDataList().add(new SeasonDto(val));
        });
        super.setResults(super.getDataList().size());
    }
}
