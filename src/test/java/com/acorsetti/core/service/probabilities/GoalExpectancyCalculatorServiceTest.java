package com.acorsetti.core.service.probabilities;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.FixtureBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigWebContextLoader.class)
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class GoalExpectancyCalculatorServiceTest {

    @Autowired
    @Qualifier("GoalExpectancyCalculatorServiceWeighted")
    private GoalExpectancyCalculatorService goalExpectancyCalculatorService;

    @Test
    public void test(){
        Fixture f = new FixtureBuilder().withFixtureId("ID1").withHomeTeamId("505").withAwayTeamId("498").withLeagueId("713").build();
        GoalExpectancy ge = this.goalExpectancyCalculatorService.calculateExpectancy(f);
        assertTrue(ge.getHomeValue() > 0.0 && ge.getAwayValue() > 0.0);
    }

    @Test
    public void testNotValidInput(){
        GoalExpectancy ge;
        try{
            ge = this.goalExpectancyCalculatorService.calculateExpectancy(null);
            assertTrue(ge.getHomeValue() > 0.0 && ge.getAwayValue() > 0.0);
        }
        catch (IllegalArgumentException e){
            //OK, exception catched
            return;
        }
        fail("test case should have catched the illegal argument exception for null fixture argument");

    }

}
