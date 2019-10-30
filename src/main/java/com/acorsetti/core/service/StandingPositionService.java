package com.acorsetti.core.service;

import com.acorsetti.core.model.jpa.StandingPosition;

import java.util.List;

public interface StandingPositionService {

    List<StandingPosition> byLeague(String leagueId);

    StandingPosition getStandingPositionByRank(String leagueId, int rank);

    StandingPosition getStandingPositionByTeamName(String leagueId, String teamName);
}
