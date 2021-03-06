package com.acorsetti.core.service.impl.probabilities;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.service.probabilities.ExactScoreProbabilityCalculatorService;
import com.acorsetti.core.service.probabilities.PoissonCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExactScoreProbabilityCalculatorServiceImpl implements ExactScoreProbabilityCalculatorService {

    @Autowired
    private PoissonCalculatorService poissonCalculatorService;

    public Map<MarketValue, Chance> calculate(GoalExpectancy goalExpectancy){
        if ( goalExpectancy == null ) return new HashMap<>();

        Map<MarketValue, Chance> exactScoresChances = new HashMap<>();
        double otherChance = 0;

        for(int homeGoals = 0; homeGoals < 9; homeGoals++){
            for(int awayGoals = 0; awayGoals < 9; awayGoals++){
                double scoreChance =
                        poissonCalculatorService.poissonFormula(homeGoals, goalExpectancy.getHomeValue())
                                * poissonCalculatorService.poissonFormula(awayGoals, goalExpectancy.getAwayValue());

                MarketValue marketValue = MarketValue.byRepresentation(homeGoals + " - " + awayGoals);
                if ( marketValue != MarketValue.EMPTY_MARKET_VALUE ){
                    exactScoresChances.put(marketValue, new Chance(scoreChance));
                }
                if ( homeGoals > 4 && awayGoals > 4 ) otherChance += scoreChance;
            }
        }
        exactScoresChances.put(MarketValue.OTHER, new Chance(otherChance));
        return exactScoresChances;
    }
}
