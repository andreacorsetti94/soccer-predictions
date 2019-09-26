package com.acorsetti.core.service.probabilities;

import com.acorsetti.core.model.eval.FixtureEvals;
import com.acorsetti.core.model.eval.FixtureOutcomes;

public interface FixtureOutcomesEvaluatorService {

    FixtureEvals evaluate(FixtureOutcomes fixtureOutcomes);
}
