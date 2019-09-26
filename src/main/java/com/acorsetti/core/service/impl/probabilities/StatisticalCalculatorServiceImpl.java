package com.acorsetti.core.service.impl.probabilities;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.eval.TeamsForm;
import com.acorsetti.core.model.eval.TeamsStrength;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.probabilities.StatisticalCalculatorService;
import com.acorsetti.core.service.probabilities.TeamFormCalculatorService;
import com.acorsetti.core.service.probabilities.TeamStrengthAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("statisticalCalculatorByDB")
@Profile("test")
public class StatisticalCalculatorServiceImpl implements StatisticalCalculatorService {

    private static final int MATCHES_TO_CONSIDER = 6;

    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private TeamStrengthAnalyzerService teamStrengthAnalyzerService;

    @Autowired
    private TeamFormCalculatorService teamFormCalculatorService;

    @Override
    public GoalExpectancy calculateExpectancy(Fixture fixture) {
        if ( fixture == null ) throw new IllegalArgumentException("Fixture Argument is null!");

        String homeTeamId = fixture.getHomeTeamId();
        String awayTeamId = fixture.getAwayTeamId();

        List<Fixture> lastHomeTeamMatches = this.fixtureService.lastTeamMatches(homeTeamId, MATCHES_TO_CONSIDER);
        List<Fixture> lastAwayTeamMatches = this.fixtureService.lastTeamMatches(awayTeamId, MATCHES_TO_CONSIDER);

        TeamsStrength ts = this.teamStrengthAnalyzerService.analyzeStrengths(homeTeamId, awayTeamId, lastHomeTeamMatches, lastAwayTeamMatches);

        double homeAttackStrength = ts.getHomeAttackStrength();
        double awayAttackStrength = ts.getAwayAttackStrenth();

        double homeDefenceStrength = ts.getHomeDefenceStrength();
        double awayDefenceStrength = ts.getAwayDefenceStrenth();


        TeamsForm tf = this.teamFormCalculatorService.calculateTeamsForm(homeTeamId,awayTeamId,lastHomeTeamMatches,lastAwayTeamMatches);
        double homeTeamForm = tf.getHomeTeamForm();
        double awayTeamForm = tf.getAwayTeamForm();

        double hge = homeAttackStrength * awayDefenceStrength;
        double age = awayAttackStrength * homeDefenceStrength;

        double homeAdvantageOffset = offset(hge, age);

        if ( homeTeamForm > awayTeamForm ){
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
