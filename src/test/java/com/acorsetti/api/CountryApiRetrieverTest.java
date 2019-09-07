package com.acorsetti.api;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.api.response.CountryResponse;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.jpa.Country;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
public class CountryApiRetrieverTest {

    @Autowired
    private APICountryRetriever apiCountryRetriever;

    @Test
    public void testLegalEndpoint(){
        CountryResponse countryResponse = this.apiCountryRetriever.allCountriesByAPI();
        assertEquals(200, countryResponse.getResponse().value());

        assertTrue( countryResponse.getResults() > 1000);

        List<Country> countryList = countryResponse.getBody();
        assertTrue( countryList.size() > 0 );

        Country randomCountry1 = countryList.get(new Random().nextInt(countryList.size()));
        assertTrue( randomCountry1.getCountryId() != null && !randomCountry1.getCountryId().isEmpty());
        assertTrue( randomCountry1.getCountryName() != null && !randomCountry1.getCountryName().isEmpty());

    }
}
