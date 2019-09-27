package com.acorsetti.core.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.enums.PickResult;
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

import static junit.framework.TestCase.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigWebContextLoader.class)
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class PickResultExtracterServiceTest {

    @Autowired
    private PickResultExtracterService pickResultExtracterService;

    @Autowired
    private FixtureService fixtureService;

    @Test
    public void testPickResultExtraction(){
        Fixture f1 = this.fixtureService.byId("107316");
        Fixture f2 = this.fixtureService.byId("107323");
        Fixture f3 = this.fixtureService.byId("107305");
        Fixture f4 = this.fixtureService.byId("107696");
        Fixture f5 = this.fixtureService.byId("107694");
        Fixture f6 = this.fixtureService.byId("107307");
        Fixture f7 = this.fixtureService.byId("107309");
        Fixture f8 = this.fixtureService.byId("107317");

        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.BTTS_NO));
        assertEquals(PickResult.YES, this.pickResultExtracterService.pickResult(f1, MarketValue.BTTS_YES));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.DC_DRAW_AWAY));
        assertEquals(PickResult.YES, this.pickResultExtracterService.pickResult(f1, MarketValue.DC_HOME_AWAY));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.FOUR_FOUR));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.FOUR_NIL));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.FOUR_ONE));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.FOUR_TWO));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.FOUR_THREE));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.NIL_FOUR));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.NIL_NIL));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.THREE_THREE));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.THREE_ONE));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.THREE_NIL));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.THREE_FOUR));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.ONE_TWO));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.ONE_THREE));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.ONE_ONE));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.ONE_NIL));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.ONE_FOUR));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.NIL_TWO));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.NIL_THREE));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.NIL_ONE));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.TWO_FOUR));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.TWO_NIL));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.TWO_ONE));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.TWO_THREE));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.TWO_TWO));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f1, MarketValue.OTHER));
        assertEquals(PickResult.YES, this.pickResultExtracterService.pickResult(f1, MarketValue.THREE_TWO));

        assertEquals(PickResult.YES, this.pickResultExtracterService.pickResult(f2, MarketValue.DC_HOME_DRAW));

        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f3, MarketValue.HDA_AWAY));

        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f4, MarketValue.HDA_BTTS_AWAY_NO));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f4, MarketValue.HDA_BTTS_AWAY_YES));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f4, MarketValue.HDA_BTTS_DRAW_YES));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f4, MarketValue.O4_5));
        assertEquals(PickResult.YES, this.pickResultExtracterService.pickResult(f4, MarketValue.U4_5));

        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f5, MarketValue.HDA_BTTS_DRAW_NO));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f5, MarketValue.HDA_BTTS_HOME_NO));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f5, MarketValue.HDA_BTTS_HOME_YES));

        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f6, MarketValue.HDA_DRAW));
        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f6, MarketValue.HDA_HOME));

        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f7, MarketValue.O2_5));

        assertEquals(PickResult.NO, this.pickResultExtracterService.pickResult(f8, MarketValue.O3_5));
        assertEquals(PickResult.YES, this.pickResultExtracterService.pickResult(f8, MarketValue.U3_5));
    }
}
