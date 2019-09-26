package com.acorsetti.core.service;

import com.acorsetti.core.model.jpa.Team;

import java.util.List;

public interface LeagueTeamService {

    List<Team> teamsInLeague(String leagueId);
}
