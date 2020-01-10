package com.acorsetti.core.newodds;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.MatchProbability;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.odds.OddsValue;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.MatchProbabilityCalculatorService;
import com.acorsetti.core.utils.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PickGenerator {

    @Autowired
    private OddsSaver oddsSaver;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private MatchProbabilityCalculatorService matchProbabilityCalculatorService;

    public void saveOddsAndCreatePicks(){
        List<Fixture> fixtures = this.fixtureService.fixturesInPeriodByDB(LocalDate.now().minusDays(1), LocalDate.now().plusDays(3));
        List<OddEntity> globalOddList = this.oddsSaver.saveOdds(fixtures);
        fixtures.forEach( f -> {
            try {
                MatchProbability mp = this.matchProbabilityCalculatorService.calculateProbability(f);
                List<OddEntity> oddEntities = this.filterOddEntitiesByFixture(f, globalOddList);
                oddEntities.forEach( oddEntity -> {
                    MarketValue marketValue = oddEntity.getMarketValue();
                    Chance chance = mp.getMarketChance(marketValue);
                    OddsValue oddsValue = oddEntity.getAvgVal();
                    double pickValue = this.getPickValue(oddsValue, chance);
                    if( pickValue > 0 ){
                        System.out.println("Value pick! OddEntity" + oddEntity + " chance: " + chance.getValue() + " // Value: " + pickValue);
                        this.saveValuePick(f.getFixtureId(), marketValue, chance, oddsValue, pickValue);
                    }
                    else {
                        System.out.println("NOT a Value pick! OddEntity" + oddEntity + " chance: " + chance.getValue() + " // Value: " + pickValue);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println(e.toString());
            }
        });
    }

    private void saveValuePick(String fixtureId, MarketValue marketValue, Chance chance, OddsValue oddsValue, double pickValue){
        try{
            String url = "jdbc:mysql://localhost:3306/soccerlab";
            Connection conn = DriverManager.getConnection(url,"admin","admin");
            String insert = "replace into valuepick values (?,?,?,?,?,?) ";
            PreparedStatement preparedStmt = conn.prepareStatement(insert);
            preparedStmt.setString(1, fixtureId);
            preparedStmt.setString(2, marketValue.toString());
            preparedStmt.setDouble(3, chance.getValue());
            preparedStmt.setDouble(4, oddsValue.getValue());
            preparedStmt.setDouble(5, pickValue);
            preparedStmt.setString(6, "TBD");
            preparedStmt.execute();

            conn.close();
        }
        catch (Exception e){
            System.err.println(e.toString());
            e.printStackTrace();
        }
    }

    private List<OddEntity> filterOddEntitiesByFixture(Fixture fixture, List<OddEntity> globalOddEntities){
        return globalOddEntities.stream().filter( oddEntity ->
            oddEntity.getFixtureId().equals(fixture.getFixtureId())
        ).collect(Collectors.toList());
    }

    private double getPickValue(OddsValue oddsValue, Chance chance){
        return MathUtils.round( (chance.getValue()*oddsValue.getValue()) - 1, 2);
    }

}
