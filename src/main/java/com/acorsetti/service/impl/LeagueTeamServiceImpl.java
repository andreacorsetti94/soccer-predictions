package com.acorsetti.service.impl;

import com.acorsetti.model.Team;
import com.acorsetti.repository.LeagueTeamRepository;
import com.acorsetti.service.LeagueTeamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LeagueTeamServiceImpl implements LeagueTeamService {

    @Autowired
    private LeagueTeamRepository leagueTeamRepository;

    @Override
    public List<Team> teamsInLeague(String leagueId) {
        return this.leagueTeamRepository.findByLeagueId(leagueId);
    }
}
