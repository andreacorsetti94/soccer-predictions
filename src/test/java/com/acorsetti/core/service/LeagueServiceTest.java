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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles("test")
public class LeagueServiceTest {

    @Autowired
    private LeagueService leagueService;

    @Test
    public void allLeagues(){
        assertTrue( this.leagueService.listLeagues().size() > 0);
    }

    @Test
    public void testById(){
        String presentId = "10";
        String notPresentId = "asdf1012e";
        assertNotNull(this.leagueService.byId(presentId));
        assertNull(this.leagueService.byId(notPresentId));
    }
}
