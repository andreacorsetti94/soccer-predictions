package com.acorsetti.core.api;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.eval.TeamStatistics;
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
public class TeamStatisticsRetrieverTest {

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private APITeamStatisticsRetriever apiTeamStatisticsRetriever;

    private String legalJson;

    @Before
    public void setUp(){
        mockServer = MockRestServiceServer.createServer(restTemplate);
        legalJson = "{\"api\":{\"results\":1,\"statistics\":{\"matchs\":{\"matchsPlayed\":{\"home\":19,\"away\":19,\"total\":38},\"wins\":{\"home\":5,\"away\":4,\"total\":9},\"draws\":{\"home\":7,\"away\":7,\"total\":14},\"loses\":{\"home\":7,\"away\":8,\"total\":15}},\"goals\":{\"goalsFor\":{\"home\":23,\"away\":19,\"total\":42},\"goalsAgainst\":{\"home\":28,\"away\":24,\"total\":52}},\"goalsAvg\":{\"goalsFor\":{\"home\":\"1.2\",\"away\":\"1.0\",\"total\":\"1.1\"},\"goalsAgainst\":{\"home\":\"1.5\",\"away\":\"1.3\",\"total\":\"1.4\"}}}}}";
    }

    @Test
    public void testRetrieving(){
        String leagueId = "22";
        String teamId = "77";
        String endpoint = this.environment.getProperty("statsByLeague").replace("<leagueId>", leagueId).replace("<teamId>",teamId);
        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<TeamStatistics> apiResponse = this.apiTeamStatisticsRetriever.getStatsForLeagueAndTeam(leagueId, teamId);
        assertEquals(200, apiResponse.getResponse().value());

        assertEquals(1, apiResponse.getResults());
        List<TeamStatistics> teamStatisticsList = apiResponse.getBody();
        TeamStatistics teamStatistics = teamStatisticsList.get(0);

        assertEquals(19, teamStatistics.getMatchPlayedHome());
        assertEquals(19, teamStatistics.getMatchPlayedAway());
        assertEquals(38, teamStatistics.getMatchPlayedTotal());

        assertEquals(5, teamStatistics.getWinsHome());
        assertEquals(4, teamStatistics.getWinsAway());
        assertEquals(9, teamStatistics.getWinsTotal());

        assertEquals(7, teamStatistics.getDrawsHome());
        assertEquals(7, teamStatistics.getDrawsAway());
        assertEquals(14, teamStatistics.getDrawsTotal());

        assertEquals(7, teamStatistics.getLosesHome());
        assertEquals(8, teamStatistics.getLosesAway());
        assertEquals(15, teamStatistics.getLosesTotal());

        assertEquals(23, teamStatistics.getGoalsForHome());
        assertEquals(19, teamStatistics.getGoalsForAway());
        assertEquals(42, teamStatistics.getGoalsForTotal());

        assertEquals(28, teamStatistics.getGoalsAgainstHome());
        assertEquals(24, teamStatistics.getGoalsAgainstAway());
        assertEquals(52, teamStatistics.getGoalsAgainstTotal());

        assertEquals(1.2, teamStatistics.getAvgGoalsForHome(),0.0);
        assertEquals(1.0, teamStatistics.getAvgGoalsForAway(),0.0);
        assertEquals(1.1, teamStatistics.getAvgGoalsForTotal(),0.0);

        assertEquals(1.5, teamStatistics.getAvgGoalsAgainstHome(),0.0);
        assertEquals(1.3, teamStatistics.getAvgGoalsAgainstAway(),0.0);
        assertEquals(1.4, teamStatistics.getAvgGoalsAgainstTotal(),0.0);

    }
}
