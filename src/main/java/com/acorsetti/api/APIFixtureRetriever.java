package com.acorsetti.api;

import com.acorsetti.model.jpa.Fixture;

import java.time.LocalDate;

public interface APIFixtureRetriever {
    APIResponse<Fixture> byDay(LocalDate localDate);
    APIResponse<Fixture> live();
    APIResponse<Fixture> byLeague(String leagueId);
    APIResponse<Fixture> byTeam(String teamId);
    APIResponse<Fixture> byId(String id);
    APIResponse<Fixture> byH2H(String teamOne, String teamTwo);
}
