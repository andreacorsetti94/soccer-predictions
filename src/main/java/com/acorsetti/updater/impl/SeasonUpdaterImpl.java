package com.acorsetti.updater.impl;

import com.acorsetti.api.APISeasonRetriever;
import com.acorsetti.model.jpa.Season;
import com.acorsetti.repository.SeasonRepository;
import com.acorsetti.updater.SeasonUpdater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class SeasonUpdaterImpl implements SeasonUpdater {

    @Autowired
    private APISeasonRetriever apiSeasonRetriever;

    @Autowired
    private SeasonRepository seasonRepository;

    @Override
    @Scheduled(cron = "${cron.seasonUpdate}")
    public void updateSeasons() {
        List<Season> seasonList = this.apiSeasonRetriever.allSeasons().getBody();
        this.seasonRepository.saveAll(seasonList);
    }
}
