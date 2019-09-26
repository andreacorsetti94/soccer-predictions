package com.acorsetti.core.service.impl.probabilities;

import com.acorsetti.core.model.eval.TeamsStrength;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.service.TeamService;
import com.acorsetti.core.service.probabilities.TeamStrengthAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamStrengthAnalyzerServiceImpl implements TeamStrengthAnalyzerService {

    @Autowired
    private TeamService teamService;

    public TeamsStrength analyzeStrengths(String homeTeamId, String awayTeamId, List<Fixture> lastHomeTeamMatches, List<Fixture> lastAwayTeamMatches){
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


        return new TeamsStrength(homeAttackStrength, homeDefenceStrength, awayAttackStrength, awayDefenceStrength);
    }

}
