package com.acorsetti.core.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.enums.MarketType;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.*;
import com.acorsetti.core.model.jpa.MatchPick;
import com.acorsetti.core.model.odds.FixtureOdds;
import com.acorsetti.core.model.odds.MarketOdds;
import com.acorsetti.core.model.odds.OddsValue;
import org.junit.Before;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigWebContextLoader.class)
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class MatchPickBuilderServiceTest {

    @Autowired
    private MatchPickBuilderService matchPickBuilderService;

    private FixtureEvals fixtureEvals;
    private FixtureOdds fixtureOdds;
    private MatchPick matchPick1;
    private MatchPick matchPick2;
    private MatchPick matchPick3;

    @Before
    public void setUp(){
        MarketOutcome mo1 = new MarketOutcome(MarketType.HDA, MarketValue.HDA_AWAY);
        MarketOutcome mo2 = new MarketOutcome(MarketType.BTTS, MarketValue.BTTS_YES);
        MarketOutcome mo3 = new MarketOutcome(MarketType.BTTS, MarketValue.BTTS_NO);
        OutcomeEvaluation oe1 = new OutcomeEvaluation(mo1, new Chance(0.3));
        OutcomeEvaluation oe2 = new OutcomeEvaluation(mo2, new Chance(0.02));
        OutcomeEvaluation oe3 = new OutcomeEvaluation(mo3, new Chance(0.98));
        this.fixtureEvals = new FixtureEvals("prova", new ArrayList<>(Arrays.asList(oe1,oe2,oe3)));

        MarketOdds modd1 = new MarketOdds(MarketType.HDA, MarketValue.HDA_AWAY, new OddsValue(1.34));
        MarketOdds modd2 = new MarketOdds(MarketType.BTTS, MarketValue.BTTS_YES, new OddsValue(1.71));
        MarketOdds modd3 = new MarketOdds(MarketType.BTTS, MarketValue.BTTS_NO, new OddsValue(2.15));
        this.fixtureOdds = new FixtureOdds("prova", new ArrayList<>(Arrays.asList(modd1,modd2,modd3)));

        this.matchPick1 = new MatchPick("prova",MarketValue.HDA_AWAY,
                new OddsValue(1.34), new Chance(0.3), new PickValue(-0.45));

        this.matchPick2 = new MatchPick("prova",MarketValue.BTTS_YES,
                new OddsValue(1.71), new Chance(0.02), new PickValue(-0.56));

        this.matchPick3 = new MatchPick("prova",MarketValue.BTTS_NO,
                new OddsValue(2.15), new Chance(0.98), new PickValue(0.51));

    }

    @Test
    public void testMatchPickBuilding(){

        assertEquals(0,this.matchPickBuilderService.buildMatchPicks(null,null).size());

        List<MatchPick> matchPickList = this.matchPickBuilderService.buildMatchPicks(fixtureEvals,fixtureOdds);
        assertEquals(matchPick1, matchPickList.get(0));
        assertEquals(matchPick2, matchPickList.get(1));
        assertEquals(matchPick3, matchPickList.get(2));

        matchPickList = this.matchPickBuilderService.buildMatchPicks(new FixtureEvals("p1",null),fixtureOdds);
        assertEquals(0,matchPickList.size());


    }
}
