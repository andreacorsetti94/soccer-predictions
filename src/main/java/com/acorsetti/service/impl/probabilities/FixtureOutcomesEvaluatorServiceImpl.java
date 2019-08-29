package com.acorsetti.service.impl.probabilities;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.eval.*;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.service.probabilities.FixtureOutcomesEvaluatorService;
import com.acorsetti.service.FixtureService;
import com.acorsetti.service.MatchProbabilityCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FixtureOutcomesEvaluatorServiceImpl implements FixtureOutcomesEvaluatorService {

    @Autowired
    private MatchProbabilityCalculatorService matchProbabilityCalculatorService;

    @Autowired
    private FixtureService fixtureService;

    /**
     MarketOutcome > > > > > > > > > > OutcomeEvaluation
     */
    @Override
    public FixtureEvals evaluate(FixtureOutcomes fixtureOutcomes) {
        String fixtureId = fixtureOutcomes.getFixtureId();
        Fixture fixture = this.fixtureService.byId(fixtureId);

        MatchProbability matchProbability = this.matchProbabilityCalculatorService.calculateProbability(fixture);

        List<OutcomeEvaluation> outcomeEvaluationList = new ArrayList<>();

        for( MarketOutcome marketOutcome: fixtureOutcomes.getMarketOutcomes() ){
            MarketValue marketValue = marketOutcome.getMarketValue();
            Chance chance = matchProbability.getMarketChance(marketValue);
            OutcomeEvaluation outcomeEvaluation = new OutcomeEvaluation(marketOutcome, chance);
            outcomeEvaluationList.add(outcomeEvaluation);
        }

        return new FixtureEvals(fixtureId, outcomeEvaluationList);
    }

}
