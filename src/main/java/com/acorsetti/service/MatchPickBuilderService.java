package com.acorsetti.service;

import com.acorsetti.model.eval.FixtureEvals;
import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.model.odds.FixtureOdds;

import java.util.List;

public interface MatchPickBuilderService {

    List<MatchPick> buildMatchPicks(FixtureEvals fixtureEvals, FixtureOdds fixtureOdds);
}
