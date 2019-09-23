package com.acorsetti.service.impl;

import com.acorsetti.model.eval.GoalExpectancy;
import com.acorsetti.model.eval.MatchProbability;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.service.MatchProbabilityCalculatorService;
import com.acorsetti.service.probabilities.PoissonCalculatorService;
import com.acorsetti.service.probabilities.StatisticalCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MatchProbabilityCalculatorServiceImpl implements MatchProbabilityCalculatorService {

    @Autowired
    @Qualifier("statisticalCalculatorByAPI")
    private StatisticalCalculatorService statisticalCalculatorService;

    @Autowired
    private PoissonCalculatorService poissonCalculatorService;

    @Override
    public MatchProbability calculateProbability(Fixture fixture) throws IllegalArgumentException{
        if ( fixture == null ) throw new IllegalArgumentException("Null Fixture passed as argument!");
        GoalExpectancy goalExpectancy = this.statisticalCalculatorService.calculateExpectancy(fixture);
        if ( !goalExpectancy.isLegit() ) throw new IllegalStateException("Goal expectancy for: " + fixture + " is not legit.");
        return this.poissonCalculatorService.calculateOutcomesProbabilities(fixture, goalExpectancy);
    }
}
