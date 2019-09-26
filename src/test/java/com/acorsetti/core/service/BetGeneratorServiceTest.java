package com.acorsetti.core.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.PickValue;
import com.acorsetti.core.model.jpa.Bet;
import com.acorsetti.core.model.jpa.MatchPick;
import com.acorsetti.core.model.odds.OddsValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class BetGeneratorServiceTest {

    @Autowired
    private BetGeneratorService betGeneratorService;

    @Test
    public void testBetGeneration(){
        MatchPick m1 = new MatchPick("a", MarketValue.HDA_AWAY, new OddsValue(1.05), new Chance(34), new PickValue(0.3));
        MatchPick m2 = new MatchPick("b", MarketValue.DC_DRAW_AWAY, new OddsValue(5), new Chance(12), new PickValue(0.5));
        MatchPick m3 = new MatchPick("c", MarketValue.HDA_BTTS_DRAW_NO, new OddsValue(1.25), new Chance(0.86), new PickValue(0.1));

        List<MatchPick> matchPickList = Arrays.asList(m1,m2,m3);

        List<Bet> bets = this.betGeneratorService.generateBets(matchPickList);

        for(Bet bet : bets){
            assertEquals("c", bet.getFixtureId());
            assertEquals(MarketValue.HDA_BTTS_DRAW_NO, bet.getMarket());
            assertEquals(1.25, bet.getOdds().getValue(),0.0);
            assertTrue(bet.getAmount() > 0 && bet.getAmount() <= 100 );

        }
    }


}
