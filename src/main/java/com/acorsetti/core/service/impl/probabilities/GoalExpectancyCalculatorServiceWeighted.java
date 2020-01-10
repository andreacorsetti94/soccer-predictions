package com.acorsetti.core.service.impl.probabilities;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.service.FixtureService;
import com.acorsetti.core.service.probabilities.GoalExpectancyCalculatorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("GoalExpectancyCalculatorServiceWeighted")
public class GoalExpectancyCalculatorServiceWeighted implements GoalExpectancyCalculatorService {

    private static final Logger logger = Logger.getLogger(GoalExpectancyCalculatorServiceWeighted.class);

    @Autowired
    private FixtureService fixtureService;

    private static final double[] WEIGHTS = {
            1,1,1,1,1,2,2,2,2,2,3,3,3,3,3,4,4,4,4,4,5,5,5,5,5,6,6,6,6,6,
            7,7,7,7,7,8,8,8,8,8,9,9,9,9,9,10,10.5,11,11.5,12
    };

    /**
     * Tramite media ponderata calcola goal expectancy di un match:
     *
     * (x1 * p1) + (x2 * p2) + .... + (xN * pN) / (p1 + p2 + .... + pn)
     * @param fixture
     * @return
     */
    @Override
    public GoalExpectancy calculateExpectancy(Fixture fixture) {
        if ( fixture == null ) throw new IllegalArgumentException("Fixture Argument is null!");

        Double[] expectedGoals = this.expectedGoals(fixture);

        double expectedGoalsHomeTeam = expectedGoals[0];
        double expectedGoalsAwayTeam = expectedGoals[1];

        GoalExpectancy ge = new GoalExpectancy(expectedGoalsHomeTeam, expectedGoalsAwayTeam);
        return ge;
    }

    protected Double[] expectedGoals(Fixture fixture){

        String homeTeamId = fixture.getHomeTeamId();
        String awayTeamId = fixture.getAwayTeamId();

        /*
        List<Fixture> lastHomeTeamMatches =
                this.fixtureService.lastTeamMatches(fixture.getHomeTeamId(), WEIGHTS.length);
        List<Fixture> lastAwayTeamMatches =
                this.fixtureService.lastTeamMatches(fixture.getAwayTeamId(), WEIGHTS.length);
        */
        List<Fixture> lastHomeTeamMatches =
                this.fixtureService.lastTeamMatchesBeforeAMatch(fixture.getHomeTeamId(), WEIGHTS.length, fixture);
        List<Fixture> lastAwayTeamMatches =
                this.fixtureService.lastTeamMatchesBeforeAMatch(fixture.getAwayTeamId(), WEIGHTS.length, fixture);


        double dividendoHomeScored = 0.0;
        double dividendoHomeConceived = 0.0;
        double weigthSumHome = 0.0;
        for( int i = 0; i < lastHomeTeamMatches.size(); i++ ){
            Fixture homeTeamMatch = lastHomeTeamMatches.get(i);
            int goalsScoredByHomeTeamInMatch = this.fixtureService.getTeamGoalsFor(homeTeamMatch, homeTeamId);
            int goalsConceivedByHomeTeamInMatch = this.fixtureService.getTeamGoalsConceived(homeTeamMatch, homeTeamId);

            double weight;
            if ( homeTeamMatch.getHomeTeamId().equals(homeTeamId) ){
                weight = WEIGHTS[WEIGHTS.length - i - 1];
                dividendoHomeScored += goalsScoredByHomeTeamInMatch * weight;
                dividendoHomeConceived += goalsConceivedByHomeTeamInMatch * weight;
            } //i pesi dei match giocati in casa valgono il doppio di quelli giocati in trasferta
            else{
                weight = WEIGHTS[WEIGHTS.length - i - 1] / 2.0;
                dividendoHomeScored += goalsScoredByHomeTeamInMatch * weight;
                dividendoHomeConceived += goalsConceivedByHomeTeamInMatch * weight;
            }
            weigthSumHome += weight;
        }
        double weigthedHomeScoredAvg = dividendoHomeScored / weigthSumHome;
        double weigthedHomeConceivedAvg = dividendoHomeConceived / weigthSumHome;


        //away team
        double dividendoAwayScored = 0.0;
        double dividendoAwayConceived = 0.0;
        double weigthSumAway = 0.0;
        for( int i = 0; i < lastAwayTeamMatches.size(); i++ ){
            Fixture awayTeamMatch = lastAwayTeamMatches.get(i);
            int goalsScoredByAwayTeamInMatch = this.fixtureService.getTeamGoalsFor(awayTeamMatch, awayTeamId);
            int goalsConceivedByAwayTeamInMatch = this.fixtureService.getTeamGoalsConceived(awayTeamMatch, awayTeamId);

            double weight;
            if ( awayTeamMatch.getAwayTeamId().equals(awayTeamId) ){
                weight = WEIGHTS[WEIGHTS.length - i - 1];
                dividendoAwayScored += goalsScoredByAwayTeamInMatch * weight;
                dividendoAwayConceived += goalsConceivedByAwayTeamInMatch * weight;
            } //i pesi dei match giocati in casa valgono il doppio di quelli giocati in trasferta
            else{
                weight = WEIGHTS[WEIGHTS.length - i - 1] / 2.0;
                dividendoAwayScored += goalsScoredByAwayTeamInMatch * weight;
                dividendoAwayConceived += goalsConceivedByAwayTeamInMatch * weight;
            }
            weigthSumAway += weight;
        }
        double weigthedAwayScoredAvg = dividendoAwayScored / weigthSumAway;
        double weigthedAwayConceivedAvg = dividendoAwayConceived / weigthSumAway;

        double expectedGoalsHomeTeam = (weigthedHomeScoredAvg + weigthedAwayConceivedAvg)/ 2.0;
        double expectedGoalsAwayTeam = (weigthedAwayScoredAvg + weigthedHomeConceivedAvg)/ 2.0;

        return new Double[]{expectedGoalsHomeTeam, expectedGoalsAwayTeam};
    }

}
