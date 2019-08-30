package com.acorsetti.service.impl.probabilities;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.eval.Chance;
import com.acorsetti.model.eval.GoalExpectancy;
import com.acorsetti.model.eval.MatchProbability;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.service.probabilities.ExactScoreProbabilityCalculatorService;
import com.acorsetti.service.probabilities.MarketValuesPoissonMapperService;
import com.acorsetti.service.probabilities.PoissonCalculatorService;
import org.apache.commons.math3.distribution.PoissonDistribution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PoissonCalculatorServiceImpl implements PoissonCalculatorService {

    @Autowired
    private ExactScoreProbabilityCalculatorService exactScoreProbabilityCalculatorService;

    @Autowired
    private MarketValuesPoissonMapperService marketValuesPoissonMapperService;

    @Override
    public MatchProbability calculateOutcomesProbabilities(Fixture fixture, GoalExpectancy matchGoalExpectancy) {
        if ( fixture == null || matchGoalExpectancy == null ) {
            throw  new IllegalArgumentException("Fixture or GoalExpectancy is null. IllegalArguments!");
        }

        Map<MarketValue, Chance> exactScoresProbabilities = this.exactScoreProbabilityCalculatorService.calculate(matchGoalExpectancy);

        Chance over1_5 = this.marketValuesPoissonMapperService.computeOver(exactScoresProbabilities,1);
        Chance over2_5 = this.marketValuesPoissonMapperService.computeOver(exactScoresProbabilities,2);
        Chance over3_5 = this.marketValuesPoissonMapperService.computeOver(exactScoresProbabilities,3);
        Chance over4_5 = this.marketValuesPoissonMapperService.computeOver(exactScoresProbabilities,4);
        Chance under1_5 = new Chance(1 - over1_5.getValue());
        Chance under2_5 = new Chance(1 - over2_5.getValue());
        Chance under3_5 = new Chance(1 - over3_5.getValue());
        Chance under4_5 = new Chance(1 - over4_5.getValue());
        Chance homeFT = this.marketValuesPoissonMapperService.computeHomeFT(exactScoresProbabilities);
        Chance awayFT = this.marketValuesPoissonMapperService.computeAwayFT(exactScoresProbabilities);
        Chance drawFT = new Chance(1 - (homeFT.getValue() + awayFT.getValue()));
        Chance hdDC = new Chance(homeFT.getValue() + drawFT.getValue());
        Chance daDC = new Chance(drawFT.getValue() + awayFT.getValue());
        Chance haDC = new Chance(homeFT.getValue() + awayFT.getValue());
        Chance goal = this.marketValuesPoissonMapperService.computeGoalFT(exactScoresProbabilities);
        Chance noGoal = new Chance(1 - goal.getValue());

        Chance home_over1_5 = new Chance(homeFT.getValue()*over1_5.getValue());
        Chance home_over2_5 = new Chance(homeFT.getValue()*over2_5.getValue());
        Chance home_over3_5 = new Chance(homeFT.getValue()*over3_5.getValue());
        Chance home_over4_5 = new Chance(homeFT.getValue()*over4_5.getValue());
        Chance home_under1_5 = new Chance(homeFT.getValue()*under1_5.getValue());
        Chance home_under2_5 = new Chance(homeFT.getValue()*under2_5.getValue());
        Chance home_under3_5 = new Chance(homeFT.getValue()*under3_5.getValue());
        Chance home_under4_5 = new Chance(homeFT.getValue()*under4_5.getValue());
        Chance draw_over1_5 = new Chance(drawFT.getValue()*over1_5.getValue());
        Chance draw_over2_5 = new Chance(drawFT.getValue()*over2_5.getValue());
        Chance draw_over3_5 = new Chance(drawFT.getValue()*over3_5.getValue());
        Chance draw_over4_5 = new Chance(drawFT.getValue()*over4_5.getValue());
        Chance draw_under1_5 = new Chance(drawFT.getValue()*under1_5.getValue());
        Chance draw_under2_5 = new Chance(drawFT.getValue()*under2_5.getValue());
        Chance draw_under3_5 = new Chance(drawFT.getValue()*under3_5.getValue());
        Chance draw_under4_5 = new Chance(drawFT.getValue()*under4_5.getValue());
        Chance away_over1_5 = new Chance(awayFT.getValue()*over1_5.getValue());
        Chance away_over2_5 = new Chance(awayFT.getValue()*over2_5.getValue());
        Chance away_over3_5 = new Chance(awayFT.getValue()*over3_5.getValue());
        Chance away_over4_5 = new Chance(awayFT.getValue()*over4_5.getValue());
        Chance away_under1_5 = new Chance(awayFT.getValue()*under1_5.getValue());
        Chance away_under2_5 = new Chance(awayFT.getValue()*under2_5.getValue());
        Chance away_under3_5 = new Chance(awayFT.getValue()*under3_5.getValue());
        Chance away_under4_5 = new Chance(awayFT.getValue()*under4_5.getValue());
        Chance home_goalYes = new Chance(homeFT.getValue()*goal.getValue());
        Chance home_goalNo = new Chance(homeFT.getValue()*noGoal.getValue());
        Chance draw_goalYes = new Chance(drawFT.getValue()*goal.getValue());
        Chance draw_goalNo = new Chance(drawFT.getValue()*noGoal.getValue());
        Chance away_goalYes = new Chance(awayFT.getValue()*goal.getValue());
        Chance away_goalNo = new Chance(awayFT.getValue()*noGoal.getValue());

        exactScoresProbabilities.put(MarketValue.HDA_HOME,homeFT);
        exactScoresProbabilities.put(MarketValue.HDA_DRAW,drawFT);
        exactScoresProbabilities.put(MarketValue.HDA_AWAY,awayFT);
        exactScoresProbabilities.put(MarketValue.DC_HOME_DRAW,hdDC);
        exactScoresProbabilities.put(MarketValue.DC_DRAW_AWAY,daDC);
        exactScoresProbabilities.put(MarketValue.DC_HOME_AWAY,haDC);
        exactScoresProbabilities.put(MarketValue.BTTS_YES,goal);
        exactScoresProbabilities.put(MarketValue.BTTS_NO,noGoal);
        exactScoresProbabilities.put(MarketValue.O1_5,over1_5);
        exactScoresProbabilities.put(MarketValue.O2_5,over2_5);
        exactScoresProbabilities.put(MarketValue.O3_5,over3_5);
        exactScoresProbabilities.put(MarketValue.O4_5,over4_5);
        exactScoresProbabilities.put(MarketValue.U1_5,under1_5);
        exactScoresProbabilities.put(MarketValue.U2_5,under2_5);
        exactScoresProbabilities.put(MarketValue.U3_5,under3_5);
        exactScoresProbabilities.put(MarketValue.U4_5,under4_5);
        exactScoresProbabilities.put(MarketValue.HDA_HOME_O1_5,home_over1_5);
        exactScoresProbabilities.put(MarketValue.HDA_HOME_O2_5,home_over2_5);
        exactScoresProbabilities.put(MarketValue.HDA_HOME_O3_5,home_over3_5);
        exactScoresProbabilities.put(MarketValue.HDA_HOME_O4_5,home_over4_5);
        exactScoresProbabilities.put(MarketValue.HDA_HOME_U1_5,home_under1_5);
        exactScoresProbabilities.put(MarketValue.HDA_HOME_U2_5,home_under2_5);
        exactScoresProbabilities.put(MarketValue.HDA_HOME_U3_5,home_under3_5);
        exactScoresProbabilities.put(MarketValue.HDA_HOME_U4_5,home_under4_5);
        exactScoresProbabilities.put(MarketValue.HDA_DRAW_O1_5,draw_over1_5);
        exactScoresProbabilities.put(MarketValue.HDA_DRAW_O2_5,draw_over2_5);
        exactScoresProbabilities.put(MarketValue.HDA_DRAW_O3_5,draw_over3_5);
        exactScoresProbabilities.put(MarketValue.HDA_DRAW_O4_5,draw_over4_5);
        exactScoresProbabilities.put(MarketValue.HDA_DRAW_U1_5,draw_under1_5);
        exactScoresProbabilities.put(MarketValue.HDA_DRAW_U2_5,draw_under2_5);
        exactScoresProbabilities.put(MarketValue.HDA_DRAW_U3_5,draw_under3_5);
        exactScoresProbabilities.put(MarketValue.HDA_DRAW_U4_5,draw_under4_5);
        exactScoresProbabilities.put(MarketValue.HDA_AWAY_O1_5,away_over1_5);
        exactScoresProbabilities.put(MarketValue.HDA_AWAY_O2_5,away_over2_5);
        exactScoresProbabilities.put(MarketValue.HDA_AWAY_O3_5,away_over3_5);
        exactScoresProbabilities.put(MarketValue.HDA_AWAY_O4_5,away_over4_5);
        exactScoresProbabilities.put(MarketValue.HDA_AWAY_U1_5,away_under1_5);
        exactScoresProbabilities.put(MarketValue.HDA_AWAY_U2_5,away_under2_5);
        exactScoresProbabilities.put(MarketValue.HDA_AWAY_U3_5,away_under3_5);
        exactScoresProbabilities.put(MarketValue.HDA_AWAY_U4_5,away_under4_5);
        exactScoresProbabilities.put(MarketValue.HDA_BTTS_HOME_YES,home_goalYes);
        exactScoresProbabilities.put(MarketValue.HDA_BTTS_DRAW_YES,draw_goalYes);
        exactScoresProbabilities.put(MarketValue.HDA_BTTS_AWAY_YES,away_goalYes);
        exactScoresProbabilities.put(MarketValue.HDA_BTTS_HOME_NO,home_goalNo);
        exactScoresProbabilities.put(MarketValue.HDA_BTTS_DRAW_NO,draw_goalNo);
        exactScoresProbabilities.put(MarketValue.HDA_BTTS_AWAY_NO,away_goalNo);

        return new MatchProbability(fixture, exactScoresProbabilities);
    }

    @Override
    public double poissonFormula(int numOfEvents, double mean) {
        if ( mean <= 0 ) return 0;
        double poissonDistribution =
                new PoissonDistribution(mean, PoissonDistribution.DEFAULT_EPSILON).probability(numOfEvents);
        return Math.max(0.001, poissonDistribution);
    }

}
