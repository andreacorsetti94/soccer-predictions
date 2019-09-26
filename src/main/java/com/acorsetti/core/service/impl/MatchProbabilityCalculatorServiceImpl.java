package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.eval.MatchProbability;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.service.MatchProbabilityCalculatorService;
import com.acorsetti.core.service.probabilities.PoissonCalculatorService;
import com.acorsetti.core.service.probabilities.StatisticalCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchProbabilityCalculatorServiceImpl implements MatchProbabilityCalculatorService {

    @Autowired
    private StatisticalCalculatorService statisticalCalculatorService;

    @Autowired
    private PoissonCalculatorService poissonCalculatorService;

    @Override
    public MatchProbability calculateProbability(Fixture fixture) throws Exception {
        if ( fixture == null ) throw new IllegalArgumentException("Null Fixture passed as argument!");
        GoalExpectancy goalExpectancy = this.statisticalCalculatorService.calculateExpectancy(fixture);
        if ( !goalExpectancy.isLegit() ) throw new IllegalStateException("Goal expectancy for: " + fixture + " is not legit.");
        return this.poissonCalculatorService.calculateOutcomesProbabilities(fixture, goalExpectancy);
    }
}
