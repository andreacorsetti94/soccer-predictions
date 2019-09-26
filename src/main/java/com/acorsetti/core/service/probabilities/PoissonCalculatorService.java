package com.acorsetti.core.service.probabilities;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.eval.MatchProbability;
import com.acorsetti.core.model.jpa.Fixture;


public interface PoissonCalculatorService {

    MatchProbability calculateOutcomesProbabilities(Fixture fixture, GoalExpectancy matchGoalExpectancy);
    double poissonFormula(int numOfEvents, double mean);

}
