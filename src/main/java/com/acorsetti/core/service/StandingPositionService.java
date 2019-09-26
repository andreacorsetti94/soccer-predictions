package com.acorsetti.core.service;

import com.acorsetti.core.model.jpa.StandingPosition;

import java.util.List;

public interface StandingPositionService {

    List<StandingPosition> byLeague(String leagueId);
}
