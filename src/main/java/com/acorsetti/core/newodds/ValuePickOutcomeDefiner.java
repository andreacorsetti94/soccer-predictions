package com.acorsetti.core.newodds;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
public class ValuePickOutcomeDefiner {

    @Autowired
    private FixtureService fixtureService;

    public void defineValuePickOutcome(){
        try{
            String url = "jdbc:mysql://localhost:3306/soccerlab";
            Connection conn = DriverManager.getConnection(url,"admin","admin");

            PreparedStatement preparedStatement = conn.prepareStatement("select * from valuepick where outcome = ?");
            preparedStatement.setString(1, "TBD");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                String fixtureId = resultSet.getString("fixtureId");
                MarketValue marketValue = MarketValue.byName(resultSet.getString("market"));
                Fixture fixture = this.fixtureService.byId(fixtureId);

                String outcome = this.computeFixtureOutcome(fixture, marketValue);
                PreparedStatement preparedUpdate = conn.prepareStatement("update valuepick set outcome = ?");
                preparedUpdate.setString(1, outcome);
                preparedUpdate.execute();
            }
            conn.close();
        }
        catch (Exception e){
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }

    public String computeFixtureOutcome(Fixture fixture, MarketValue marketValue){
        if ( this.fixtureService.isCompleted(fixture) ){
            int homeGoals = this.fixtureService.getTeamGoalsFor(fixture, fixture.getHomeTeamId());
            int awayGoals = this.fixtureService.getTeamGoalsFor(fixture, fixture.getAwayTeamId());
            int totGoals = homeGoals + awayGoals;

            switch (marketValue){
                case HDA_HOME: return homeGoals > awayGoals ? "Y" : "N";
                case HDA_DRAW: return homeGoals == awayGoals ? "Y" : "N";
                case HDA_AWAY: return homeGoals < awayGoals ? "Y" : "N";
                case BTTS_YES: return homeGoals > 0 && awayGoals > 0 ? "Y" : "N";
                case BTTS_NO: return homeGoals == 0 || awayGoals == 0 ? "Y" : "N";
                case DC_HOME_DRAW: return homeGoals >= awayGoals ? "Y" : "N";
                case DC_DRAW_AWAY: return homeGoals <= awayGoals ? "Y" : "N";
                case DC_HOME_AWAY: return homeGoals != awayGoals ? "Y" : "N";
                case U1_5: return totGoals <= 1 ? "Y" : "N";
                case O1_5: return totGoals > 1 ? "Y" : "N";
                case U2_5: return totGoals <= 2 ? "Y" : "N";
                case O2_5: return totGoals > 2 ? "Y" : "N";
                case U3_5: return totGoals <= 3 ? "Y" : "N";
                case O3_5: return totGoals > 3 ? "Y" : "N";
                case U4_5: return totGoals <= 4 ? "Y" : "N";
                case O4_5: return totGoals > 4 ? "Y" : "N";
                default: return "N";
            }
        }
        else return "TBD";
    }
}
