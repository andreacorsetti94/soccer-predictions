package com.acorsetti.service.probabilities;

import com.acorsetti.model.eval.GoalExpectancy;
import com.acorsetti.model.eval.MatchProbability;
import com.acorsetti.model.jpa.Fixture;


public interface PoissonCalculatorService {

    MatchProbability calculateOutcomesProbabilities(Fixture fixture, GoalExpectancy matchGoalExpectancy);
    double poissonFormula(int numOfEvents, double mean);

}
