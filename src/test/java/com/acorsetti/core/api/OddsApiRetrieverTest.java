package com.acorsetti.core.api;

import com.acorsetti.SpringDataApplication;
import com.acorsetti.core.config.HibernateConfigTest;
import com.acorsetti.core.model.odds.FixtureOdds;
import com.acorsetti.core.model.odds.MarketOdds;
import com.acorsetti.core.model.odds.OddsValue;
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
public class OddsApiRetrieverTest {

    private MockRestServiceServer mockServer;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Environment environment;

    @Autowired
    private APIOddsRetriever apiOddsRetriever;

    private String legalJson;

    @Before
    public void setUp(){
        mockServer = MockRestServiceServer.createServer(restTemplate);
        legalJson = "{\n" +
                "  \"api\": {\n" +
                "    \"results\": 23,\n" +
                "    \"odds\": {\n" +
                "      \"Win the match\": {\n" +
                "        \"1\": {\n" +
                "          \"label\": \"1\",\n" +
                "          \"pos\": \"1\",\n" +
                "          \"odd\": \"1.42\"\n" +
                "        },\n" +
                "        \"2\": {\n" +
                "          \"label\": \"2\",\n" +
                "          \"pos\": \"3\",\n" +
                "          \"odd\": \"5.80\"\n" +
                "        },\n" +
                "        \"N\": {\n" +
                "          \"label\": \"N\",\n" +
                "          \"pos\": \"2\",\n" +
                "          \"odd\": \"4.35\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Score exact (FullTime)\": {\n" +
                "        \"1 - 0\": {\n" +
                "          \"label\": \"1 - 0\",\n" +
                "          \"pos\": \"1\",\n" +
                "          \"odd\": \"6.50\"\n" +
                "        },\n" +
                "        \"2 - 0\": {\n" +
                "          \"label\": \"2 - 0\",\n" +
                "          \"pos\": \"2\",\n" +
                "          \"odd\": \"6.00\"\n" +
                "        },\n" +
                "        \"2 - 1\": {\n" +
                "          \"label\": \"2 - 1\",\n" +
                "          \"pos\": \"3\",\n" +
                "          \"odd\": \"7.00\"\n" +
                "        },\n" +
                "        \"3 - 0\": {\n" +
                "          \"label\": \"3 - 0\",\n" +
                "          \"pos\": \"4\",\n" +
                "          \"odd\": \"6.50\"\n" +
                "        },\n" +
                "        \"3 - 1\": {\n" +
                "          \"label\": \"3 - 1\",\n" +
                "          \"pos\": \"5\",\n" +
                "          \"odd\": \"7.75\"\n" +
                "        },\n" +
                "        \"3 - 2\": {\n" +
                "          \"label\": \"3 - 2\",\n" +
                "          \"pos\": \"6\",\n" +
                "          \"odd\": \"15.00\"\n" +
                "        },\n" +
                "        \"4 - 0\": {\n" +
                "          \"label\": \"4 - 0\",\n" +
                "          \"pos\": \"7\",\n" +
                "          \"odd\": \"15.00\"\n" +
                "        },\n" +
                "        \"4 - 1\": {\n" +
                "          \"label\": \"4 - 1\",\n" +
                "          \"pos\": \"8\",\n" +
                "          \"odd\": \"14.00\"\n" +
                "        },\n" +
                "        \"4 - 2\": {\n" +
                "          \"label\": \"4 - 2\",\n" +
                "          \"pos\": \"9\",\n" +
                "          \"odd\": \"25.00\"\n" +
                "        },\n" +
                "        \"4 - 3\": {\n" +
                "          \"label\": \"4 - 3\",\n" +
                "          \"pos\": \"10\",\n" +
                "          \"odd\": \"70.00\"\n" +
                "        },\n" +
                "        \"0 - 0\": {\n" +
                "          \"label\": \"0 - 0\",\n" +
                "          \"pos\": \"11\",\n" +
                "          \"odd\": \"14.00\"\n" +
                "        },\n" +
                "        \"1 - 1\": {\n" +
                "          \"label\": \"1 - 1\",\n" +
                "          \"pos\": \"12\",\n" +
                "          \"odd\": \"7.75\"\n" +
                "        },\n" +
                "        \"2 - 2\": {\n" +
                "          \"label\": \"2 - 2\",\n" +
                "          \"pos\": \"13\",\n" +
                "          \"odd\": \"14.00\"\n" +
                "        },\n" +
                "        \"3 - 3\": {\n" +
                "          \"label\": \"3 - 3\",\n" +
                "          \"pos\": \"14\",\n" +
                "          \"odd\": \"43.00\"\n" +
                "        },\n" +
                "        \"4 - 4\": {\n" +
                "          \"label\": \"4 - 4\",\n" +
                "          \"pos\": \"15\",\n" +
                "          \"odd\": \"150.00\"\n" +
                "        },\n" +
                "        \"0 - 1\": {\n" +
                "          \"label\": \"0 - 1\",\n" +
                "          \"pos\": \"16\",\n" +
                "          \"odd\": \"15.50\"\n" +
                "        },\n" +
                "        \"0 - 2\": {\n" +
                "          \"label\": \"0 - 2\",\n" +
                "          \"pos\": \"17\",\n" +
                "          \"odd\": \"31.00\"\n" +
                "        },\n" +
                "        \"1 - 2\": {\n" +
                "          \"label\": \"1 - 2\",\n" +
                "          \"pos\": \"18\",\n" +
                "          \"odd\": \"17.00\"\n" +
                "        },\n" +
                "        \"0 - 3\": {\n" +
                "          \"label\": \"0 - 3\",\n" +
                "          \"pos\": \"19\",\n" +
                "          \"odd\": \"80.00\"\n" +
                "        },\n" +
                "        \"1 - 3\": {\n" +
                "          \"label\": \"1 - 3\",\n" +
                "          \"pos\": \"20\",\n" +
                "          \"odd\": \"44.00\"\n" +
                "        },\n" +
                "        \"2 - 3\": {\n" +
                "          \"label\": \"2 - 3\",\n" +
                "          \"pos\": \"21\",\n" +
                "          \"odd\": \"35.00\"\n" +
                "        },\n" +
                "        \"0 - 4\": {\n" +
                "          \"label\": \"0 - 4\",\n" +
                "          \"pos\": \"22\",\n" +
                "          \"odd\": \"100.00\"\n" +
                "        },\n" +
                "        \"1 - 4\": {\n" +
                "          \"label\": \"1 - 4\",\n" +
                "          \"pos\": \"23\",\n" +
                "          \"odd\": \"150.00\"\n" +
                "        },\n" +
                "        \"2 - 4\": {\n" +
                "          \"label\": \"2 - 4\",\n" +
                "          \"pos\": \"24\",\n" +
                "          \"odd\": \"142.00\"\n" +
                "        },\n" +
                "        \"3 - 4\": {\n" +
                "          \"label\": \"3 - 4\",\n" +
                "          \"pos\": \"25\",\n" +
                "          \"odd\": \"150.00\"\n" +
                "        },\n" +
                "        \"Other\": {\n" +
                "          \"label\": \"Other\",\n" +
                "          \"pos\": \"26\",\n" +
                "          \"odd\": \"7.00\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Double chance (FullTime)\": {\n" +
                "        \"1/N\": {\n" +
                "          \"label\": \"1/N\",\n" +
                "          \"pos\": \"1\",\n" +
                "          \"odd\": \"1.06\"\n" +
                "        },\n" +
                "        \"N/2\": {\n" +
                "          \"label\": \"N/2\",\n" +
                "          \"pos\": \"2\",\n" +
                "          \"odd\": \"2.50\"\n" +
                "        },\n" +
                "        \"1/2\": {\n" +
                "          \"label\": \"1/2\",\n" +
                "          \"pos\": \"3\",\n" +
                "          \"odd\": \"1.14\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Result & The 2 teams score\": {\n" +
                "        \"FC Barcelone / Yes\": {\n" +
                "          \"label\": \"FC Barcelone / Yes\",\n" +
                "          \"pos\": \"1\",\n" +
                "          \"odd\": \"2.30\"\n" +
                "        },\n" +
                "        \"FC Barcelone / No\": {\n" +
                "          \"label\": \"FC Barcelone / No\",\n" +
                "          \"pos\": \"2\",\n" +
                "          \"odd\": \"2.20\"\n" +
                "        },\n" +
                "        \"N / Yes\": {\n" +
                "          \"label\": \"N / Yes\",\n" +
                "          \"pos\": \"3\",\n" +
                "          \"odd\": \"4.30\"\n" +
                "        },\n" +
                "        \"N / No\": {\n" +
                "          \"label\": \"N / No\",\n" +
                "          \"pos\": \"4\",\n" +
                "          \"odd\": \"14.80\"\n" +
                "        },\n" +
                "        \"CF Valence / Yes\": {\n" +
                "          \"label\": \"CF Valence / Yes\",\n" +
                "          \"pos\": \"5\",\n" +
                "          \"odd\": \"7.75\"\n" +
                "        },\n" +
                "        \"CF Valence / No\": {\n" +
                "          \"label\": \"CF Valence / No\",\n" +
                "          \"pos\": \"6\",\n" +
                "          \"odd\": \"9.75\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Both teams score\": {\n" +
                "        \"Yes\": {\n" +
                "          \"label\": \"Yes\",\n" +
                "          \"pos\": \"1\",\n" +
                "          \"odd\": \"1.60\"\n" +
                "        },\n" +
                "        \"No\": {\n" +
                "          \"label\": \"No\",\n" +
                "          \"pos\": \"2\",\n" +
                "          \"odd\": \"1.95\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Over/Under 1,5 goal (FullTime)\": {\n" +
                "        \"Over 1,5\": {\n" +
                "          \"label\": \"Over 1,5\",\n" +
                "          \"pos\": \"1\",\n" +
                "          \"odd\": \"1.10\"\n" +
                "        },\n" +
                "        \"Under 1,5\": {\n" +
                "          \"label\": \"Under 1,5\",\n" +
                "          \"pos\": \"2\",\n" +
                "          \"odd\": \"3.50\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Over/Under 3,5 goals (FullTime)\": {\n" +
                "        \"Over 3,5\": {\n" +
                "          \"label\": \"Over 3,5\",\n" +
                "          \"pos\": \"1\",\n" +
                "          \"odd\": \"2.00\"\n" +
                "        },\n" +
                "        \"Under 3,5\": {\n" +
                "          \"label\": \"Under 3,5\",\n" +
                "          \"pos\": \"2\",\n" +
                "          \"odd\": \"1.45\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Over/Under 2,5 goals (FullTime)\": {\n" +
                "        \"Over 2,5\": {\n" +
                "          \"label\": \"Over 2,5\",\n" +
                "          \"pos\": \"1\",\n" +
                "          \"odd\": \"1.40\"\n" +
                "        },\n" +
                "        \"Under 2,5\": {\n" +
                "          \"label\": \"Under 2,5\",\n" +
                "          \"pos\": \"2\",\n" +
                "          \"odd\": \"2.10\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"Result & Over/Under 2,5 goals (FullTime)\": {\n" +
                "        \"FC Barcelone & over 2,5\": {\n" +
                "          \"label\": \"FC Barcelone & over 2,5\",\n" +
                "          \"pos\": \"1\",\n" +
                "          \"odd\": \"1.78\"\n" +
                "        },\n" +
                "        \"Draw & over 2,5\": {\n" +
                "          \"label\": \"Draw & over 2,5\",\n" +
                "          \"pos\": \"2\",\n" +
                "          \"odd\": \"9.00\"\n" +
                "        },\n" +
                "        \"CF Valence & over 2,5\": {\n" +
                "          \"label\": \"CF Valence & over 2,5\",\n" +
                "          \"pos\": \"3\",\n" +
                "          \"odd\": \"8.50\"\n" +
                "        },\n" +
                "        \"FC Barcelone & under 2,5\": {\n" +
                "          \"label\": \"FC Barcelone & under 2,5\",\n" +
                "          \"pos\": \"4\",\n" +
                "          \"odd\": \"3.05\"\n" +
                "        },\n" +
                "        \"Draw & under 2,5\": {\n" +
                "          \"label\": \"Draw & under 2,5\",\n" +
                "          \"pos\": \"5\",\n" +
                "          \"odd\": \"4.50\"\n" +
                "        },\n" +
                "        \"CF Valence & under 2,5\": {\n" +
                "          \"label\": \"CF Valence & under 2,5\",\n" +
                "          \"pos\": \"6\",\n" +
                "          \"odd\": \"12.00\"\n" +
                "        }\n" +
                "      }   \n" +
                "    }\n" +
                "  }\n" +
                "}";
    }

    @Test
    public void test(){
        String fixtureId = "214053";
        String url = this.environment.getProperty("oddsByFixture").replace("<fixtureId>", fixtureId);

        mockServer
                .expect(requestTo(url))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(legalJson, MediaType.APPLICATION_JSON));

        APIResponse<FixtureOdds> apiResponse = this.apiOddsRetriever.oddsByFixture(fixtureId);
        assertEquals(200, apiResponse.getResponse().value());
        assertEquals(1, apiResponse.getResults());
        List<FixtureOdds> fixtureOddsList = apiResponse.getBody();
        FixtureOdds fixtureOdds = fixtureOddsList.get(0);
        assertEquals(fixtureId, fixtureOdds.getFixtureId());
        List<MarketOdds> marketOdds = fixtureOdds.getMarketOdds();
        marketOdds.forEach( marketOdd -> {
            switch (marketOdd.getMarketValue()){
                case HDA_HOME:
                    assertEquals(new OddsValue(1.42), marketOdd.getOddsValue());
                    break;
                case HDA_DRAW:
                    assertEquals(new OddsValue(4.35), marketOdd.getOddsValue());
                    break;
                case HDA_AWAY:
                    assertEquals(new OddsValue(5.8), marketOdd.getOddsValue());
                    break;
                case DC_HOME_DRAW:
                    assertEquals(new OddsValue(1.06), marketOdd.getOddsValue());
                    break;
                case DC_DRAW_AWAY:
                    assertEquals(new OddsValue(2.5), marketOdd.getOddsValue());
                    break;
                case DC_HOME_AWAY:
                    assertEquals(new OddsValue(1.14), marketOdd.getOddsValue());
                    break;
                case HDA_BTTS_HOME_YES:
                    assertEquals(new OddsValue(2.3), marketOdd.getOddsValue());
                    break;
                case HDA_BTTS_HOME_NO:
                    assertEquals(new OddsValue(2.2), marketOdd.getOddsValue());
                    break;
                case HDA_BTTS_DRAW_YES:
                    assertEquals(new OddsValue(4.3), marketOdd.getOddsValue());
                    break;
                case HDA_BTTS_DRAW_NO:
                    assertEquals(new OddsValue(14.8), marketOdd.getOddsValue());
                    break;
                case HDA_BTTS_AWAY_YES:
                    assertEquals(new OddsValue(7.75), marketOdd.getOddsValue());
                    break;
                case HDA_BTTS_AWAY_NO:
                    assertEquals(new OddsValue(9.75), marketOdd.getOddsValue());
                    break;
                case BTTS_YES:
                    assertEquals(new OddsValue(1.6), marketOdd.getOddsValue());
                    break;
                case BTTS_NO:
                    assertEquals(new OddsValue(1.95), marketOdd.getOddsValue());
                    break;
                case U1_5:
                    assertEquals(new OddsValue(3.5), marketOdd.getOddsValue());
                    break;
                case U2_5:
                    assertEquals(new OddsValue(2.1), marketOdd.getOddsValue());
                    break;
                case U3_5:
                    assertEquals(new OddsValue(1.45), marketOdd.getOddsValue());
                    break;
                case O1_5:
                    assertEquals(new OddsValue(1.1), marketOdd.getOddsValue());
                    break;
                case O2_5:
                    assertEquals(new OddsValue(1.4), marketOdd.getOddsValue());
                    break;
                case O3_5:
                    assertEquals(new OddsValue(2), marketOdd.getOddsValue());
                    break;
                case HDA_HOME_O2_5:
                    assertEquals(new OddsValue(1.78), marketOdd.getOddsValue());
                    break;
                case HDA_DRAW_O2_5:
                    assertEquals(new OddsValue(9), marketOdd.getOddsValue());
                    break;
                case HDA_AWAY_O2_5:
                    assertEquals(new OddsValue(8.5), marketOdd.getOddsValue());
                    break;
                case HDA_HOME_U2_5:
                    assertEquals(new OddsValue(3.05), marketOdd.getOddsValue());
                    break;
                case HDA_DRAW_U2_5:
                    assertEquals(new OddsValue(4.5), marketOdd.getOddsValue());
                    break;
                case HDA_AWAY_U2_5:
                    assertEquals(new OddsValue(12.0), marketOdd.getOddsValue());
                    break;
                case NIL_NIL:
                    assertEquals(new OddsValue(14.0), marketOdd.getOddsValue());
                    break;
                case NIL_ONE:
                    assertEquals(new OddsValue(15.5), marketOdd.getOddsValue());
                    break;
                case NIL_TWO:
                    assertEquals(new OddsValue(31.0), marketOdd.getOddsValue());
                    break;
                case NIL_THREE:
                    assertEquals(new OddsValue(80.0), marketOdd.getOddsValue());
                    break;
                case NIL_FOUR:
                    assertEquals(new OddsValue(100.0), marketOdd.getOddsValue());
                    break;
                case ONE_NIL:
                    assertEquals(new OddsValue(6.5), marketOdd.getOddsValue());
                    break;
                case ONE_ONE:
                    assertEquals(new OddsValue(7.75), marketOdd.getOddsValue());
                    break;
                case ONE_TWO:
                    assertEquals(new OddsValue(17.0), marketOdd.getOddsValue());
                    break;
                case ONE_THREE:
                    assertEquals(new OddsValue(44.0), marketOdd.getOddsValue());
                    break;
                case ONE_FOUR:
                    assertEquals(new OddsValue(150.0), marketOdd.getOddsValue());
                    break;
                case TWO_NIL:
                    assertEquals(new OddsValue(6), marketOdd.getOddsValue());
                    break;
                case TWO_ONE:
                    assertEquals(new OddsValue(7), marketOdd.getOddsValue());
                    break;
                case TWO_TWO:
                    assertEquals(new OddsValue(14.0), marketOdd.getOddsValue());
                    break;
                case TWO_THREE:
                    assertEquals(new OddsValue(35.0), marketOdd.getOddsValue());
                    break;
                case TWO_FOUR:
                    assertEquals(new OddsValue(142.0), marketOdd.getOddsValue());
                    break;
                case THREE_NIL:
                    assertEquals(new OddsValue(6.5), marketOdd.getOddsValue());
                    break;
                case THREE_ONE:
                    assertEquals(new OddsValue(7.75), marketOdd.getOddsValue());
                    break;
                case THREE_TWO:
                    assertEquals(new OddsValue(15.0), marketOdd.getOddsValue());
                    break;
                case THREE_THREE:
                    assertEquals(new OddsValue(43.0), marketOdd.getOddsValue());
                    break;
                case THREE_FOUR:
                    assertEquals(new OddsValue(150.0), marketOdd.getOddsValue());
                    break;
                case FOUR_NIL:
                    assertEquals(new OddsValue(15.0), marketOdd.getOddsValue());
                    break;
                case FOUR_ONE:
                    assertEquals(new OddsValue(14.0), marketOdd.getOddsValue());
                    break;
                case FOUR_TWO:
                    assertEquals(new OddsValue(25.0), marketOdd.getOddsValue());
                    break;
                case FOUR_THREE:
                    assertEquals(new OddsValue(70.0), marketOdd.getOddsValue());
                    break;
                case FOUR_FOUR:
                    assertEquals(new OddsValue(150.0), marketOdd.getOddsValue());
                    break;
                case OTHER:
                    assertEquals(new OddsValue(7.0), marketOdd.getOddsValue());
                    break;
            }
        });
    }

}
