package com.acorsetti.core.service.impl.probabilities;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.StandingPosition;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.StandingPositionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GoalExpectancyCalculatorServiceLeague")
public class GoalExpectancyCalculatorServiceLeague extends GoalExpectancyCalculatorServiceWeighted {

    private static final Logger logger = Logger.getLogger(GoalExpectancyCalculatorServiceLeague.class);

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private StandingPositionService standingPositionService;

    private static final double[] WEIGHTS = {
            1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,6,6,6,6,6,
            7,7,7,7,7,8,8,8,8,8,9,9,9,9,9,10,10.5,11,11.5,12
    };


    @Override
    public GoalExpectancy calculateExpectancy(Fixture fixture) {
        if ( fixture == null ) throw new IllegalArgumentException("Fixture Argument is null!");

        logger.info("GoalExpectancyCalculatorServiceLeague calculating GE for match: " + fixture.getFixtureId());
        Double[] expectedGoals = super.expectedGoals(fixture);

        double expectedGoalsHomeTeam = expectedGoals[0];
        double expectedGoalsAwayTeam = expectedGoals[1];

        //check the teams league status
        String leagueId = fixture.getLeagueId();
        List<StandingPosition> standingPositions = this.standingPositionService.byLeague(leagueId);
        if ( standingPositions == null || standingPositions.isEmpty() ){ //its a cup
            //TODO
            return new GoalExpectancy(expectedGoalsHomeTeam, expectedGoalsAwayTeam);
        }
        else{
            String homeTeamName = fixture.getHomeTeamName();
            String awayTeamName = fixture.getAwayTeamName();
            StandingPosition homeStandingPosition = this.standingPositionService.getStandingPositionByTeamName(leagueId, homeTeamName);
            StandingPosition awayStandingPosition = this.standingPositionService.getStandingPositionByTeamName(leagueId, awayTeamName);

            if ( homeStandingPosition == null || awayStandingPosition == null ){
                return new GoalExpectancy(expectedGoalsHomeTeam, expectedGoalsAwayTeam);
            }
            else {
                double homePoints = (double) homeStandingPosition.getPoints();
                double awayPoints = (double) awayStandingPosition.getPoints();

                if ( homePoints == awayPoints ) return new GoalExpectancy(expectedGoalsHomeTeam, expectedGoalsAwayTeam);
                double pointsRelativeDiff = Math.abs(homePoints - awayPoints) / Math.max(homePoints,awayPoints);

                if ( homePoints > awayPoints ){
                    expectedGoalsHomeTeam = expectedGoalsHomeTeam + (pointsRelativeDiff * expectedGoalsHomeTeam / 2);
                    expectedGoalsAwayTeam = expectedGoalsAwayTeam - (pointsRelativeDiff * expectedGoalsAwayTeam / 2);
                }
                else {
                    expectedGoalsHomeTeam = expectedGoalsHomeTeam - (pointsRelativeDiff * expectedGoalsHomeTeam / 4);
                    expectedGoalsAwayTeam = expectedGoalsAwayTeam + (pointsRelativeDiff * expectedGoalsAwayTeam / 3);
                }
                return new GoalExpectancy(expectedGoalsHomeTeam, expectedGoalsAwayTeam);
            }

        }
    }

}
