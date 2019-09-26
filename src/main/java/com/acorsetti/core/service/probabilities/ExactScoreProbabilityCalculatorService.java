package com.acorsetti.core.service.probabilities;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.GoalExpectancy;

import java.util.Map;

public interface ExactScoreProbabilityCalculatorService {

    Map<MarketValue, Chance> calculate(GoalExpectancy goalExpectancy);
}
