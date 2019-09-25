package com.acorsetti.updater.impl;

import com.acorsetti.api.APIStandingsRetriever;
import com.acorsetti.model.jpa.League;
import com.acorsetti.model.jpa.StandingPosition;
import com.acorsetti.repository.LeagueRepository;
import com.acorsetti.repository.StandingPositionRepository;
import com.acorsetti.updater.StandingUpdater;
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
public class StandingUpdaterImpl implements StandingUpdater {
    private static final Logger logger = Logger.getLogger(StandingUpdaterImpl.class);

    @Autowired
    private StandingPositionRepository standingPositionRepository;

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private APIStandingsRetriever apiStandingsRetriever;

    @Override
    @Scheduled(cron = "${cron.standingsUpdate}")
    public void updateAllStandings() {
        logger.info("Standing position update started...");
        List<League> leagueList = this.leagueRepository.findAll();
        List<StandingPosition> allStandings = new ArrayList<>();
        leagueList.forEach( league -> {
            List<StandingPosition> standingPositions = this.apiStandingsRetriever.standingsByLeague(league.getLeagueId()).getBody();
            allStandings.addAll(standingPositions);
        });

        this.standingPositionRepository.saveAll(allStandings);
        logger.info("Standing position update complete");
    }
}
