package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.FixtureEvals;
import com.acorsetti.core.model.eval.OutcomeEvaluation;
import com.acorsetti.core.model.eval.PickValue;
import com.acorsetti.core.model.jpa.MatchPick;
import com.acorsetti.core.model.odds.FixtureOdds;
import com.acorsetti.core.model.odds.MarketOdds;
import com.acorsetti.core.model.odds.OddsValue;
import com.acorsetti.core.service.MatchPickBuilderService;
import com.acorsetti.core.service.PickValueCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MatchPickBuilderServiceImpl implements MatchPickBuilderService {

    @Autowired
    private PickValueCalculatorService pickValueCalculatorService;

    @Override
    public List<MatchPick> buildMatchPicks(FixtureEvals fixtureEvals, FixtureOdds fixtureOdds) {
        if ( fixtureEvals == null || fixtureOdds == null ) return Collections.emptyList();
        if ( ! fixtureEvals.getFixtureId().equals( fixtureOdds.getFixtureId() ) ) return Collections.emptyList();


        List<MatchPick> matchPicks = new ArrayList<>();

        for( OutcomeEvaluation outcomeEvaluation: fixtureEvals.getOutcomeEvaluations() ){
            for(MarketOdds marketOdds: fixtureOdds.getMarketOdds() ){
                //join on marketValue
                if ( outcomeEvaluation.getMarketOutcome().getMarketValue() == marketOdds.getMarketValue() ){
                    String fixtureId = fixtureOdds.getFixtureId();
                    MarketValue marketValue = marketOdds.getMarketValue();
                    OddsValue odds = marketOdds.getOddsValue();
                    Chance chance = outcomeEvaluation.getChance();
                    PickValue pickValue = this.pickValueCalculatorService.calculatePickValue(chance,odds);

                    MatchPick matchPick = new MatchPick(fixtureId,marketValue,odds,chance,pickValue);
                    matchPicks.add(matchPick);
                }

            }

        }
        return matchPicks;
    }

}
