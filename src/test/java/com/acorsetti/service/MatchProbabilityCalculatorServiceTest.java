package com.acorsetti.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.api.APITeamStatisticsRetriever;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.eval.Chance;
import com.acorsetti.model.eval.GoalExpectancy;
import com.acorsetti.model.eval.MatchProbability;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.model.jpa.FixtureBuilder;
import com.acorsetti.service.probabilities.PoissonCalculatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class MatchProbabilityCalculatorServiceTest {

    @Autowired
    private PoissonCalculatorService poissonCalculatorService;

    @Test
    public void testMatchProbabilityCalculation(){
        String homeTeamId = "505";
        String awayTeamId = "498";
        String leagueId = "713";

        Fixture fixture = new FixtureBuilder().withFixtureId("ID1").withHomeTeamId(homeTeamId).withAwayTeamId(awayTeamId).withLeagueId(leagueId).build();

        GoalExpectancy goalExpectancy = new GoalExpectancy(2.1, 1.5);

        MatchProbability mp = this.poissonCalculatorService.calculateOutcomesProbabilities(fixture, goalExpectancy);

        System.out.println("Match Probabilities for fixture between team " + homeTeamId +" and " + awayTeamId+ " :");
        System.out.println(mp.toString());

        for(MarketValue mv: mp.getProbabilities().keySet()){
            Chance chance = mp.getMarketChance(mv);
            assertTrue(chance.getValue() >= 0.01 && chance.getValue() <= 0.99);
        }
    }

}
