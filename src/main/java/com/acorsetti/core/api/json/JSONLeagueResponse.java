package com.acorsetti.core.api.json;

import com.acorsetti.core.model.dto.LeagueDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONLeagueResponse extends JsonResponse<LeagueDto> {
    public JSONLeagueResponse() {
    }

    public JSONLeagueResponse(int results, List<LeagueDto> dataList) {
        super(results, dataList);
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpackNested(Map<String,Object> leaguesMap){
        Map<String,Object> leagueKeys = (Map<String,Object>) leaguesMap.get("leagues");

        super.setDataList(new ArrayList<>());
        leagueKeys.forEach( (key, league) -> {
            Map<String,Object> leagueProperties = (Map<String,Object>) league;
            String id = (String) leagueProperties.get("league_id");
            String name = (String) leagueProperties.get("name");
            String country = (String) leagueProperties.get("country");
            String country_code = (String) leagueProperties.get("country_code");
            String season = (String) leagueProperties.get("season");
            String season_start = (String) leagueProperties.get("season_start");
            String season_end = (String) leagueProperties.get("season_end");
            String logo = (String) leagueProperties.get("logo");
            String flag = (String) leagueProperties.get("flag");
            boolean standings = (Boolean) leagueProperties.get("standings");

            LeagueDto leagueDto = new LeagueDto(id,name,country,country_code,season,season_start,
                    season_end,logo,flag, standings);
            super.getDataList().add(leagueDto);
        });
        super.setResults( super.getDataList().size() );
    }
}
