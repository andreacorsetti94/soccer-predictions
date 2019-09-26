package com.acorsetti.core.api;

import com.acorsetti.core.model.jpa.League;

public interface APILeagueRetriever {
    APIResponse<League> allLeagues();
    APIResponse<League> byId(String id);
    APIResponse<League> byYear(String year);
    APIResponse<League> byCountry(String country);
    APIResponse<League> byCountryAndYear(String country, String year);
}
