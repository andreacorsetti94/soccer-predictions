package com.acorsetti.core.api;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.jpa.LeagueTeam;
import com.acorsetti.core.model.jpa.Team;
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
public class TeamApiRetrieverTest {

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private APITeamRetriever apiTeamRetriever;

    private String legalJson;

    @Before
    public void setUp() {
        mockServer = MockRestServiceServer.createServer(restTemplate);
        legalJson = "{\"api\":{\"results\":18,\"teams\":{\"210\":{\"team_id\":\"210\"," +
                "\"name\":\"Heerenveen\",\"code\":\"\",\"logo\"" +
                ":\"https:\\/\\/media.api-football.com\\/teams\\/210.png\"}," +
                "\"200\":{\"team_id\":\"200\",\"name\":\"Vitesse\",\"code\":\"\"," +
                "\"logo\":\"https:\\/\\/media.api-football.com\\/teams\\/200.png\"}}}}";
    }

    @Test
    public void testByTeamId(){
        String id = "210";
        String endpoint = this.environment.getProperty("teamsById").replace("<teamId>",id);

        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<Team> apiResponse = this.apiTeamRetriever.byId(id);
        assertEquals(200, apiResponse.getResponse().value());

        assertEquals(2, apiResponse.getResults());
        List<Team> teams = apiResponse.getBody();
        Team one = teams.get(0);
        Team two = teams.get(1);
        assertEquals("210",one.getTeamId());
        assertEquals("200",two.getTeamId());
        assertEquals("Heerenveen",one.getTeamName());
        assertEquals("Vitesse",two.getTeamName());
        assertEquals("https://media.api-football.com/teams/210.png",one.getImgUrl());
        assertEquals("https://media.api-football.com/teams/200.png",two.getImgUrl());


    }

    @Test
    public void testByLeagueId(){
        String id = "718";
        String endpoint = this.environment.getProperty("teamsByLeagueId").replace("<leagueId>",id);

        mockServer
                .expect(requestTo(endpoint))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<LeagueTeam> apiResponse = this.apiTeamRetriever.byLeagueId(id);
        assertEquals(200, apiResponse.getResponse().value());

        assertEquals(2, apiResponse.getResults());
        List<LeagueTeam> leagueTeams = apiResponse.getBody();
        LeagueTeam one = leagueTeams.get(0);
        LeagueTeam two = leagueTeams.get(1);
        assertEquals("210",one.getTeamId());
        assertEquals("200",two.getTeamId());
        assertEquals("718", one.getLeagueId());
        assertEquals("718", two.getLeagueId());
    }
}
