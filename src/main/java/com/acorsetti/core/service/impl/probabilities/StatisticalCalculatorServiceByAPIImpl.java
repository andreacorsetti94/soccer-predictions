package com.acorsetti.core.service.impl.probabilities;

import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.api.APITeamStatisticsRetriever;
import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.eval.TeamStatistics;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.service.probabilities.StatisticalCalculatorService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("development")
public class StatisticalCalculatorServiceByAPIImpl implements StatisticalCalculatorService {
    private static final Logger logger = Logger.getLogger(StatisticalCalculatorServiceByAPIImpl.class);

    @Autowired
    private APITeamStatisticsRetriever apiTeamStatisticsRetriever;

    @Override
    public GoalExpectancy calculateExpectancy(Fixture fixture) {
        if ( fixture == null ) throw new IllegalArgumentException("Fixture Argument is null!");

        String homeTeamId = fixture.getHomeTeamId();
        String awayTeamId = fixture.getAwayTeamId();
        String leagueId = fixture.getLeagueId();

        APIResponse<TeamStatistics> apiHomeTeamResponse = this.apiTeamStatisticsRetriever.getStatsForLeagueAndTeam(leagueId, homeTeamId);
        APIResponse<TeamStatistics> apiAwayTeamResponse = this.apiTeamStatisticsRetriever.getStatsForLeagueAndTeam(leagueId, awayTeamId);

        if ( apiHomeTeamResponse.getResponse().value() != 200 || apiAwayTeamResponse.getResponse().value() != 200 ){
            return new GoalExpectancy(0.0,0.0, false);
        }

        try {
            TeamStatistics homeTeamStats = apiHomeTeamResponse.getBody().get(0);
            TeamStatistics awayTeamStats = apiAwayTeamResponse.getBody().get(0);

            double avgGoalsScoredByTeams = (homeTeamStats.getAvgGoalsForTotal() + awayTeamStats.getAvgGoalsForTotal()) / 2;
            double avgGoalsConceivedByTeams = (homeTeamStats.getAvgGoalsAgainstTotal() + awayTeamStats.getAvgGoalsAgainstTotal()) / 2;

            double homeAttackStrength = homeTeamStats.getAvgGoalsForHome() / avgGoalsScoredByTeams;
            double awayAttackStrength = awayTeamStats.getAvgGoalsForAway() / avgGoalsScoredByTeams;

            double homeDefenceStrength = homeTeamStats.getAvgGoalsAgainstHome() / avgGoalsConceivedByTeams;
            double awayDefenceStrength = awayTeamStats.getAvgGoalsAgainstAway() / avgGoalsConceivedByTeams;

            boolean homeBetterThanAway = homeTeamStats.getWinsTotal() * 3 + homeTeamStats.getDrawsTotal() > awayTeamStats.getWinsTotal() * 3 * awayTeamStats.getDrawsTotal();

            double hge = homeAttackStrength * awayDefenceStrength;
            double age = awayAttackStrength * homeDefenceStrength;

            double homeAdvantageOffset = offset(hge, age);

            if (homeBetterThanAway) {
                return new GoalExpectancy(hge + homeAdvantageOffset, age);
            } else {
                return new GoalExpectancy(hge + (homeAdvantageOffset / 3), age + homeAdvantageOffset);
            }
        }
        catch (Exception e){
            logger.error("Catched exception: " + e.toString() + " \nReturning not legit goal expectancy.");
            return new GoalExpectancy(0.0,0.0, false);
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
