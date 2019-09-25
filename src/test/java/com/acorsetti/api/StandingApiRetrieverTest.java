package com.acorsetti.api;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.config.HibernateConfigTest;
import com.acorsetti.model.jpa.StandingPosition;
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
public class StandingApiRetrieverTest {

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private APIStandingsRetriever apiStandingsRetriever;

    private String legalJson;

    @Before
    public void setUp(){
        mockServer = MockRestServiceServer.createServer(restTemplate);
        legalJson = "{\n" +
                "    \"api\": {\n" +
                "        \"results\": 1,\n" +
                "        \"standings\": [\n" +
                "            [\n" +
                "                {\n" +
                "                    \"rank\": \"1\",\n" +
                "                    \"team_id\": \"489\",\n" +
                "                    \"teamName\": \"AC Milan\",\n" +
                "                    \"matchsPlayed\": \"38\",\n" +
                "                    \"win\": \"24\",\n" +
                "                    \"draw\": \"10\",\n" +
                "                    \"lose\": \"4\",\n" +
                "                    \"goalsFor\": \"65\",\n" +
                "                    \"goalsAgainst\": \"24\",\n" +
                "                    \"goalsDiff\": \"+41\",\n" +
                "                    \"points\": \"82\",\n" +
                "                    \"group\": \"Serie A\",\n" +
                "                    \"lastUpdate\": \"2018-02-15\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"rank\": \"2\",\n" +
                "                    \"team_id\": \"505\",\n" +
                "                    \"teamName\": \"Inter\",\n" +
                "                    \"matchsPlayed\": \"38\",\n" +
                "                    \"win\": \"23\",\n" +
                "                    \"draw\": \"7\",\n" +
                "                    \"lose\": \"8\",\n" +
                "                    \"goalsFor\": \"69\",\n" +
                "                    \"goalsAgainst\": \"42\",\n" +
                "                    \"goalsDiff\": \"+27\",\n" +
                "                    \"points\": \"76\",\n" +
                "                    \"group\": \"Serie A\",\n" +
                "                    \"lastUpdate\": \"2018-02-15\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"rank\": \"3\",\n" +
                "                    \"team_id\": \"492\",\n" +
                "                    \"teamName\": \"Napoli\",\n" +
                "                    \"matchsPlayed\": \"38\",\n" +
                "                    \"win\": \"21\",\n" +
                "                    \"draw\": \"7\",\n" +
                "                    \"lose\": \"10\",\n" +
                "                    \"goalsFor\": \"59\",\n" +
                "                    \"goalsAgainst\": \"39\",\n" +
                "                    \"goalsDiff\": \"+20\",\n" +
                "                    \"points\": \"70\",\n" +
                "                    \"group\": \"Serie A\",\n" +
                "                    \"lastUpdate\": \"2018-02-15\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"rank\": \"4\",\n" +
                "                    \"team_id\": \"494\",\n" +
                "                    \"teamName\": \"Udinese\",\n" +
                "                    \"matchsPlayed\": \"38\",\n" +
                "                    \"win\": \"20\",\n" +
                "                    \"draw\": \"6\",\n" +
                "                    \"lose\": \"12\",\n" +
                "                    \"goalsFor\": \"65\",\n" +
                "                    \"goalsAgainst\": \"43\",\n" +
                "                    \"goalsDiff\": \"+22\",\n" +
                "                    \"points\": \"66\",\n" +
                "                    \"group\": \"Serie A\",\n" +
                "                    \"lastUpdate\": \"2018-02-15\"\n" +
                "                }]]}}";
    }

    @Test
    public void testStandings(){
        String leagueId = "718";
        String endpoint = this.environment.getProperty("standingsByLeague").replace("<leagueId>", leagueId);
        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<StandingPosition> apiResponse = this.apiStandingsRetriever.standingsByLeague(leagueId);
        assertEquals(200, apiResponse.getResponse().value());
        assertEquals(4, apiResponse.getResults());

        List<StandingPosition> standingPositionList = apiResponse.getBody();
        StandingPosition first = standingPositionList.get(0);
        assertEquals("718", first.getLeagueId());
        assertEquals(1, first.getPosition());
        assertEquals("AC Milan", first.getTeamName());
        assertEquals(38, first.getPlayed());
        assertEquals(24, first.getWon());
        assertEquals(10, first.getDraw());
        assertEquals(4, first.getLost());
        assertEquals(82, first.getPoints());
        assertEquals("Serie A", first.getGroupName());
        assertEquals(LocalDate.of(2018, 2, 15), first.getLastUpd());
    }
}
