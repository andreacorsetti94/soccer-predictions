package com.acorsetti.core.service;

import com.acorsetti.core.model.jpa.Fixture;

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
    List<Fixture> lastTeamMatchesBeforeAMatch(String teamId, int numOfMatches, Fixture pivot,String... leaguesId);

    int goalSum(Fixture fixture);
    List<Fixture> fixturesInPeriodByAPI(LocalDate lowerBoundDate, LocalDate upperBoundDate);
    List<Fixture> fixturesInPeriodByDB(LocalDate lowerBoundDate, LocalDate upperBoundDate);
    List<Fixture> byLeagueHomeAndAway(String leagueId, String home, String away);

}
