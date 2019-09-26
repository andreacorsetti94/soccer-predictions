package com.acorsetti.core.service.probabilities;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.FixtureBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class StatisticalCalculatorServiceTest {

    @Autowired
    private StatisticalCalculatorService statisticalCalculatorService;

    @Test
    public void test(){
        Fixture f = new FixtureBuilder().withFixtureId("ID1").withHomeTeamId("505").withAwayTeamId("498").withLeagueId("713").build();
        GoalExpectancy ge = this.statisticalCalculatorService.calculateExpectancy(f);
        assertTrue(ge.getHomeValue() > 0.0 && ge.getAwayValue() > 0.0);
    }

    @Test
    public void testNotValidInput(){
        GoalExpectancy ge;
        try{
            ge = this.statisticalCalculatorService.calculateExpectancy(null);
            assertTrue(ge.getHomeValue() > 0.0 && ge.getAwayValue() > 0.0);
        }
        catch (IllegalArgumentException e){
            //OK, exception catched
            return;
        }
        fail("test case should have catched the illegal argument exception for null fixture argument");

    }

}
