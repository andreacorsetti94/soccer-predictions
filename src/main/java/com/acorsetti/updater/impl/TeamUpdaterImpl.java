package com.acorsetti.updater.impl;

import com.acorsetti.api.APITeamRetriever;
import com.acorsetti.model.jpa.League;
import com.acorsetti.model.jpa.LeagueTeam;
import com.acorsetti.model.jpa.Team;
import com.acorsetti.repository.LeagueRepository;
import com.acorsetti.repository.LeagueTeamRepository;
import com.acorsetti.repository.TeamRepository;
import com.acorsetti.updater.TeamUpdater;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class TeamUpdaterImpl implements TeamUpdater {

    private static final Logger logger = Logger.getLogger(TeamUpdaterImpl.class);

    @Autowired
    private LeagueTeamRepository leagueTeamRepository;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private APITeamRetriever apiTeamRetriever;

    @Autowired
    private LeagueRepository leagueRepository;

    @Override
    @Scheduled(cron = "${cron.updateTeams}")
    public void updateAllTeams() {
        List<League> leagueList = this.leagueRepository.findAll();

        List<LeagueTeam> allLeagueTeams = new ArrayList<>();
        List<Team> teamList = new ArrayList<>();
        leagueList.forEach( league -> {
            List<LeagueTeam> leagueTeams = this.apiTeamRetriever.byLeagueId(league.getLeagueId()).getBody();
            logger.info("Teams Update: League-Teams retrieved: " + leagueTeams);
            allLeagueTeams.addAll(leagueTeams);
            leagueTeams.forEach( leagueTeam -> {
                String teamId = leagueTeam.getTeamId();
                Team team = this.teamRepository.findByTeamId(teamId);
                if ( team == null ){
                    List<Team> apiTeams = this.apiTeamRetriever.byId(teamId).getBody();
                    teamList.addAll(apiTeams);
                }
                else teamList.add(team);
            });
        });
        this.leagueTeamRepository.saveAll(allLeagueTeams);
        logger.info("Teams Update: League-Teams retrieved saved. ");
        this.teamRepository.saveAll(teamList);
        logger.info("Teams Update: Teams retrieved saved. ");
    }

}
