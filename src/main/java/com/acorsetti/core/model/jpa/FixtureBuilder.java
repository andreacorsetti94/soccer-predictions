package com.acorsetti.core.model.jpa;

import java.time.LocalDateTime;

public class FixtureBuilder {

    private String fixtureId;

    private String eventTimestamp;
    private LocalDateTime eventDate;
    private String leagueId;
    private String round;
    private String homeTeamId;
    private String awayTeamId;
    private String homeTeamName;
    private String awayTeamName;
    private String status;
    private String statusShort;
    private String goalsHomeTeam;
    private String goalsAwayTeam;
    private String halfTimeScore;
    private String finalScore;
    private String penalty;
    private String elapsed;
    private String firstHalfStart;
    private String secondHalfStart;

    public Fixture build(){
        return new Fixture(this.fixtureId, this.eventTimestamp, this.eventDate, this.leagueId,
                this.round, this.homeTeamId, this.awayTeamId, this.homeTeamName,
                this.awayTeamName, this.status, this.statusShort, this.goalsHomeTeam,
                this.goalsAwayTeam, this.halfTimeScore, this.finalScore, this.penalty,
                this.elapsed, this.firstHalfStart, this.secondHalfStart);
    }

    public FixtureBuilder withFixtureId(String fixtureId){
        this.fixtureId = fixtureId;
        return this;
    }

    public FixtureBuilder withEventTimestamp (String eventTimestamp){
        this.eventTimestamp = eventTimestamp;
        return this;
    }

    public FixtureBuilder withEventDate(LocalDateTime eventDate){
        this.eventDate = eventDate;
        return this;
    }


    public FixtureBuilder withLeagueId(String leagueId){
        this.leagueId = leagueId;
        return this;
    }

    public FixtureBuilder withRound(String round){
        this.round = round;
        return this;
    }

    public FixtureBuilder withHomeTeamId(String homeTeamId){
        this.homeTeamId = homeTeamId;
        return this;
    }

    public FixtureBuilder withAwayTeamId(String awayTeamId){
        this.awayTeamId = awayTeamId;
        return this;
    }

    public FixtureBuilder withHomeTeamName(String homeTeamName){
        this.homeTeamName = homeTeamName;
        return this;
    }

    public FixtureBuilder withAwayTeamName(String awayTeamName){
        this.awayTeamName = awayTeamName;
        return this;
    }

    public FixtureBuilder withStatus(String status){
        this.status = status;
        return this;
    }

    public FixtureBuilder withStatusShort(String statusShort){
        this.statusShort = statusShort;
        return this;
    }

    public FixtureBuilder withGoalsHomeTeam(String goalsHomeTeam){
        this.goalsHomeTeam = goalsHomeTeam;
        return this;
    }

    public FixtureBuilder withGoalsAwayTeam(String goalsAwayTeam){
        this.goalsAwayTeam = goalsAwayTeam;
        return this;
    }

    public FixtureBuilder withHalfTimeScore(String halfTimeScore){
        this.halfTimeScore = halfTimeScore;
        return this;
    }

    public FixtureBuilder withFinalScore(String finalScore){
        this.finalScore = finalScore;
        return this;
    }

    public FixtureBuilder withPenalty(String penalty){
        this.penalty = penalty;
        return this;
    }

    public FixtureBuilder withElapsed(String elapsed){
        this.elapsed = elapsed;
        return this;
    }

    public FixtureBuilder withFirstHalfStart(String firstHalfStart){
        this.firstHalfStart = firstHalfStart;
        return this;
    }

    public FixtureBuilder withSecondHalfStart(String secondHalfStart){
        this.secondHalfStart = secondHalfStart;
        return this;
    }

}
