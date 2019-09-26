package com.acorsetti.core.service.probabilities;

import com.acorsetti.core.model.eval.TeamsForm;
import com.acorsetti.core.model.jpa.Fixture;

import java.util.List;

public interface TeamFormCalculatorService {

    TeamsForm calculateTeamsForm(String homeTeamId, String awayTeamId, List<Fixture> lastHomeTeamMatches, List<Fixture> lastAwayTeamMatches);

}
