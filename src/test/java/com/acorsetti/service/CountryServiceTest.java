package com.acorsetti.service;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class CountryServiceTest {

    @Autowired
    private CountryService countryService;

    @Test
    public void testDBAllCountries(){
        assertTrue( this.countryService.allCountries().size() > 0);
    }
}
