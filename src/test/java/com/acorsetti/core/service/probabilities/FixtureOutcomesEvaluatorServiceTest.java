package com.acorsetti.core.service.probabilities;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.enums.MarketType;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.FixtureEvals;
import com.acorsetti.core.model.eval.FixtureOutcomes;
import com.acorsetti.core.model.eval.MarketOutcome;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class FixtureOutcomesEvaluatorServiceTest {

    @Autowired
    private FixtureOutcomesEvaluatorService fixtureOutcomesEvaluatorService;

    @Test
    public void test(){
        MarketOutcome mo1 = new MarketOutcome(MarketType.HDA, MarketValue.HDA_AWAY);
        MarketOutcome mo2 = new MarketOutcome(MarketType.HDA, MarketValue.HDA_DRAW);
        MarketOutcome mo3 = new MarketOutcome(MarketType.HDA, MarketValue.HDA_HOME);

        FixtureOutcomes fo = new FixtureOutcomes("76263", Arrays.asList(mo1,mo2,mo3));
        FixtureEvals fixtureEvals = this.fixtureOutcomesEvaluatorService.evaluate(fo);

        assertEquals(3, fixtureEvals.getOutcomeEvaluations().size());
        assertEquals("76263",fixtureEvals.getFixtureId());

        fixtureEvals.getOutcomeEvaluations().forEach( oe -> {
            assertTrue(oe.getChance().getValue() >= 0.01 && oe.getChance().getValue() <= 0.99
                    && oe.getMarketOutcome().getMarketType() == MarketType.HDA);
        });
    }

    @Test
    public void testNotLegitFixtureOutcomes(){
        FixtureEvals fixtureEvals = this.fixtureOutcomesEvaluatorService.evaluate(null);
        assertEquals(0,fixtureEvals.getOutcomeEvaluations().size());

        fixtureEvals = this.fixtureOutcomesEvaluatorService.evaluate(new FixtureOutcomes("", Collections.emptyList()));
        assertEquals(0,fixtureEvals.getOutcomeEvaluations().size());

    }
}
