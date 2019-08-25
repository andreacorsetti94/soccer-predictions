package com.acorsetti.service;

import com.acorsetti.model.Fixture;

import java.util.List;

public interface FixtureService {

    List<Fixture> fixturesByDay(String dayLike);

    boolean isCompleted(Fixture fixture);
    String winnerTeamId(Fixture fixture);
    String loserTeamId(Fixture fixture);
    int getTeamGoalsFor(Fixture fixture, String teamId);
    int getTeamGoalsConceived(Fixture fixture, String teamId);
    int pointsForTeam(Fixture fixture, String teamId);
}
