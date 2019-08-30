package com.acorsetti.service.probabilities;

import com.acorsetti.model.eval.TeamsForm;
import com.acorsetti.model.jpa.Fixture;

import java.util.List;

public interface TeamFormCalculatorService {

    TeamsForm calculateTeamsForm(String homeTeamId, String awayTeamId, List<Fixture> lastHomeTeamMatches, List<Fixture> lastAwayTeamMatches);

}
