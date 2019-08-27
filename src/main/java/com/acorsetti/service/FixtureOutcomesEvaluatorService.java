package com.acorsetti.service;

import com.acorsetti.model.eval.FixtureEvals;
import com.acorsetti.model.eval.FixtureOutcomes;

public interface FixtureOutcomesEvaluatorService {

    FixtureEvals evaluate(FixtureOutcomes fixtureOutcomes);
}
