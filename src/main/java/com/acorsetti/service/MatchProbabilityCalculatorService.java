package com.acorsetti.service;

import com.acorsetti.model.eval.MatchProbability;
import com.acorsetti.model.jpa.Fixture;

public interface MatchProbabilityCalculatorService {

    MatchProbability calculateProbability(Fixture fixture);
}
