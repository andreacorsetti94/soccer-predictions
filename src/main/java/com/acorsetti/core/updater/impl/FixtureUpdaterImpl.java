package com.acorsetti.core.updater.impl;

import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.repository.FixtureRepository;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.updater.FixtureUpdater;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class FixtureUpdaterImpl implements FixtureUpdater {
    private static final Logger logger = Logger.getLogger(FixtureUpdaterImpl.class);

    @Autowired
    private FixtureRepository fixtureRepository;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private Environment environment;

    @Override
    @Scheduled(cron = "${cron.closeFixtures}")
    public void updateCloseFixtures() {
        logger.info("Updating close fixtures....");
        int daysPriorToThisDay = Integer.parseInt(Objects.requireNonNull(this.environment.getProperty("daysBefore")));
        int daysAfterThisDay = Integer.parseInt(Objects.requireNonNull(this.environment.getProperty("daysAfter")));

        LocalDate now = LocalDate.now();
        LocalDate lowerBound = now.minusDays(daysPriorToThisDay);
        LocalDate upperBound = now.plusDays(daysAfterThisDay);

        List<Fixture> fixturesToSave = this.fixtureService.fixturesInPeriodByAPI(lowerBound, upperBound);
        this.fixtureRepository.saveAll(fixturesToSave);
        logger.info("Close fixtures updated.");
    }

    @Override
    @Scheduled(cron = "${cron.currentFixtures}")
    public void updateCurrentFixtures() {
        logger.info("Updating current fixtures....");
        LocalDate now = LocalDate.now();
        LocalDate lowerBound = now.minusDays(2);
        LocalDate upperBound = now.plusDays(2);

        List<Fixture> fixturesToSave = this.fixtureService.fixturesInPeriodByAPI(lowerBound, upperBound);
        this.fixtureRepository.saveAll(fixturesToSave);
        logger.info("Current fixtures updated.");
    }
}
