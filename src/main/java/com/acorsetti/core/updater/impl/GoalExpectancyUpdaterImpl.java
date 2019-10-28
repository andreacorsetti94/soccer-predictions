package com.acorsetti.core.updater.impl;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.GoalExpectancyEntity;
import com.acorsetti.core.repository.GoalExpectancyRepository;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.probabilities.StatisticalCalculatorService;
import com.acorsetti.core.updater.GoalExpectancyUpdater;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Configuration
@Service
@PropertySource("classpath:scheduler.properties")
public class GoalExpectancyUpdaterImpl implements GoalExpectancyUpdater {

    private static final Logger logger = Logger.getLogger(GoalExpectancyUpdaterImpl.class);

    @Autowired
    private Environment environment;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private StatisticalCalculatorService statisticalCalculatorService;

    @Autowired
    private GoalExpectancyRepository goalExpectancyRepository;

    @Override
    @Scheduled(cron = "${cron.updateBets}")
    public void updateGoalExpectancy() {
        int nextDays = Integer.parseInt(Objects.requireNonNull(environment.getProperty("nextDays")));
        LocalDate now = LocalDate.now();
        LocalDate upperBound = now.plusDays(nextDays);

        List<Fixture> fixtureList = this.fixtureService.fixturesInPeriodByAPI(now, upperBound);
        fixtureList.forEach( fixture -> {

            //do not calculate expectancy if the match is already started
            if ( fixture.getEventDate().isAfter(LocalDateTime.now() ) ) return;

            GoalExpectancy goalExpectancy = this.statisticalCalculatorService.calculateExpectancy(fixture);
            if ( goalExpectancy == null || !goalExpectancy.isLegit() ) return;

            GoalExpectancyEntity goalExpectancyEntity = new GoalExpectancyEntity(fixture.getFixtureId(), goalExpectancy);
            this.goalExpectancyRepository.save(goalExpectancyEntity);
            logger.info("Goal Expectancy for fixture: " + fixture.getFixtureId() +
                    " is calculated: " + goalExpectancyEntity.toString());
        });
    }
}
