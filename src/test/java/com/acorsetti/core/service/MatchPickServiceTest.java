package com.acorsetti.core.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.enums.PickResult;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.PickValue;
import com.acorsetti.core.model.jpa.MatchPick;
import com.acorsetti.core.model.odds.OddsValue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigWebContextLoader.class)
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
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

    @Test
    public void testMatchPickResultUpdate(){
        MatchPick one = new MatchPick("107307",MarketValue.HDA_AWAY, new OddsValue(4.24), new Chance(0.16), new PickValue(-23.0));
        MatchPick two = new MatchPick("107307",MarketValue.HDA_DRAW, new OddsValue(3.08), new Chance(0.27), new PickValue(-32.0));
        MatchPick three = new MatchPick("107305",MarketValue.HDA_AWAY, new OddsValue(2.11), new Chance(0.62), new PickValue(-47.0));
        List<MatchPick> matchPicks = Arrays.asList(one,two,three);

        List<MatchPick> updatedPicks = this.matchPickService.updateMatchPicksResult(matchPicks);
        MatchPick updatedOne = updatedPicks.get(0);
        MatchPick updatedTwo = updatedPicks.get(1);
        MatchPick updatedThree = updatedPicks.get(2);
        assertEquals(one, updatedOne);
        assertEquals(two, updatedTwo);
        assertEquals(three, updatedThree);
        assertEquals(PickResult.YES, updatedOne.getPickResult());
        assertEquals(PickResult.NO, updatedTwo.getPickResult());
        assertEquals(PickResult.NO, updatedThree.getPickResult());
    }

    @Test
    public void testMatchPickGeneration(){
        /*
        Fixture f1 = this.fixtureService.byId("83265");
        Fixture f2 = this.fixtureService.byId("121035");
        Fixture f3 = this.fixtureService.byId("75695");
        Fixture f4 = this.fixtureService.byId("121033");

        List<Fixture> fixtures = Arrays.asList(f1,f2,f3,f4);

        List<MatchPick> matchPicksGenerated = this.matchPickService.generateNewPicks(fixtures);
        assertTrue(matchPicksGenerated.size() > 0);
        */
        //TODO
    }
}
