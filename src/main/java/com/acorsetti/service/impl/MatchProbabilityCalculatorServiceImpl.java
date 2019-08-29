package com.acorsetti.service.impl;

import com.acorsetti.model.eval.GoalExpectancy;
import com.acorsetti.model.eval.MatchProbability;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.service.MatchProbabilityCalculatorService;
import com.acorsetti.service.probabilities.PoissonCalculatorService;
import com.acorsetti.service.probabilities.StatisticalCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchProbabilityCalculatorServiceImpl implements MatchProbabilityCalculatorService {

    @Autowired
    private StatisticalCalculatorService statisticalCalculatorService;

    @Autowired
    private PoissonCalculatorService poissonCalculatorService;

    @Override
    public MatchProbability calculateProbability(Fixture fixture) {
        GoalExpectancy goalExpectancy = this.statisticalCalculatorService.calculateExpectancy(fixture);
        return this.poissonCalculatorService.calculateOutcomesProbabilities(fixture, goalExpectancy);
    }
}
