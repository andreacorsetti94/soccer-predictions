package com.acorsetti.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.jpa.Season;
import com.acorsetti.repository.SeasonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
