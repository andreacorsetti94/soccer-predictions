package com.acorsetti.service;

import com.acorsetti.model.jpa.Team;

import java.util.List;

public interface LeagueTeamService {

    List<Team> teamsInLeague(String leagueId);
}
