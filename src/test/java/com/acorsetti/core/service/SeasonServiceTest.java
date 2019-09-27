package com.acorsetti.core.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.repository.SeasonRepository;
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

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigWebContextLoader.class)
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class SeasonServiceTest {

    @Autowired
    private SeasonService seasonService;

    @Autowired
    private SeasonRepository seasonRepository;

    @Test
    public void testAllSeasons(){
        assertTrue(this.seasonService.allSeasons().size() > 0);
    }

    @Test
    public void testByYear(){
        String year = "2019";
        String notAYear = "PPP";

        assertNotNull(this.seasonService.byYear(year));
        assertNull(this.seasonService.byYear(notAYear));

    }
}
