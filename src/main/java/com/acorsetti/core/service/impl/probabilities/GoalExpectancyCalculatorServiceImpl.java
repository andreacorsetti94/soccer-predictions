package com.acorsetti.core.service.impl.probabilities;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.TeamService;
import com.acorsetti.core.service.probabilities.GoalExpectancyCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GoalExpectancyCalculatorServiceImpl")
public class GoalExpectancyCalculatorServiceImpl implements GoalExpectancyCalculatorService {

    private static final int MATCHES_TO_CONSIDER = 100;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private TeamService teamService;

    @Override
    public GoalExpectancy calculateExpectancy(Fixture fixture) {
        if ( fixture == null ) throw new IllegalArgumentException("Fixture Argument is null!");

        String homeTeamId = fixture.getHomeTeamId();
        String awayTeamId = fixture.getAwayTeamId();

        List<Fixture> lastHomeTeamMatches = this.fixtureService.lastTeamMatches(homeTeamId, MATCHES_TO_CONSIDER);
        List<Fixture> lastAwayTeamMatches = this.fixtureService.lastTeamMatches(awayTeamId, MATCHES_TO_CONSIDER);

        double homeAttackStrength;
        double awayAttackStrength;
        double homeDefenceStrength;
        double awayDefenceStrength;

        double avgHomeTeamGoalsFor = this.teamService.avgGoalsScored(homeTeamId,lastHomeTeamMatches);
        double avgHomeTeamGoalsConceived = this.teamService.avgGoalsConceived(homeTeamId, lastHomeTeamMatches);
        double avgAwayTeamGoalsFor = this.teamService.avgGoalsScored(awayTeamId,lastAwayTeamMatches);
        double avgAwayTeamGoalsConceived = this.teamService.avgGoalsConceived(awayTeamId,lastAwayTeamMatches);

        double avgGoalsScored = (avgAwayTeamGoalsFor + avgHomeTeamGoalsFor) / 2;
        double avgGoalsConceived = (avgAwayTeamGoalsConceived + avgHomeTeamGoalsConceived) / 2;

        if ( avgGoalsScored == 0.0 ){
            homeAttackStrength = 0.0;
            awayAttackStrength = 0.0;
        }
        else{
            homeAttackStrength = avgHomeTeamGoalsFor / avgGoalsScored;
            awayAttackStrength = avgAwayTeamGoalsFor / avgGoalsScored;
        }

        if ( avgGoalsConceived == 0.0 ){
            homeDefenceStrength = 0.0;
            awayDefenceStrength = 0.0;
        }
        else{
            homeDefenceStrength = avgHomeTeamGoalsConceived / avgGoalsConceived;
            awayDefenceStrength = avgAwayTeamGoalsConceived / avgGoalsConceived;
        }

        double homeForm = 0.0;
        double awayForm = 0.0;

        int homeMatchesCount = 0;
        int awayMatchesCount = 0;

        for(Fixture fixture1: lastHomeTeamMatches){
            int pointsForGame = this.fixtureService.pointsForTeam(fixture1,homeTeamId);

            if (fixture1.getHomeTeamId().equals(homeTeamId)) {
                homeMatchesCount++;
                homeForm += pointsForGame;
            }
        }
        if (homeMatchesCount != 0) {
            homeForm = homeForm / homeMatchesCount;
        }

        for(Fixture fixture2: lastAwayTeamMatches){
            int pointsForGame = this.fixtureService.pointsForTeam(fixture2,awayTeamId);
            if ( fixture2.getAwayTeamId().equals(awayTeamId) ){
                awayMatchesCount++;
                awayForm += pointsForGame;
            }
        }
        if (awayMatchesCount != 0) {
            awayForm = awayForm / awayMatchesCount;
        }

        double hge = homeAttackStrength * awayDefenceStrength;
        double age = awayAttackStrength * homeDefenceStrength;

        double homeAdvantageOffset = offset(hge, age);

        if ( homeForm > awayForm ){
            return new GoalExpectancy(hge + homeAdvantageOffset, age);
        }
        else{
            return new GoalExpectancy(hge + (homeAdvantageOffset/3), age + homeAdvantageOffset);
        }

    }

    private double offset(double homeForm, double awayForm){
        double absOffset = Math.abs(homeForm - awayForm);

        if (absOffset > 1.5) return 0.8;
        if (absOffset > 1) return 0.65;
        if (absOffset > 0.75) return 0.55;
        if (absOffset > 0.5) return 0.45;
        if (absOffset > 0.25) return 0.35;
        if (absOffset > 0.15) return 0.15;

        return 0.05;
    }

}
