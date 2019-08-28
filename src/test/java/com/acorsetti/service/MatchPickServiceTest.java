package com.acorsetti.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.enums.PickResult;
import com.acorsetti.model.odds.OddsValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class MatchPickServiceTest {

    @Autowired
    private MatchPickService matchPickService;

    @Test
    public void testAllPicks(){
        assertTrue( this.matchPickService.listAllPicks().size() > 0);
    }

    @Test
    public void testFindMatchpick(){
        String fixtureId = "107305";
        MarketValue mv = MarketValue.HDA_AWAY;
        assertNotNull( this.matchPickService.byFixtureAndMarket(fixtureId, mv) );

        assertNull(this.matchPickService.byFixtureAndMarket(null,null) );
    }

    @Test
    public void testPicksWithOddsBetween(){
        OddsValue low = new OddsValue(2.0);
        OddsValue high = new OddsValue(10.0);

        this.matchPickService.picksWithOddsBetween(low.getValue(),high.getValue()).forEach( matchPick ->
                assertTrue(matchPick.getOdds().getValue() >= low.getValue()
                && matchPick.getOdds().getValue() <= high.getValue()));

        OddsValue newHigh = new OddsValue(-10.0);

        assertEquals(0, this.matchPickService.picksWithOddsBetween(low.getValue(), newHigh.getValue()).size());

    }

    @Test
    public void testOpenPicks(){
        this.matchPickService.openPicks().forEach( pick ->
                assertTrue(pick.getPickResult() != PickResult.YES
                        && pick.getPickResult() != PickResult.NO)
        );
    }

    @Test
    public void testOpenValuablePicks(){
        this.matchPickService.openValuablePicks().forEach( pick ->
                assertTrue(
                pick.getPickResult() != PickResult.YES
                        && pick.getPickResult() != PickResult.NO
                        && pick.getPickValue().getValue() > 0
                )
        );
    }
}
