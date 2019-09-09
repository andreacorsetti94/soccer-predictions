package com.acorsetti.api;

import com.acorsetti.model.jpa.StandingPosition;

public interface APIStandingsRetriever {
    APIResponse<StandingPosition> standingsByLeague(String leagueId);
}
