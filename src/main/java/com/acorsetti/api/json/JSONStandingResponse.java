package com.acorsetti.api.json;

import com.acorsetti.model.dto.StandingDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
                    int position = Integer.parseInt(standingPositionMap.get("rank"));
                    String teamName = standingPositionMap.get("teamName");
                    int played = Integer.parseInt(standingPositionMap.get("matchsPlayed"));
                    int win = Integer.parseInt(standingPositionMap.get("win"));
                    int draw = Integer.parseInt(standingPositionMap.get("draw"));
                    int lose = Integer.parseInt(standingPositionMap.get("lose"));
                    int goalFor = Integer.parseInt(standingPositionMap.get("goalsFor"));
                    int goalAgainst = Integer.parseInt(standingPositionMap.get("goalsAgainst"));
                    int points = Integer.parseInt(standingPositionMap.get("points"));
                    String groupName = standingPositionMap.get("group");
                    LocalDate lastUpd = LocalDate.parse(standingPositionMap.get("lastUpdate"));
                    StandingDto standingDto = new StandingDto(null, position, teamName, played, win,
                            draw, lose, goalFor, goalAgainst, goalFor-goalAgainst, points, groupName, lastUpd);
                    super.getDataList().add(standingDto);
                });
            });
        }
        else if( standingObj instanceof Map ){
            System.out.println("ATTTTTTTTTTTTTTTTTTT: " + standingObj.toString());
        }
        else{
            System.out.println("boh, class: " + standingObj.getClass().getSimpleName());
        }

    }
}
