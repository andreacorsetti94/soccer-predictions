package com.acorsetti.core.service.impl.probabilities;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.*;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.service.probabilities.FixtureOutcomesEvaluatorService;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.MatchProbabilityCalculatorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class FixtureOutcomesEvaluatorServiceImpl implements FixtureOutcomesEvaluatorService {
    private static final Logger logger = Logger.getLogger(FixtureOutcomesEvaluatorServiceImpl.class);

    @Autowired
    private MatchProbabilityCalculatorService matchProbabilityCalculatorService;

    @Autowired
    private FixtureService fixtureService;

    /**
     MarketOutcome > > > > > > > > > > OutcomeEvaluation
     */
    @Override
    public FixtureEvals evaluate(FixtureOutcomes fixtureOutcomes) {
        if ( fixtureOutcomes == null ) return new FixtureEvals("", Collections.emptyList());

        String fixtureId = fixtureOutcomes.getFixtureId();
        Fixture fixture = this.fixtureService.byId(fixtureId);
        MatchProbability matchProbability;
        try{
            matchProbability = this.matchProbabilityCalculatorService.calculateProbability(fixture);
        }
        catch (Exception e){
            logger.warn("Catched Exception. Fixture with ID: " + fixtureId + " cannot be retrieved from DB",e);
            return new FixtureEvals("", Collections.emptyList());
        }

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
