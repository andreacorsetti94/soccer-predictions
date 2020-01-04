package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.eval.MatchProbability;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.GoalExpectancyEntity;
import com.acorsetti.core.repository.GoalExpectancyRepository;
import com.acorsetti.core.service.MatchProbabilityCalculatorService;
import com.acorsetti.core.service.probabilities.PoissonCalculatorService;
import com.acorsetti.core.service.probabilities.GoalExpectancyCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MatchProbabilityCalculatorServiceImpl implements MatchProbabilityCalculatorService {

    @Autowired
    @Qualifier("GoalExpectancyCalculatorServiceWeighted")
    private GoalExpectancyCalculatorService goalExpectancyCalculatorService;

    @Autowired
    private PoissonCalculatorService poissonCalculatorService;

    @Autowired
    private GoalExpectancyRepository goalExpectancyRepository;

    @Override
    public MatchProbability calculateProbability(Fixture fixture) throws Exception {
        if ( fixture == null ) throw new IllegalArgumentException("Null Fixture passed as argument!");
        String id = fixture.getFixtureId();
        String calculatorName = this.goalExpectancyCalculatorService.getClass().getSimpleName();

        //try to retrieve Goal Expectancy from DB. If it exists use it, if not calculate again
        GoalExpectancy goalExpectancy;
        GoalExpectancyEntity GEE = this.goalExpectancyRepository.findByFixtureIdAndCalculator(id, calculatorName);
        if ( GEE == null ){
            goalExpectancy = this.goalExpectancyCalculatorService.calculateExpectancy(fixture);
            if ( goalExpectancy == null || !goalExpectancy.isLegit() ){
                throw new RuntimeException("Goal Exception calculated for fixture: " + id + " is not legit.");
            }
            GoalExpectancyEntity createdGEE = new GoalExpectancyEntity(id, goalExpectancy, calculatorName);
            this.goalExpectancyRepository.save(createdGEE);
        }
        else{
            goalExpectancy = new GoalExpectancy(GEE.getHomeGE(), GEE.getAwayGE());
        }
        if ( !goalExpectancy.isLegit() ) throw new IllegalStateException("Goal expectancy for: " + fixture + " is not legit.");
        return this.poissonCalculatorService.calculateOutcomesProbabilities(fixture, goalExpectancy);
    }
}
