package com.acorsetti.service.probabilities;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.eval.GoalExpectancy;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.model.jpa.FixtureBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StatisticalCalculatorServiceTest {

    @Autowired
    private StatisticalCalculatorService statisticalCalculatorService;

    @Test
    public void test(){
        Fixture f = new FixtureBuilder().withFixtureId("ID1").withHomeTeamId("505").withAwayTeamId("498").build();
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