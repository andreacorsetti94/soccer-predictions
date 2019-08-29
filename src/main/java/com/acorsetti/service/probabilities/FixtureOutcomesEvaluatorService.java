package com.acorsetti.service.probabilities;

import com.acorsetti.model.eval.FixtureEvals;
import com.acorsetti.model.eval.FixtureOutcomes;

public interface FixtureOutcomesEvaluatorService {

    FixtureEvals evaluate(FixtureOutcomes fixtureOutcomes);
}
