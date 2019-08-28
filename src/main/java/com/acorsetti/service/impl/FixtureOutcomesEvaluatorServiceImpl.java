package com.acorsetti.service.impl;

import com.acorsetti.model.eval.*;
import com.acorsetti.service.ChanceCalculatorService;
import com.acorsetti.service.FixtureOutcomesEvaluatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FixtureOutcomesEvaluatorServiceImpl implements FixtureOutcomesEvaluatorService {

    @Autowired
    private ChanceCalculatorService chanceCalculatorService;

    /**
     MarketOutcome > > > > > > > > > > OutcomeEvaluation
     */
    @Override
    public FixtureEvals evaluate(FixtureOutcomes fixtureOutcomes) {
        String fixtureId = fixtureOutcomes.getFixtureId();
        List<OutcomeEvaluation> outcomeEvaluationList = new ArrayList<>();

        for( MarketOutcome marketOutcome: fixtureOutcomes.getMarketOutcomes() ){
            Chance chance = this.chanceCalculatorService.calculate(fixtureId, marketOutcome);
            OutcomeEvaluation outcomeEvaluation = new OutcomeEvaluation(marketOutcome, chance);
            outcomeEvaluationList.add(outcomeEvaluation);
        }

        return new FixtureEvals(fixtureId, outcomeEvaluationList);
    }

}
