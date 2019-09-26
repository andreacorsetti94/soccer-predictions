package com.acorsetti.core.service;

import com.acorsetti.core.model.eval.FixtureEvals;
import com.acorsetti.core.model.jpa.MatchPick;
import com.acorsetti.core.model.odds.FixtureOdds;

import java.util.List;

public interface MatchPickBuilderService {

    List<MatchPick> buildMatchPicks(FixtureEvals fixtureEvals, FixtureOdds fixtureOdds);
}
