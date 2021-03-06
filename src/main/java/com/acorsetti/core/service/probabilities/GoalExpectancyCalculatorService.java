package com.acorsetti.core.service.probabilities;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.jpa.Fixture;

public interface GoalExpectancyCalculatorService {

    GoalExpectancy calculateExpectancy(Fixture fixture);
}
