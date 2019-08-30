package com.acorsetti.service.impl.probabilities;

import com.acorsetti.model.eval.TeamsForm;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.service.FixtureService;
import com.acorsetti.service.probabilities.TeamFormCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamFormCalculatorServiceImpl implements TeamFormCalculatorService {

    @Autowired
    private FixtureService fixtureService;


    public TeamsForm calculateTeamsForm(String homeTeamId, String awayTeamId, List<Fixture> lastHomeTeamMatches, List<Fixture> lastAwayTeamMatches){
        double homeForm = 0.0;
        double awayForm = 0.0;

        int homeMatchesCount = 0;
        int awayMatchesCount = 0;

        for(Fixture fixture: lastHomeTeamMatches){
            int pointsForGame = this.fixtureService.pointsForTeam(fixture,homeTeamId);

            if (fixture.getHomeTeamId().equals(homeTeamId)) {
                homeMatchesCount++;
                homeForm += pointsForGame;
            }
        }
        if (homeMatchesCount != 0) {
            homeForm = homeForm / homeMatchesCount;
        }

        for(Fixture fixture: lastAwayTeamMatches){
            int pointsForGame = this.fixtureService.pointsForTeam(fixture,awayTeamId);
            if ( fixture.getAwayTeamId().equals(awayTeamId) ){
                awayMatchesCount++;
                awayForm += pointsForGame;
            }
        }
        if (awayMatchesCount != 0) {
            awayForm = awayForm / awayMatchesCount;
        }
        return new TeamsForm(homeForm,awayForm);
    }
}
