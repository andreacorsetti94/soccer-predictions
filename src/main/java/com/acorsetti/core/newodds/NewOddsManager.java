package com.acorsetti.core.newodds;

import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.odds.FixtureOdds;
import com.acorsetti.core.model.odds.MarketOdds;
import com.acorsetti.core.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@Configuration
@PropertySource("classpath:endpoints.properties")
@PropertySource("classpath:application.properties")
public class NewOddsManager {

    @Autowired
    private Environment environment;

    @Autowired
    private APIOddsRetriever apiOddsRetriever;

    @Autowired
    private OddsCleaner oddsCleaner;

    @Autowired
    private FixtureService fixtureService;

    public List<OddEntity> manage(String fixtureId){
        Fixture fixture = this.fixtureService.byId(fixtureId);
        if ( fixture == null || fixture.getEventDate().isAfter(LocalDateTime.now().plusDays(3))) return Collections.emptyList();
        String endpoint = this.environment.getProperty("oddsByFixture").replace("<fixtureId>", fixtureId);
        APIResponse<BookmakerOdds> apiResponse = this.apiOddsRetriever.retrieve(endpoint);
        List<BookmakerOdds> bookmakerOddsList = apiResponse.getBody();

        bookmakerOddsList.forEach( bm -> bm.setFixtureId(fixtureId));
        List<OddEntity> oddEntities = this.oddsCleaner.clean(bookmakerOddsList);
        return this.saveFixtureOdds(oddEntities);
    }

    private List<OddEntity> saveFixtureOdds(List<OddEntity> oddEntities){
        if ( oddEntities == null || oddEntities.isEmpty()) return Collections.emptyList();
        try{
            String url = "jdbc:mysql://localhost:3306/soccerlab";
            Connection conn = DriverManager.getConnection(url,"admin","admin");

            for(OddEntity oddEntity: oddEntities){
                String fixtureId = oddEntity.getFixtureId();
                MarketValue marketValue = oddEntity.getMarketValue();
                double avg = oddEntity.getAvgVal().getValue();
                double max = oddEntity.getMaxVal().getValue();

                String insert = "replace into odds values (?,?,?,?) ";
                PreparedStatement preparedStmt = conn.prepareStatement(insert);
                preparedStmt.setString(1, fixtureId);
                preparedStmt.setString(2, marketValue.toString());
                preparedStmt.setDouble(3, avg);
                preparedStmt.setDouble(4, max);
                preparedStmt.execute();

            }
            conn.close();

        }
        catch (Exception e){
            System.err.println(e.toString());
            e.printStackTrace();
        }
        return oddEntities;
    }
}
