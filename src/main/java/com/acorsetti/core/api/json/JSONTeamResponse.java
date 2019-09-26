package com.acorsetti.core.api.json;

import com.acorsetti.core.model.dto.TeamDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONTeamResponse extends JsonResponse<TeamDto> {
    public JSONTeamResponse() {
    }

    public JSONTeamResponse(int results, List<TeamDto> dataList) {
        super(results, dataList);
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpackNested(Map<String,Object> map){
        Map<String,Object> teamMap = (Map<String, Object>) map.get("teams");
        super.setDataList(new ArrayList<>());
        teamMap.forEach( (k,v) -> {
            Map<String,String> tMap = (Map<String, String>) v;
            String teamName = tMap.get("name");
            String teamId = tMap.get("team_id");
            String logoUrl = tMap.get("logo");
            TeamDto teamDto = new TeamDto(teamId, teamName, logoUrl);
            super.getDataList().add(teamDto);
        });
        super.setResults(super.getDataList().size());
    }
}
