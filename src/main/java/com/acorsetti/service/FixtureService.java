package com.acorsetti.service;

import com.acorsetti.model.jpa.Fixture;

import java.time.LocalDate;
import java.util.List;

public interface FixtureService {

    List<Fixture> fixturesByDay(LocalDate dayLike);

    boolean isCompleted(Fixture fixture);
    String winnerTeamId(Fixture fixture);
    String loserTeamId(Fixture fixture);
    int getTeamGoalsFor(Fixture fixture, String teamId);
    int getTeamGoalsConceived(Fixture fixture, String teamId);
    int pointsForTeam(Fixture fixture, String teamId);

    Fixture byId(String id);
    List<Fixture> lastTeamMatches(String teamId, int numOfMatches, String... leaguesId);

    int goalSum(Fixture fixture);
}
