package com.acorsetti.model.jpa;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="Fixture")
public class Fixture {

    @Id
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

    public Fixture() {
    }

    protected Fixture(String fixtureId, String eventTimestamp, LocalDateTime eventDate, String leagueId,
                   String round, String homeTeamId, String awayTeamId, String homeTeamName,
                   String awayTeamName, String status, String statusShort, String goalsHomeTeam,
                   String goalsAwayTeam, String halfTimeScore, String finalScore, String penalty,
                   String elapsed, String firstHalfStart, String secondHalfStart) {

        this.fixtureId = fixtureId;
        this.eventTimestamp = eventTimestamp;
        this.eventDate = eventDate;
        this.leagueId = leagueId;
        this.round = round;
        this.homeTeamId = homeTeamId;
        this.awayTeamId = awayTeamId;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.status = status;
        this.statusShort = statusShort;
        this.goalsHomeTeam = goalsHomeTeam;
        this.goalsAwayTeam = goalsAwayTeam;
        this.halfTimeScore = halfTimeScore;
        this.finalScore = finalScore;
        this.penalty = penalty;
        this.elapsed = elapsed;
        this.firstHalfStart = firstHalfStart;
        this.secondHalfStart = secondHalfStart;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public String getEventTimestamp() {
        return eventTimestamp;
    }

    public LocalDateTime getEventDate() {
        return eventDate;
    }

    public String getLeagueId() {
        return leagueId;
    }

    public String getRound() {
        return round;
    }

    public String getHomeTeamId() {
        return homeTeamId;
    }

    public String getAwayTeamId() {
        return awayTeamId;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public String getStatus() {
        return status;
    }

    public String getStatusShort() {
        return statusShort;
    }

    public String getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public String getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public String getHalfTimeScore() {
        return halfTimeScore;
    }

    public String getFinalScore() {
        return finalScore;
    }

    public String getPenalty() {
        return penalty;
    }

    public String getElapsed() {
        return elapsed;
    }

    public String getFirstHalfStart() {
        return firstHalfStart;
    }

    public String getSecondHalfStart() {
        return secondHalfStart;
    }

    public void setFixtureId(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    public void setEventTimestamp(String eventTimestamp) {
        this.eventTimestamp = eventTimestamp;
    }

    public void setEventDate(LocalDateTime eventDate) {
        this.eventDate = eventDate;
    }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public void setHomeTeamId(String homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public void setAwayTeamId(String awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setStatusShort(String statusShort) {
        this.statusShort = statusShort;
    }

    public void setGoalsHomeTeam(String goalsHomeTeam) {
        this.goalsHomeTeam = goalsHomeTeam;
    }

    public void setGoalsAwayTeam(String goalsAwayTeam) {
        this.goalsAwayTeam = goalsAwayTeam;
    }

    public void setHalfTimeScore(String halfTimeScore) {
        this.halfTimeScore = halfTimeScore;
    }

    public void setFinalScore(String finalScore) {
        this.finalScore = finalScore;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    public void setElapsed(String elapsed) {
        this.elapsed = elapsed;
    }

    public void setFirstHalfStart(String firstHalfStart) {
        this.firstHalfStart = firstHalfStart;
    }

    public void setSecondHalfStart(String secondHalfStart) {
        this.secondHalfStart = secondHalfStart;
    }

    @Override
    public String toString() {

        String match = "Match: " + homeTeamName + " vs " + awayTeamName + " = " + goalsHomeTeam + "-" + goalsAwayTeam;

        return "Fixture{" +
                "fixtureId='" + fixtureId + '\'' +
                ", Date='" + eventDate + '\'' +
                ", " + match +
                ", status: '" + status + '\'' +
                ", finalScore: '" + finalScore + '\'' +
                ", elapsed='" + elapsed + '\'' +
                ", leagueId='" + leagueId + '\'' +
                ", round='" + round + '\'' +
                ", homeTeamId='" + homeTeamId + '\'' +
                ", awayTeamId='" + awayTeamId + '\'' +
                ", statusShort='" + statusShort + '\'' +
                ", halfTimeScore='" + halfTimeScore + '\'' +
                ", penalty='" + penalty + '\'' +
                '}';
    }

    /**
     * Equals does not include ID parameter
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fixture fixture = (Fixture) o;
        return  Objects.equals(eventTimestamp, fixture.eventTimestamp) &&
                Objects.equals(homeTeamId, fixture.homeTeamId) &&
                Objects.equals(awayTeamId, fixture.awayTeamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixtureId, eventTimestamp, homeTeamId, awayTeamId);
    }
}
