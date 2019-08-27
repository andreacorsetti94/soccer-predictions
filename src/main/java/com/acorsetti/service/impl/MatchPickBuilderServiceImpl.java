package com.acorsetti.service.impl;

import com.acorsetti.model.eval.FixtureEvals;
import com.acorsetti.model.jpa.MatchPick;
import com.acorsetti.model.odds.FixtureOdds;
import com.acorsetti.service.MatchPickBuilderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchPickBuilderServiceImpl implements MatchPickBuilderService {
    @Override
    public List<MatchPick> buildMatchPicks(FixtureEvals fixtureEvals, FixtureOdds fixtureOdds) {
        //TODO
        return null;
    }
}
