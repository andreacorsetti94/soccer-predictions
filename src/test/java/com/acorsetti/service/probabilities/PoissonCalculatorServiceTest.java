package com.acorsetti.service.probabilities;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.eval.GoalExpectancy;
import com.acorsetti.model.eval.MatchProbability;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.model.jpa.FixtureBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class PoissonCalculatorServiceTest {

    @Autowired
    private PoissonCalculatorService poissonCalculatorService;

    @Test
    public void testNotLegitInput(){
        MatchProbability mp;
        try{
            mp = this.poissonCalculatorService.calculateOutcomesProbabilities(null, null);
        }
        catch (IllegalArgumentException e){
            //expection catched
            return;
        }

        assertTrue(mp.getProbabilities().isEmpty());
    }


    @Test
    public void test(){
        Fixture f = new FixtureBuilder().withFixtureId("ID1").withHomeTeamId("505").withAwayTeamId("498").build();
        MatchProbability mp = this.poissonCalculatorService.calculateOutcomesProbabilities(f, new GoalExpectancy(1.5,0.5));

        assertEquals(mp.getFixture(), f);
        assertEquals(1.0, mp.getMarketChance(MarketValue.HDA_HOME).getValue() +
                mp.getMarketChance(MarketValue.HDA_AWAY).getValue() + mp.getMarketChance(MarketValue.HDA_DRAW).getValue(),0.0);

        assertEquals(1.0, mp.getMarketChance(MarketValue.BTTS_YES).getValue() +
                mp.getMarketChance(MarketValue.BTTS_NO).getValue(),0.0);


        assertEquals(1.0, mp.getMarketChance(MarketValue.U1_5).getValue() +
                mp.getMarketChance(MarketValue.O1_5).getValue(),0.0);


        assertEquals(1.0, mp.getMarketChance(MarketValue.U2_5).getValue() +
                mp.getMarketChance(MarketValue.O2_5).getValue(),0.0);

        assertEquals(1.0, mp.getMarketChance(MarketValue.U3_5).getValue() +
                mp.getMarketChance(MarketValue.O3_5).getValue(),0.0);

        assertEquals(1.0, mp.getMarketChance(MarketValue.U4_5).getValue() +
                mp.getMarketChance(MarketValue.O4_5).getValue(),0.0);

        assertEquals(1.0, mp.getMarketChance(MarketValue.U3_5).getValue() +
                mp.getMarketChance(MarketValue.O3_5).getValue(),0.0);
    }
}
