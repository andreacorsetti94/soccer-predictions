package com.acorsetti.core.service.probabilities;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.enums.MarketType;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.GoalExpectancy;
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
import java.util.Map;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigWebContextLoader.class)
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class ExactScoreProbabilityCalculatorServiceTest {

    @Autowired
    private ExactScoreProbabilityCalculatorService exactScoreProbabilityCalculatorService;

    @Test
    public void testLegitHomeFavouriteGoalExpectancy(){
        GoalExpectancy goalExpectancy = new GoalExpectancy(1.50, 0.5);
        Map<MarketValue, Chance> exactScoreMap = this.exactScoreProbabilityCalculatorService.calculate(goalExpectancy);
        exactScoreMap.forEach((mv,chance) -> {
            assertSame(mv.getType(), MarketType.EXACT_SCORE);
            assertTrue(chance.getValue() <= 0.99 && chance.getValue() >= 0.01);
        });
        assertTrue(exactScoreMap.get(MarketValue.THREE_NIL).getValue() >= exactScoreMap.get(MarketValue.NIL_THREE).getValue());
    }

    @Test
    public void testLegitAwayFavouriteGoalExpectancy(){
        GoalExpectancy goalExpectancy = new GoalExpectancy(0.5, 2);
        Map<MarketValue, Chance> exactScoreMap = this.exactScoreProbabilityCalculatorService.calculate(goalExpectancy);
        exactScoreMap.forEach((mv,chance) -> {
            assertSame(mv.getType(), MarketType.EXACT_SCORE);
            assertTrue(chance.getValue() <= 0.99 && chance.getValue() >= 0.01);
        });
        assertTrue(exactScoreMap.get(MarketValue.THREE_NIL).getValue() <= exactScoreMap.get(MarketValue.NIL_THREE).getValue());
    }

    @Test
    public void testNotLegitGoalExpectancy(){
        GoalExpectancy goalExpectancy = new GoalExpectancy(0.0, 0.0);
        Map<MarketValue, Chance> exactScoreMap = this.exactScoreProbabilityCalculatorService.calculate(goalExpectancy);
        exactScoreMap.forEach((mv,chance) -> {
            assertSame(mv.getType(), MarketType.EXACT_SCORE);
            assertTrue(chance.getValue() <= 0.99 && chance.getValue() >= 0.01);
        });
    }
}
