package com.acorsetti.core.updater.impl;

import com.acorsetti.core.api.APITeamRetriever;
import com.acorsetti.core.model.jpa.League;
import com.acorsetti.core.model.jpa.LeagueTeam;
import com.acorsetti.core.model.jpa.Team;
import com.acorsetti.core.repository.LeagueRepository;
import com.acorsetti.core.repository.LeagueTeamRepository;
import com.acorsetti.core.repository.TeamRepository;
import com.acorsetti.core.updater.TeamUpdater;
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

        for(int i = 0; i < leagueList.size(); i++){
            League league = leagueList.get(i);
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
            logger.info("Teams for league: " + league.getLeagueId() + " have been updated. Progress: " + i + " / " + leagueList.size());
        }

        this.leagueTeamRepository.saveAll(allLeagueTeams);
        logger.info("Teams Update: League-Teams retrieved saved. ");
        this.teamRepository.saveAll(teamList);
        logger.info("Teams Update: Teams retrieved saved. ");
    }

}
