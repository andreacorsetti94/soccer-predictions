package com.acorsetti.api;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.jpa.Fixture;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
public class FixtureApiRetrieverTest {

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private APIFixtureRetriever apiFixtureRetriever;

    private String legalJson;

    @Before
    public void setUp(){
        mockServer = MockRestServiceServer.createServer(restTemplate);
        legalJson = "{\"api\":{\"results\":518,\"fixtures\":{\"169134\":{\"fixture_id\":\"169134\"," +
                "\"event_timestamp\":\"1567209600\",\"event_date\":\"2019-08-31T00:00:00+00:00\"," +
                "\"league_id\":\"584\",\"round\":\"Apertura - 8\",\"homeTeam_id\":\"2284\",\"awayTeam_id\"" +
                ":\"2294\",\"homeTeam\":\"Monarcas\",\"awayTeam\":\"Veracruz\",\"status\":\"Match Finished" +
                "\",\"statusShort\":\"FT\",\"goalsHomeTeam\":\"1\",\"goalsAwayTeam\":\"0\",\"halftime_score" +
                "\":\"0-0\",\"final_score\":\"1 - 0\",\"penalty\":\"-\",\"elapsed\":\"0\",\"firstHalfStart\":" +
                "\"1567209600\",\"secondHalfStart\":\"1567213200\"},\"169737\":{\"fixture_id\":\"169737\",\"event_timestamp" +
                "\":\"1567209600\",\"event_date\":\"2019-08-31T00:00:00+00:00\",\"league_id\":\"587\",\"round\":" +
                "\"Apertura - 5\",\"homeTeam_id\":\"2304\",\"awayTeam_id\":\"2310\",\"homeTeam\":\"Tampico Madero\"," +
                "\"awayTeam\":\"Tapachula\",\"status\":\"Match Finished\",\"statusShort\":\"FT\",\"goalsHomeTeam\":\"2" +
                "\",\"goalsAwayTeam\":\"2\",\"halftime_score\":\"1-1\",\"final_score\":\"2 - 2\",\"penalty\":\"-\",\"" +
                "elapsed\":\"0\",\"firstHalfStart\":\"1567209600\",\"secondHalfStart\":\"1567213200\"}}}}";
    }

    @Test
    public void testFixturesByDate(){
        LocalDate localDate = LocalDate.of(2019,8,31);
        String format = this.environment.getProperty("fixture.date.format");
        String endpoint = this.environment.getProperty("fixturesByDay").replace("<date>", localDate.format(DateTimeFormatter.ofPattern(format)));
        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<Fixture> apiResponse = this.apiFixtureRetriever.byDay(localDate);
        this.checkMockServerResponse(apiResponse);
    }

    @Test
    public void testLiveFixtures(){
        String endpoint = this.environment.getProperty("liveFixtures");
        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<Fixture> apiResponse = this.apiFixtureRetriever.live();
        this.checkMockServerResponse(apiResponse);
    }

    @Test
    public void testByLeague(){
        String leagueId = "584";
        String endpoint = this.environment.getProperty("fixturesByLeague").replace("<leagueId>",leagueId);

        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<Fixture> apiResponse = this.apiFixtureRetriever.byLeague(leagueId);
        this.checkMockServerResponse(apiResponse);
    }

    @Test
    public void testByTeam(){
        String teamId = "505";
        String endpoint = this.environment.getProperty("fixturesByTeam").replace("<teamId>", teamId);

        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<Fixture> apiResponse = this.apiFixtureRetriever.byTeam(teamId);
        this.checkMockServerResponse(apiResponse);
    }

    @Test
    public void testById(){
        String id = "1000";
        String endpoint = this.environment.getProperty("fixturesById").replace("<id>", id);

        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<Fixture> apiResponse = this.apiFixtureRetriever.byId(id);
        this.checkMockServerResponse(apiResponse);
    }

    @Test
    public void testByH2H(){
        String teamOne = "505";
        String teamTwo = "504";
        String endpoint = this.environment.getProperty("fixturesByH2H")
                .replace("<teamOne>", teamOne)
                .replace("<teamTwo>", teamTwo);
        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<Fixture> apiResponse = this.apiFixtureRetriever.byH2H(teamOne, teamTwo);
        this.checkMockServerResponse(apiResponse);
    }

    private void checkMockServerResponse(APIResponse<Fixture> apiResponse){

        assertEquals(200, apiResponse.getResponse().value());
        assertEquals(2, apiResponse.getResults());
        Fixture one = apiResponse.getBody().get(0);
        Fixture two = apiResponse.getBody().get(1);

        assertEquals("169134",one.getFixtureId());
        assertEquals("2284",one.getHomeTeamId());
        assertEquals("2294",one.getAwayTeamId());
        assertEquals("Monarcas",one.getHomeTeamName());
        assertEquals("Veracruz",one.getAwayTeamName());
        assertEquals(LocalDateTime.of(2019,8,31,0,0), one.getEventDate());
        assertEquals("1",one.getGoalsHomeTeam());
        assertEquals("0",one.getGoalsAwayTeam());
        assertEquals("584",one.getLeagueId());
        assertEquals("Match Finished",one.getStatus());
        assertEquals("FT",one.getStatusShort());
        assertEquals("Apertura - 8",one.getRound());
        assertEquals("1 - 0",one.getFinalScore());

        assertEquals("169737",two.getFixtureId());
        assertEquals("2304",two.getHomeTeamId());
        assertEquals("2310",two.getAwayTeamId());
        assertEquals("Tampico Madero",two.getHomeTeamName());
        assertEquals("Tapachula",two.getAwayTeamName());
        assertEquals(LocalDateTime.of(2019,8,31,0,0), two.getEventDate());
        assertEquals("2",two.getGoalsHomeTeam());
        assertEquals("2",two.getGoalsAwayTeam());
        assertEquals("587",two.getLeagueId());
        assertEquals("Match Finished",two.getStatus());
        assertEquals("FT",two.getStatusShort());
        assertEquals("Apertura - 5",two.getRound());
        assertEquals("2 - 2",two.getFinalScore());
    }
}
