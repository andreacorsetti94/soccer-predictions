package com.acorsetti.core.updater.impl;

import com.acorsetti.core.api.APIStandingsRetriever;
import com.acorsetti.core.model.jpa.League;
import com.acorsetti.core.model.jpa.StandingPosition;
import com.acorsetti.core.repository.LeagueRepository;
import com.acorsetti.core.repository.StandingPositionRepository;
import com.acorsetti.core.updater.StandingUpdater;
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

        for(int i = 0; i < leagueList.size(); i++){
            League league = leagueList.get(i);
            List<StandingPosition> standingPositions = this.apiStandingsRetriever.standingsByLeague(league.getLeagueId()).getBody();
            allStandings.addAll(standingPositions);
            logger.info("League updated. Progress: " + i + " / " + leagueList.size());
        }

        leagueList.forEach( league -> {

        });

        this.standingPositionRepository.saveAll(allStandings);
        logger.info("Standing position update complete");
    }
}
