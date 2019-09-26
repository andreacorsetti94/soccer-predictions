package com.acorsetti.core.service.probabilities;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.eval.TeamsForm;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.FixtureBuilder;
import org.junit.Assert;
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
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class TeamFormCalculatorServiceTest {

    @Autowired
    private TeamFormCalculatorService teamFormCalculatorService;

    @Test
    public void test(){
        Fixture f1 = new FixtureBuilder().withFixtureId("F1").withHomeTeamId("505")
                .withAwayTeamId("498").withGoalsHomeTeam("5").withGoalsAwayTeam("3")
                .withStatusShort("FT").build();
        Fixture f2 = new FixtureBuilder().withFixtureId("F1").withHomeTeamId("505")
                .withAwayTeamId("122").withGoalsHomeTeam("0").withGoalsAwayTeam("2")
                .withStatusShort("FT").build();
        Fixture f3 = new FixtureBuilder().withFixtureId("F1").withHomeTeamId("111")
                .withAwayTeamId("505").withGoalsHomeTeam("2").withGoalsAwayTeam("1")
                .withStatusShort("FT").build();


        Fixture f4 = new FixtureBuilder().withFixtureId("F1").withHomeTeamId("505")
                .withAwayTeamId("498").withGoalsHomeTeam("3").withGoalsAwayTeam("5")
                .withStatusShort("FT").build();
        Fixture f5 = new FixtureBuilder().withFixtureId("F1").withHomeTeamId("498")
                .withAwayTeamId("122").withGoalsHomeTeam("6").withGoalsAwayTeam("2")
                .withStatusShort("FT").build();
        Fixture f6 = new FixtureBuilder().withFixtureId("F1").withHomeTeamId("111")
                .withAwayTeamId("498").withGoalsHomeTeam("4").withGoalsAwayTeam("1")
                .withStatusShort("FT").build();

        List<Fixture> fixtures505 = Arrays.asList(f1,f2,f3);
        List<Fixture> fixtures498 = Arrays.asList(f4,f5,f6);

        TeamsForm tf = this.teamFormCalculatorService.calculateTeamsForm("505","498", fixtures505,fixtures498);

        assertTrue(tf.getHomeTeamForm() > 0.0 && tf.getAwayTeamForm() > 0.0);
    }

    @Test
    public void testNotValidInputs(){

        TeamsForm tf = this.teamFormCalculatorService.calculateTeamsForm(null,null, Collections.emptyList(),Collections.emptyList());
        Assert.assertEquals(0.0, tf.getHomeTeamForm(),0.0);
        Assert.assertEquals(0.0, tf.getAwayTeamForm(),0.0);
    }
}
