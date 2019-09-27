package com.acorsetti.core.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.jpa.Fixture;
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
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigWebContextLoader.class)
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class FixtureServiceTest {

    @Autowired
    private FixtureService fixtureService;

    @Test
    public void testFixtureByDay(){
        LocalDate noFixtures = LocalDate.of(1999,3,31);
        LocalDate fixtures = LocalDate.of(2019,6,17);

        assertEquals(0, this.fixtureService.fixturesByDay(noFixtures).size());
        assertTrue( this.fixtureService.fixturesByDay(fixtures).size() > 0 );
    }

    @Test
    public void testById(){
        String idNotPresent = "AAAAPPPPP";
        String idPresent = "121035";

        assertNull(this.fixtureService.byId(idNotPresent));
        assertNotNull(this.fixtureService.byId(idPresent));

    }

    @Test
    public void testLastTeamMatches(){
        String teamId = "505";
        int numOfMatches = 9;
        String[] leagues = {"66","94"};

        List<Fixture> lastMatches = this.fixtureService.lastTeamMatches(teamId,numOfMatches,leagues);
        assertTrue(lastMatches.size() <= numOfMatches && lastMatches.size() > 0);

        //check leagues if of matches in list
        lastMatches.forEach( match -> {
            assertTrue(Arrays.asList(leagues).contains(match.getLeagueId()));
        });

        //check order of matches
        LocalDate tmpDate = lastMatches.get(0).getEventDate().toLocalDate();
        int i = 1;
        while( i < lastMatches.size() ){
            LocalDate currentDate = lastMatches.get(i).getEventDate().toLocalDate();
            assertTrue( currentDate.isBefore(tmpDate) );
            tmpDate = currentDate;
            i++;
        }

        //testLegitHomeFavouriteGoalExpectancy null leagueId list
        lastMatches = this.fixtureService.lastTeamMatches(teamId,numOfMatches);
        assertTrue(lastMatches.size() <= numOfMatches && lastMatches.size() > 0);

        //check order of matches
        tmpDate = lastMatches.get(0).getEventDate().toLocalDate();
        i = 1;
        while( i < lastMatches.size() ){
            LocalDate currentDate = lastMatches.get(i).getEventDate().toLocalDate();
            assertTrue( currentDate.isBefore(tmpDate) );
            tmpDate = currentDate;
            i++;
        }

        //check empty list result
        numOfMatches = 0;
        lastMatches = this.fixtureService.lastTeamMatches(teamId,numOfMatches,leagues);
        assertEquals(0, lastMatches.size());

        //check empty list result
        teamId = "AAABBB";
        numOfMatches = 10;
        lastMatches = this.fixtureService.lastTeamMatches(teamId,numOfMatches,leagues);
        assertEquals(0, lastMatches.size());
    }

    @Test
    public void testCompletedFixture(){
        Fixture completed = this.fixtureService.byId("29044");
        assertTrue(this.fixtureService.isCompleted(completed));

        Fixture cancelled = this.fixtureService.byId("102506");
        assertFalse(this.fixtureService.isCompleted(cancelled));

    }

    @Test
    public void testWinnerTeamId(){
        Fixture one = this.fixtureService.byId("105439");
        Fixture x = this.fixtureService.byId("105540");
        Fixture two = this.fixtureService.byId("105481");
        Fixture notFinished = this.fixtureService.byId("102506");

        assertEquals(one.getHomeTeamId(), this.fixtureService.winnerTeamId(one));
        assertEquals("",this.fixtureService.winnerTeamId(x));
        assertEquals(two.getAwayTeamId(), this.fixtureService.winnerTeamId(two));
        assertEquals("",this.fixtureService.winnerTeamId(notFinished));

    }

    @Test
    public void testLoserTeamId(){
        Fixture one = this.fixtureService.byId("105439");
        Fixture x = this.fixtureService.byId("105540");
        Fixture two = this.fixtureService.byId("105481");
        Fixture notFinished = this.fixtureService.byId("102506");

        assertEquals(one.getAwayTeamId(), this.fixtureService.loserTeamId(one));
        assertEquals("",this.fixtureService.loserTeamId(x));
        assertEquals(two.getHomeTeamId(), this.fixtureService.loserTeamId(two));
        assertEquals("",this.fixtureService.loserTeamId(notFinished));

    }

    @Test
    public void testGoalsForTeam(){
        Fixture one = this.fixtureService.byId("105439");
        Fixture notFinished = this.fixtureService.byId("102506");

        assertEquals(1, this.fixtureService.getTeamGoalsFor(one, one.getHomeTeamId()));
        assertEquals(0, this.fixtureService.getTeamGoalsFor(one, one.getAwayTeamId()));

        assertEquals(0, this.fixtureService.getTeamGoalsFor(notFinished, notFinished.getHomeTeamId()));
        assertEquals(0, this.fixtureService.getTeamGoalsFor(notFinished, notFinished.getAwayTeamId()));
    }

    @Test
    public void testGoalsAgainstTeam(){
        Fixture one = this.fixtureService.byId("105439");
        Fixture notFinished = this.fixtureService.byId("102506");

        assertEquals(0, this.fixtureService.getTeamGoalsConceived(one, one.getHomeTeamId()));
        assertEquals(1, this.fixtureService.getTeamGoalsConceived(one, one.getAwayTeamId()));

        assertEquals(0, this.fixtureService.getTeamGoalsConceived(notFinished, notFinished.getHomeTeamId()));
        assertEquals(0, this.fixtureService.getTeamGoalsConceived(notFinished, notFinished.getAwayTeamId()));
    }

    @Test
    public void testPoints(){
        Fixture one = this.fixtureService.byId("105439");
        Fixture x = this.fixtureService.byId("105540");
        Fixture two = this.fixtureService.byId("105481");
        Fixture notFinished = this.fixtureService.byId("102506");

        assertEquals(3, this.fixtureService.pointsForTeam(one,one.getHomeTeamId()));
        assertEquals(0, this.fixtureService.pointsForTeam(one,one.getAwayTeamId()));

        assertEquals(1, this.fixtureService.pointsForTeam(x,x.getHomeTeamId()));
        assertEquals(1, this.fixtureService.pointsForTeam(x,x.getAwayTeamId()));

        assertEquals(0, this.fixtureService.pointsForTeam(two,two.getHomeTeamId()));
        assertEquals(3, this.fixtureService.pointsForTeam(two,two.getAwayTeamId()));

        assertEquals(0, this.fixtureService.pointsForTeam(notFinished,notFinished.getHomeTeamId()));
        assertEquals(0, this.fixtureService.pointsForTeam(notFinished,notFinished.getAwayTeamId()));
    }

    @Test
    public void testGoalSum(){
        Fixture one = this.fixtureService.byId("105439");
        Fixture x = this.fixtureService.byId("105540");
        Fixture two = this.fixtureService.byId("105481");
        Fixture notFinished = this.fixtureService.byId("102506");

        assertEquals(1, this.fixtureService.goalSum(one));
        assertEquals(0, this.fixtureService.goalSum(x));
        assertEquals(1, this.fixtureService.goalSum(two));
        assertEquals(0, this.fixtureService.goalSum(notFinished));
    }
}
