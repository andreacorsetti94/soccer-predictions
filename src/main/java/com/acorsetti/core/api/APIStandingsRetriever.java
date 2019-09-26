package com.acorsetti.core.api;

import com.acorsetti.core.model.jpa.StandingPosition;

public interface APIStandingsRetriever {
    APIResponse<StandingPosition> standingsByLeague(String leagueId);
}
