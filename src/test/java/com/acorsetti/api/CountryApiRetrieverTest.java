package com.acorsetti.api;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.jpa.Country;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigContextLoader.class)
@Transactional
@Configuration
@PropertySource("classpath:endpoints.properties")
public class CountryApiRetrieverTest {

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private APICountryRetriever apiCountryRetriever;

    private String legalJson;

    @Before
    public void setUp(){
        mockServer = MockRestServiceServer.createServer(restTemplate);
        legalJson = "{\"api\":{\"results\":124,\"countries\":{\"1\":\"Albania\",\"2\":\"Algeria\",\"3\":\"Andorra\"}}}";
    }

    @Test
    public void test(){
        String url = this.environment.getProperty("allCountries");
        mockServer
                .expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<Country> countryResponse = this.apiCountryRetriever.allCountries();
        assertEquals(200, countryResponse.getResponse().value());

        assertEquals(3, countryResponse.getResults());
        List<Country> countryList = countryResponse.getBody();

        Country albania = countryList.get(0);
        Country algeria = countryList.get(1);
        Country andorra = countryList.get(2);

        assertTrue( albania.getCountryName().equals("Albania") && albania.getCountryId().equals("1") );
        assertTrue( algeria.getCountryName().equals("Algeria") && algeria.getCountryId().equals("2") );
        assertTrue( andorra.getCountryName().equals("Andorra") && andorra.getCountryId().equals("3") );

    }

}
