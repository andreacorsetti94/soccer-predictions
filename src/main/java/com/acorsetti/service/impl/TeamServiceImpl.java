package com.acorsetti.service.impl;

import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.model.jpa.Team;
import com.acorsetti.repository.TeamRepository;
import com.acorsetti.service.FixtureService;
import com.acorsetti.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private FixtureService fixtureService;

    public List<Team> listAllTeams(){
        List<Team> teams = new ArrayList<>();
        this.teamRepository.findAll().forEach(teams::add);
        return teams;
    }

    public Team byId(String id){
        return this.teamRepository.findByTeamId(id);
    }

    @Override
    public double avgGoalsScored(String teamId, List<Fixture> fixtures) {
        int fixturesWithTeamId = 0;

        int goalsScored = 0;
        for(Fixture fixture: fixtures){
            if ( fixture.getHomeTeamId().equals(teamId) || fixture.getAwayTeamId().equals(teamId) ) {
                goalsScored += this.fixtureService.getTeamGoalsFor(fixture, teamId);
                fixturesWithTeamId++;
            }
        }

        if ( fixturesWithTeamId == 0 ) return 0.0;
        return (double) goalsScored/ (double) fixturesWithTeamId;
    }

    @Override
    public double avgGoalsConceived(String teamId, List<Fixture> fixtures) {
        int fixturesWithTeamId = 0;

        int goalsConceived = 0;
        for(Fixture fixture: fixtures){
            if ( fixture.getHomeTeamId().equals(teamId) || fixture.getAwayTeamId().equals(teamId) ){
                goalsConceived += this.fixtureService.getTeamGoalsConceived(fixture,teamId);
                fixturesWithTeamId++;
            }
        }
        if ( fixturesWithTeamId == 0 ) return 0.0;
        return (double) goalsConceived/ (double) fixturesWithTeamId;
    }
}
