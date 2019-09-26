package com.acorsetti.core.service;

import com.acorsetti.core.model.eval.MatchProbability;
import com.acorsetti.core.model.jpa.Fixture;

public interface MatchProbabilityCalculatorService {

    MatchProbability calculateProbability(Fixture fixture) throws Exception;
}
