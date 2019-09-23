package com.acorsetti.api.json;

import com.acorsetti.model.dto.TeamStatisticsDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONTeamStatisticsResponse extends JsonResponse<TeamStatisticsDto> {
    public JSONTeamStatisticsResponse() {
    }

    public JSONTeamStatisticsResponse(int results, List<TeamStatisticsDto> dataList) {
        super(results, dataList);
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpackNested(Map<String,Object> map){
        Map<String,Object> statsMap = (Map<String, Object>) map.get("statistics");
        Map<String,Object> matchsMap = (Map<String, Object>) statsMap.get("matchs");
        Map<String,Integer> matchsPlayed = (Map<String, Integer>) matchsMap.get("matchsPlayed");
        int homePlayed = matchsPlayed.get("home");
        int awayPlayed = matchsPlayed.get("away");
        int totalPlayed = matchsPlayed.get("total");

        Map<String,Integer> wins = (Map<String, Integer>) matchsMap.get("wins");
        int homeWins = wins.get("home");
        int awayWins = wins.get("away");
        int totalWins =wins.get("total");

        Map<String,Integer> draws = (Map<String, Integer>) matchsMap.get("draws");
        int homeDraws = draws.get("home");
        int awayDraws = draws.get("away");
        int totalDraws = draws.get("total");

        Map<String,Integer> loses = (Map<String, Integer>) matchsMap.get("loses");
        int homeLoses = loses.get("home");
        int awayLoses = loses.get("away");
        int totalLoses =loses.get("total");

        Map<String,Object> goalsMap = (Map<String, Object>) statsMap.get("goals");
        Map<String,Integer> goalsForMap = (Map<String, Integer>) goalsMap.get("goalsFor");
        Map<String,Integer> goalsAgainstMap = (Map<String, Integer>) goalsMap.get("goalsAgainst");
        int goalsForHome = goalsForMap.get("home");
        int goalsForAway = goalsForMap.get("away");
        int goalsForTotal = goalsForMap.get("total");

        int goalsAgainstHome = goalsAgainstMap.get("home");
        int goalsAgainstAway = goalsAgainstMap.get("away");
        int goalsAgainstTotal = goalsAgainstMap.get("total");

        Map<String,Object> goalsAvgMap = (Map<String, Object>) statsMap.get("goalsAvg");
        Map<String,String> avgGoalsForMap = (Map<String, String>) goalsAvgMap.get("goalsFor");
        Map<String,String> avgGoalsAgainstMap = (Map<String, String>) goalsAvgMap.get("goalsAgainst");

        double avgGoalsForHome = Double.parseDouble(avgGoalsForMap.get("home"));
        double avgGoalsForAway = Double.parseDouble(avgGoalsForMap.get("away"));
        double avgGoalsForTotal = Double.parseDouble(avgGoalsForMap.get("total"));

        double avgGoalsAgainstHome = Double.parseDouble(avgGoalsAgainstMap.get("home"));
        double avgGoalsAgainstAway = Double.parseDouble(avgGoalsAgainstMap.get("away"));
        double avgGoalsAgainstTotal = Double.parseDouble(avgGoalsAgainstMap.get("total"));

        super.setDataList(new ArrayList<>());
        TeamStatisticsDto teamStatisticsDto = new TeamStatisticsDto(totalPlayed, homePlayed, awayPlayed, totalWins, homeWins, awayWins, totalDraws, homeDraws, awayDraws, totalLoses, homeLoses, awayLoses, goalsForHome, goalsForAway, goalsForTotal, goalsAgainstHome, goalsAgainstAway, goalsAgainstTotal, avgGoalsForHome, avgGoalsForAway, avgGoalsForTotal, avgGoalsAgainstHome, avgGoalsAgainstAway, avgGoalsAgainstTotal);
        super.getDataList().add(teamStatisticsDto);
        super.setResults(super.getDataList().size());
    }

}
