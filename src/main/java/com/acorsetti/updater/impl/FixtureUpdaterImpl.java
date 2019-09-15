package com.acorsetti.updater.impl;

import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.repository.FixtureRepository;
import com.acorsetti.service.FixtureService;
import com.acorsetti.updater.FixtureUpdater;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class FixtureUpdaterImpl implements FixtureUpdater {
    private static final Logger logger = Logger.getLogger(FixtureUpdaterImpl.class);

    @Autowired
    private FixtureRepository fixtureRepository;

    @Autowired
    private FixtureService fixtureService;

    @Override
    @Scheduled(cron = "${cron.closeFixtures}")
    public void updateCloseFixtures() {
        int daysPriorToThisDay = Integer.parseInt("${daysBefore}");
        int daysAfterThisDay = Integer.parseInt("${daysAfter}");

        LocalDate now = LocalDate.now();
        LocalDate lowerBound = now.minusDays(daysPriorToThisDay);
        LocalDate upperBound = now.plusDays(daysAfterThisDay);

        List<Fixture> fixturesToSave = this.fixtureService.fixturesInPeriodByAPI(lowerBound, upperBound);
        this.fixtureRepository.saveAll(fixturesToSave);
    }

    @Override
    @Scheduled(cron = "${cron.currentFixtures}")
    public void updateCurrentFixtures() {
        LocalDate now = LocalDate.now();
        LocalDate lowerBound = now.minusDays(2);
        LocalDate upperBound = now.plusDays(2);

        List<Fixture> fixturesToSave = this.fixtureService.fixturesInPeriodByAPI(lowerBound, upperBound);
        this.fixtureRepository.saveAll(fixturesToSave);
    }
}
