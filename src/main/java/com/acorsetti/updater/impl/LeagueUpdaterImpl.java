package com.acorsetti.updater.impl;

import com.acorsetti.api.APILeagueRetriever;
import com.acorsetti.model.jpa.League;
import com.acorsetti.repository.LeagueRepository;
import com.acorsetti.updater.LeagueUpdater;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class LeagueUpdaterImpl implements LeagueUpdater {
    private static final Logger logger = Logger.getLogger(LeagueUpdaterImpl.class);

    @Autowired
    private LeagueRepository leagueRepository;

    @Autowired
    private APILeagueRetriever apiLeagueRetriever;

    @Override
    @Scheduled(cron = "${cron.updateLeagues}")
    public void updateLeagues() {
        List<League> leagues = this.apiLeagueRetriever.allLeagues().getBody();
        logger.info("League Update. Leagues retrieved: " + leagues);
        this.leagueRepository.saveAll(leagues);
        logger.info("Leagues retrieved saved.");
    }
}
