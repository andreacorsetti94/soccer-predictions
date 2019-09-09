package com.acorsetti.api;

import com.acorsetti.model.jpa.League;

public interface APILeagueRetriever {
    APIResponse<League> allLeagues();
    APIResponse<League> byId(String id);
    APIResponse<League> byYear(String year);
    APIResponse<League> byCountry(String country);
    APIResponse<League> byCountryAndYear(String country, String year);
}
