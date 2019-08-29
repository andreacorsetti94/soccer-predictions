package com.acorsetti.service.probabilities;

import com.acorsetti.model.eval.GoalExpectancy;
import com.acorsetti.model.jpa.Fixture;

public interface StatisticalCalculatorService {

    GoalExpectancy calculateExpectancy(Fixture fixture);
}
