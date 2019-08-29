package com.acorsetti.service.probabilities;

import com.acorsetti.model.jpa.Fixture;

import java.util.List;

public interface TeamFormCalculatorService {

    void calculateTeamsForm(String homeTeamId, String awayTeamId, List<Fixture> lastHomeTeamMatches,  List<Fixture> lastAwayTeamMatches);
    double getHomeForm();
    double getAwayForm();
}
