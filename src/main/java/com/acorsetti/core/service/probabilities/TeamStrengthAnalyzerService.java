package com.acorsetti.core.service.probabilities;

import com.acorsetti.core.model.eval.TeamsStrength;
import com.acorsetti.core.model.jpa.Fixture;

import java.util.List;

public interface TeamStrengthAnalyzerService {

    TeamsStrength analyzeStrengths(String homeTeamId, String awayTeamId, List<Fixture> lastHomeTeamMatches, List<Fixture> lastAwayTeamMatches);

}
