package com.acorsetti.core.updater.impl;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.GoalExpectancyEntity;
import com.acorsetti.core.repository.GoalExpectancyRepository;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.probabilities.GoalExpectancyCalculatorService;
import com.acorsetti.core.updater.GoalExpectancyUpdater;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private GoalExpectancyRepository goalExpectancyRepository;

    @Autowired
    @Qualifier("GoalExpectancyCalculatorServiceImpl")
    private GoalExpectancyCalculatorService goalExpectancyCalculatorServiceImpl;

    @Autowired
    @Qualifier("GoalExpectancyCalculatorServiceWeighted")
    private GoalExpectancyCalculatorService goalExpectancyCalculatorServiceWeighted;

    @Override
    @Scheduled(cron = "${cron.updateBets}")
    public void updateGoalExpectancy() {

        /*
        GoalExpectancyCalculatorService[] services =
                {goalExpectancyCalculatorServiceImpl, goalExpectancyCalculatorServiceWeighted};
        */
        GoalExpectancyCalculatorService[] services =
                {goalExpectancyCalculatorServiceWeighted};

        int nextDays = Integer.parseInt(Objects.requireNonNull(environment.getProperty("nextDays")));
        LocalDate now = LocalDate.now();
        LocalDate upperBound = now.plusDays(nextDays);

        List<Fixture> fixtureList = this.fixtureService.fixturesInPeriodByAPI(now, upperBound);
        for( int i = 0; i < fixtureList.size(); i++){
            Fixture currentFixture = fixtureList.get(i);

            for( GoalExpectancyCalculatorService service: services ){

                //do not calculate expectancy if the match is already started
                if ( currentFixture.getEventDate().isBefore(LocalDateTime.now() ) ) continue;
                if ( this.goalExpectancyRepository.findByFixtureIdAndCalculator(currentFixture.getFixtureId(),service.getClass().getSimpleName()) != null ) continue;

                GoalExpectancy goalExpectancy = service.calculateExpectancy(currentFixture);
                if ( goalExpectancy == null || !goalExpectancy.isLegit() ) continue;

                GoalExpectancyEntity goalExpectancyEntity = new GoalExpectancyEntity(currentFixture.getFixtureId(), goalExpectancy, service.getClass().getSimpleName());
                this.goalExpectancyRepository.save(goalExpectancyEntity);

            }
            logger.info("Goal Expectancy for fixture: " + currentFixture.getFixtureId() +
                    " is calculated. Progress: " + i + " / " + fixtureList.size());
        }
    }

}
