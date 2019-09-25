package com.acorsetti.api;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.jpa.League;
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
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
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
@ActiveProfiles("test")
public class LeagueApiRetrieverTest {

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private APILeagueRetriever apiLeagueRetriever;

    private String legalJson;

    @Before
    public void setUp(){
        mockServer = MockRestServiceServer.createServer(restTemplate);
        legalJson = "{\"api\":{\"results\":946,\"leagues\":{\"1\":{\"league_id\":\"1\",\"name\"" +
                ":\"World Cup\",\"country\":\"World\",\"country_code\":\"\",\"season\":\"2018\",\"" +
                "season_start\":\"2018-06-14\",\"season_end\":\"2018-07-15\",\"logo\":\"https:\\/\\" +
                "/media.api-football.com\\/leagues\\/1.png\",\"flag\":\"\",\"standings\":false},\"2" +
                "\":{\"league_id\":\"2\",\"name\":\"Premier League\",\"country\":\"England\",\"" +
                "country_code\":\"GB\",\"season\":\"2018\",\"season_start\":\"2018-08-10\",\"season_end" +
                "\":\"2019-05-12\",\"logo\":\"https:\\/\\/media.api-football.com\\/leagues\\/2.png\"," +
                "\"flag\":\"https:\\/\\/media.api-football.com\\/flags\\/gb.svg\",\"standings\":true}" +
                ",\"3\":{\"league_id\":\"3\",\"name\":\"Championship\",\"country\":\"England\",\"" +
                "country_code\":\"GB\",\"season\":\"2018\",\"season_start\":\"2018-08-03\",\"season_end" +
                "\":\"2019-05-27\",\"logo\":\"https:\\/\\/media.api-football.com\\/leagues\\/3.png\",\"" +
                "flag\":\"https:\\/\\/media.api-football.com\\/flags\\/gb.svg\",\"standings\":true}}}}}";
    }

    @Test
    public void testById(){
        String id = "1";
        String endpoint = this.environment.getProperty("leaguesById").replace("<leagueId>", id);

        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<League> apiResponse = this.apiLeagueRetriever.byId(id);
        this.checkMockServerResponse(apiResponse);
    }


    @Test
    public void testAllLeagues(){
        String endpoint = this.environment.getProperty("allLeagues");
        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<League> apiResponse = this.apiLeagueRetriever.allLeagues();
        this.checkMockServerResponse(apiResponse);
    }


    @Test
    public void testByYear(){
        String year = "2018";
        String endpoint = this.environment.getProperty("leaguesByYear").replace("<year>", year);
        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<League> apiResponse = this.apiLeagueRetriever.byYear(year);
        this.checkMockServerResponse(apiResponse);
    }


    @Test
    public void testByCountry(){
        String country = "England";
        String endpoint = this.environment.getProperty("leaguesByCountry").replace("<country>", country);
        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<League> apiResponse = this.apiLeagueRetriever.byCountry(country);
        this.checkMockServerResponse(apiResponse);
    }


    @Test
    public void testByCountryAndYear(){
        String country = "England";
        String year = "2018";
        String endpoint = this.environment.getProperty("leaguesByCountryAndYear")
                .replace("<country>", country)
                .replace("<year>", year);
        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<League> apiResponse = this.apiLeagueRetriever.byCountryAndYear(country, year);
        this.checkMockServerResponse(apiResponse);
    }

    private void checkMockServerResponse(APIResponse<League> apiResponse){
        assertEquals(200, apiResponse.getResponse().value());
        assertEquals(3, apiResponse.getResults());

        List<League> leagueList = apiResponse.getBody();
        League one = leagueList.get(0);
        League two = leagueList.get(1);
        League three = leagueList.get(2);

        assertEquals("1", one.getLeagueId());
        assertEquals("2", two.getLeagueId());
        assertEquals("3", three.getLeagueId());

        assertEquals("World Cup", one.getLeagueName());
        assertEquals("Premier League", two.getLeagueName());
        assertEquals("Championship", three.getLeagueName());

        assertEquals("World", one.getCountryName());
        assertEquals("England", two.getCountryName());
        assertEquals("England", three.getCountryName());

        assertEquals("2018", one.getSeasonYear());
        assertEquals("2018", two.getSeasonYear());
        assertEquals("2018", three.getSeasonYear());

        assertEquals("https://media.api-football.com/leagues/1.png", one.getSeasonLogoUrl());
        assertEquals("https://media.api-football.com/leagues/2.png", two.getSeasonLogoUrl());
        assertEquals("https://media.api-football.com/leagues/3.png", three.getSeasonLogoUrl());

        assertEquals("2018-06-14", one.getSeasonStartDate());
        assertEquals("2018-08-10", two.getSeasonStartDate());
        assertEquals("2018-08-03", three.getSeasonStartDate());
        assertEquals("2018-07-15", one.getSeasonEndDate());
        assertEquals("2019-05-12", two.getSeasonEndDate());
        assertEquals("2019-05-27", three.getSeasonEndDate());
    }
}
