package com.acorsetti.core.api;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.jpa.Season;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@PropertySource("classpath:endpoints.properties")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
        classes = { HibernateConfigTest.class, SpringDataApplication.class},
        loader = AnnotationConfigWebContextLoader.class)
@Transactional
@WebAppConfiguration
@ActiveProfiles("test")
public class SeasonApiRetrieverTest {

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private APISeasonRetriever apiSeasonRetriever;

    private String legalJson;

    @Before
    public void setUp(){
        mockServer = MockRestServiceServer.createServer(restTemplate);
        legalJson = "{\n" +
                "    \"api\": {\n" +
                "        \"results\": 11,\n" +
                "        \"seasons\": {\n" +
                "            \"1\": \"2008\",\n" +
                "            \"2\": \"2010\",\n" +
                "            \"3\": \"2011\",\n" +
                "            \"4\": \"2012\"}}}";
    }

    @Test
    public void testAllSeasons(){
        String endpoint = this.environment.getProperty("allSeasons");

        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<Season> apiResponse = this.apiSeasonRetriever.allSeasons();
        assertEquals(200, apiResponse.getResponse().value());
        assertEquals(4, apiResponse.getResults());
        List<Season> seasons = apiResponse.getBody();

        assertEquals("2008", seasons.get(0).getYear());
        assertEquals("2010", seasons.get(1).getYear());
        assertEquals("2011", seasons.get(2).getYear());
        assertEquals("2012", seasons.get(3).getYear());
    }
}
