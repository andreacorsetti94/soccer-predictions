package com.acorsetti.service.probabilities;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.eval.Chance;
import com.acorsetti.model.eval.GoalExpectancy;

import java.util.Map;

public interface ExactScoreProbabilityCalculatorService {

    Map<MarketValue, Chance> calculate(GoalExpectancy goalExpectancy);
}
