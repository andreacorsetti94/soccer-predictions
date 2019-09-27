package com.acorsetti.core.api.json;

import com.acorsetti.core.model.dto.StandingDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONStandingResponse extends JsonResponse<StandingDto> {

    public JSONStandingResponse() {
    }

    public JSONStandingResponse(int results, List<StandingDto> dataList) {
        super(results, dataList);
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpack(Map<String,Object> standingsMap){
        super.setDataList(new ArrayList<>());
        Object standingObj = standingsMap.get("standings");
        if ( standingObj instanceof List ){
            List<List> standingList = (List<List>) standingsMap.get("standings");
            standingList.forEach( spLeagueList -> {
                spLeagueList.forEach( spMap -> {
                    Map<String,String> standingPositionMap = (Map<String, String>) spMap;
                    this.extractStandingPositionFromMap(standingPositionMap);
                });
            });
        }
        else if( standingObj instanceof Map ){
            Map<String,List<Map<String,String>>> m = (Map<String,List<Map<String,String>>>) standingObj;
            m.keySet().forEach( key -> {
                List<Map<String,String>> list = m.get(key);
                list.forEach(this::extractStandingPositionFromMap);
            });
        }
        else{
            return;
        }
        super.setResults(super.getDataList().size());
    }

    private void extractStandingPositionFromMap(Map<String,String> spMap){
        int position = Integer.parseInt(spMap.get("rank"));
        String teamName = spMap.get("teamName");
        int played = Integer.parseInt(spMap.get("matchsPlayed"));
        int win = Integer.parseInt(spMap.get("win"));
        int draw = Integer.parseInt(spMap.get("draw"));
        int lose = Integer.parseInt(spMap.get("lose"));
        int goalFor = Integer.parseInt(spMap.get("goalsFor"));
        int goalAgainst = Integer.parseInt(spMap.get("goalsAgainst"));
        int points = Integer.parseInt(spMap.get("points"));
        String groupName = spMap.get("group");
        LocalDate lastUpd = LocalDate.parse(spMap.get("lastUpdate"));
        StandingDto standingDto = new StandingDto(null, position, teamName, played, win,
                draw, lose, goalFor, goalAgainst, goalFor-goalAgainst, points, groupName, lastUpd);
        super.getDataList().add(standingDto);
    }
}
