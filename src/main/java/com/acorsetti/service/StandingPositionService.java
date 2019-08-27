package com.acorsetti.service;

import com.acorsetti.model.jpa.StandingPosition;

import java.util.List;

public interface StandingPositionService {

    List<StandingPosition> byLeague(String leagueId);
}
