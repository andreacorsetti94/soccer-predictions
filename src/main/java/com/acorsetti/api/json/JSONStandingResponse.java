package com.acorsetti.api.json;

import com.acorsetti.model.dto.StandingDto;
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
        List<List<Map<String,String>>> standingPositionsList = (List<List<Map<String, String>>>) standingsMap.get("standings");
        super.setDataList(new ArrayList<>());

        standingPositionsList.forEach( spList -> {
            spList.forEach( spMap -> {
                int rank = Integer.parseInt(spMap.get("rank"));
                String teamName = spMap.get("teamName");
                int played = Integer.parseInt(spMap.get("matchsPlayed"));
                int win = Integer.parseInt(spMap.get("win"));
                int draw = Integer.parseInt(spMap.get("draw"));
                int lose = Integer.parseInt(spMap.get("lose"));
                int goalFor = Integer.parseInt(spMap.get("goalsFor"));
                int goalAgainst = Integer.parseInt(spMap.get("goalsAgainst"));
                int points = Integer.parseInt(spMap.get("points"));
                String group = spMap.get("group");
                LocalDate lastUpd = LocalDate.parse(spMap.get("lastUpdate"));
                StandingDto standingDto = new StandingDto(null, rank, teamName, played, win,
                        draw, lose, goalFor, goalAgainst, goalFor-goalAgainst, points, group, lastUpd);
                super.getDataList().add(standingDto);
            });

        });
        super.setResults(super.getDataList().size());
    }
}
