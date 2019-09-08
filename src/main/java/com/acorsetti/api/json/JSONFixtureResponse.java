package com.acorsetti.api.json;

import com.acorsetti.model.dto.FixtureDto;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONFixtureResponse {
    private int results;
    private List<FixtureDto> fixtureDtoList;

    public JSONFixtureResponse(int results, List<FixtureDto> fixtureDtoList) {
        this.results = results;
        this.fixtureDtoList = fixtureDtoList;
    }

    public JSONFixtureResponse() {
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public List<FixtureDto> getFixtureDtoList() {
        return fixtureDtoList;
    }

    public void setFixtureDtoList(List<FixtureDto> fixtureDtoList) {
        this.fixtureDtoList = fixtureDtoList;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpackNested(Map<String,Object> fixtureMap){
        Map<String,Object> fixturesKeys = (Map<String,Object>) fixtureMap.get("fixtures");

        this.fixtureDtoList = new ArrayList<>();
        fixturesKeys.forEach( (key, fixture) -> {
            Map<String,String> fixtureProperties = (Map<String,String>) fixture;
            String id = fixtureProperties.get("fixture_id");
            String event_timestamp = fixtureProperties.get("event_timestamp");
            String league_id = fixtureProperties.get("league_id");
            String round = fixtureProperties.get("round");
            String homeTeam_id = fixtureProperties.get("homeTeam_id");
            String awayTeam_id = fixtureProperties.get("awayTeam_id");
            String homeTeam = fixtureProperties.get("homeTeam");
            String awayTeam = fixtureProperties.get("awayTeam");
            String status = fixtureProperties.get("status");
            String statusShort = fixtureProperties.get("statusShort");
            String goalsHomeTeam = fixtureProperties.get("goalsHomeTeam");
            String goalsAwayTeam = fixtureProperties.get("goalsAwayTeam");
            String halftime_score = fixtureProperties.get("halftime_score");
            String final_score = fixtureProperties.get("final_score");
            String penalty = fixtureProperties.get("penalty");
            String elapsed = fixtureProperties.get("elapsed");
            String firstHalfStart = fixtureProperties.get("firstHalfStart");
            String secondHalfStart = fixtureProperties.get("secondHalfStart");

            String event_date = fixtureProperties.get("event_date");
            
            //TODO configura env
            LocalDateTime dateTime = LocalDateTime.parse(event_date.substring(0,19));
            this.fixtureDtoList.add(new FixtureDto(id,event_timestamp, dateTime, league_id, round, homeTeam_id,
                    awayTeam_id, homeTeam, awayTeam, status, statusShort, goalsHomeTeam, goalsAwayTeam, halftime_score,
                    final_score, penalty, elapsed, firstHalfStart, secondHalfStart));
        });
        this.results = this.fixtureDtoList.size();
    }
}
