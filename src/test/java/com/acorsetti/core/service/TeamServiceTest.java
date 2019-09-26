package com.acorsetti.core.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class TeamServiceTest {


    @Autowired
    private TeamService teamService;

    @Test
    public void testAllTeams(){
        assertTrue(this.teamService.listAllTeams().size() > 0);
    }

    @Test
    public void testById(){
        String presentTeam = "505";
        assertNotNull(this.teamService.byId(presentTeam));
        assertNull(this.teamService.byId(null));
    }

    @Test
    public void testAvgGoalsScored(){
        Fixture uno = new FixtureBuilder()
                .withFixtureId("ID1").withHomeTeamId("team1").withAwayTeamId("team2").withGoalsHomeTeam("3").withGoalsAwayTeam("4").build();

        Fixture due = new FixtureBuilder()
                .withFixtureId("ID2").withHomeTeamId("team3").withAwayTeamId("team4").withGoalsHomeTeam("3").withGoalsAwayTeam("4").build();

        Fixture tre = new FixtureBuilder()
                .withFixtureId("ID3").withHomeTeamId("team3").withAwayTeamId("team2").withGoalsHomeTeam("3").withGoalsAwayTeam("4").build();

        Fixture quattro = new FixtureBuilder()
                .withFixtureId("ID4").withHomeTeamId("team1").withAwayTeamId("team4").withGoalsHomeTeam("3").withGoalsAwayTeam("4").build();

        Fixture cinque = new FixtureBuilder()
                .withFixtureId("ID5").withHomeTeamId("team2").withAwayTeamId("team1").withGoalsHomeTeam("3").withGoalsAwayTeam("4").build();

        List<Fixture> fixtureList = Arrays.asList(uno,due,tre,quattro,cinque);
        assertEquals((double)10/3, this.teamService.avgGoalsScored("team1", fixtureList),0.01);
        assertEquals((double)11/3, this.teamService.avgGoalsScored("team2", fixtureList),0.01);
        assertEquals(0.0, this.teamService.avgGoalsScored("team6", fixtureList),0.01);
        assertEquals(0.0, this.teamService.avgGoalsScored("team6", new ArrayList<>()),0.01);

    }

    @Test
    public void testAvgGoalsConceived(){
        Fixture uno = new FixtureBuilder()
                .withFixtureId("ID1").withHomeTeamId("team1").withAwayTeamId("team2").withGoalsHomeTeam("3").withGoalsAwayTeam("4").build();

        Fixture due = new FixtureBuilder()
                .withFixtureId("ID2").withHomeTeamId("team3").withAwayTeamId("team4").withGoalsHomeTeam("3").withGoalsAwayTeam("4").build();

        Fixture tre = new FixtureBuilder()
                .withFixtureId("ID3").withHomeTeamId("team3").withAwayTeamId("team2").withGoalsHomeTeam("3").withGoalsAwayTeam("4").build();

        Fixture quattro = new FixtureBuilder()
                .withFixtureId("ID4").withHomeTeamId("team1").withAwayTeamId("team4").withGoalsHomeTeam("3").withGoalsAwayTeam("4").build();

        Fixture cinque = new FixtureBuilder()
                .withFixtureId("ID5").withHomeTeamId("team2").withAwayTeamId("team1").withGoalsHomeTeam("3").withGoalsAwayTeam("4").build();

        List<Fixture> fixtureList = Arrays.asList(uno,due,tre,quattro,cinque);
        assertEquals((double)11/3, this.teamService.avgGoalsConceived("team1", fixtureList),0.01);
        assertEquals((double)10/3, this.teamService.avgGoalsConceived("team2", fixtureList),0.01);
        assertEquals(0.0, this.teamService.avgGoalsConceived("team6", fixtureList),0.01);
        assertEquals(0.0, this.teamService.avgGoalsConceived("team6", new ArrayList<>()),0.01);

    }
}
