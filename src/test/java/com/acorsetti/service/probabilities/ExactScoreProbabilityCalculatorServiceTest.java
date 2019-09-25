package com.acorsetti.service.probabilities;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.enums.MarketType;
import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.eval.Chance;
import com.acorsetti.model.eval.GoalExpectancy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;
import java.util.Map;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
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
            System.out.println("mv: " + mv + " - chance: " + chance.getValue());
            assertSame(mv.getType(), MarketType.EXACT_SCORE);
            assertTrue(chance.getValue() <= 0.99 && chance.getValue() >= 0.01);
        });
    }
}
