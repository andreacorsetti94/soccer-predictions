package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.jpa.LeagueTeam;
import com.acorsetti.core.model.jpa.Team;
import com.acorsetti.core.repository.LeagueTeamRepository;
import com.acorsetti.core.service.LeagueTeamService;
import com.acorsetti.core.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LeagueTeamServiceImpl implements LeagueTeamService {

    @Autowired
    private LeagueTeamRepository leagueTeamRepository;

    @Autowired
    private TeamService teamService;

    @Override
    public List<Team> teamsInLeague(String leagueId) {
        List<LeagueTeam> leagueTeams = this.leagueTeamRepository.findByLeagueId(leagueId);

        List<Team> teams = new ArrayList<>();
        leagueTeams.forEach(leagueTeam -> {
            teams.add(this.teamService.byId(leagueTeam.getTeamId()));
        });
        return teams;
    }
}
