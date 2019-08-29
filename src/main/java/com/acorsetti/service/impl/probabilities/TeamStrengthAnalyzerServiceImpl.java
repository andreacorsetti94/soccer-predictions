package com.acorsetti.service.impl.probabilities;

import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.service.TeamService;
import com.acorsetti.service.probabilities.TeamStrengthAnalyzerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamStrengthAnalyzerServiceImpl implements TeamStrengthAnalyzerService {

    private double homeAttackStrength;
    private double awayAttackStrength;
    private double homeDefenceStrength;
    private double awayDefenceStrength;

    @Autowired
    private TeamService teamService;

    public void analyzeStrengths(String homeTeamId, String awayTeamId, List<Fixture> lastHomeTeamMatches, List<Fixture> lastAwayTeamMatches){

        double avgHomeTeamGoalsFor = this.teamService.avgGoalsScored(homeTeamId,lastHomeTeamMatches);
        double avgHomeTeamGoalsConceived = this.teamService.avgGoalsConceived(homeTeamId, lastHomeTeamMatches);
        double avgAwayTeamGoalsFor = this.teamService.avgGoalsScored(awayTeamId,lastAwayTeamMatches);
        double avgAwayTeamGoalsConceived = this.teamService.avgGoalsConceived(awayTeamId,lastAwayTeamMatches);

        double avgGoalsScored = (avgAwayTeamGoalsFor + avgHomeTeamGoalsFor) / 2;
        double avgGoalsConceived = (avgAwayTeamGoalsConceived + avgHomeTeamGoalsConceived) / 2;

        homeAttackStrength = avgHomeTeamGoalsFor / avgGoalsScored;
        awayAttackStrength = avgAwayTeamGoalsFor / avgGoalsScored;
        homeDefenceStrength = avgHomeTeamGoalsConceived / avgGoalsConceived;
        awayDefenceStrength = avgAwayTeamGoalsConceived / avgGoalsConceived;
    }

    public double getHomeAttackStrength() { return homeAttackStrength; }
    public double getAwayAttackStrength() { return awayAttackStrength; }
    public double getHomeDefenceStrength() { return homeDefenceStrength; }
    public double getAwayDefenceStrength() { return awayDefenceStrength; }
}
