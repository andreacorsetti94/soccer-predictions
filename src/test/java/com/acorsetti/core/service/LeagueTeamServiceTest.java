package com.acorsetti.core.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class LeagueTeamServiceTest {

    @Autowired
    private LeagueTeamService leagueTeamService;

    @Test
    public void testTeamsInLeague(){
        String emptyLeagueId = "AAAALLLL";
        String presentLeagueId = "100";

        assertEquals(0, this.leagueTeamService.teamsInLeague(emptyLeagueId).size());
        assertTrue( this.leagueTeamService.teamsInLeague(presentLeagueId).size() > 0);
    }
}
