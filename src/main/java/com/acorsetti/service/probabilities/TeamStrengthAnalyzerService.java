package com.acorsetti.service.probabilities;

import com.acorsetti.model.jpa.Fixture;

import java.util.List;

public interface TeamStrengthAnalyzerService {

    void analyzeStrengths(String homeTeamId, String awayTeamId, List<Fixture> lastHomeTeamMatches, List<Fixture> lastAwayTeamMatches);
    double getHomeAttackStrength();
    double getAwayAttackStrength();
    double getHomeDefenceStrength();
    double getAwayDefenceStrength();
}
