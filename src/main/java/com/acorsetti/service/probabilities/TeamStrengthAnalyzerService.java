package com.acorsetti.service.probabilities;

import com.acorsetti.model.eval.TeamsStrength;
import com.acorsetti.model.jpa.Fixture;

import java.util.List;

public interface TeamStrengthAnalyzerService {

    TeamsStrength analyzeStrengths(String homeTeamId, String awayTeamId, List<Fixture> lastHomeTeamMatches, List<Fixture> lastAwayTeamMatches);

}
