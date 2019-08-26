package com.acorsetti.service;

import com.acorsetti.model.StandingPosition;

import java.util.List;

public interface StandingPositionService {

    List<StandingPosition> byLeague(String leagueId);
}
