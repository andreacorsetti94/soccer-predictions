package com.acorsetti.model.jpa;

import com.acorsetti.model.keys.StandingPositionCompositeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.time.LocalDate;

@Entity
@IdClass(StandingPositionCompositeKey.class)
public class StandingPosition {

    @Id
    private String leagueId;

    @Id
    private int rank;

    @Id
    private String teamName;

    private int played;
    private int won;
    private int draw;
    private int lost;
    private int goalsFor;
    private int goalsAgainst;
    private int goalsDiff;
    private int points;
    private String group;
    private LocalDate lastUpd;

    public StandingPosition() {
    }

    public StandingPosition(String leagueId, int rank, String teamName, int played, int won, int draw, int lost,
                            int goalsFor, int goalsAgainst, int goalsDiff, int points, String group, LocalDate lastUpd) {

        this.leagueId = leagueId;
        this.rank = rank;
        this.teamName = teamName;
        this.played = played;
        this.won = won;
        this.draw = draw;
        this.lost = lost;
        this.goalsFor = goalsFor;
        this.goalsAgainst = goalsAgainst;
        this.goalsDiff = goalsDiff;
        this.points = points;
        this.group = group;
        this.lastUpd = lastUpd;
    }

    public int getRank() {
        return rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlayed() {
        return played;
    }

    public int getWon() {
        return won;
    }

    public int getDraw() {
        return draw;
    }

    public int getLost() {
        return lost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalsDiff() {
        return goalsDiff;
    }

    public int getPoints() {
        return points;
    }

    public String getGroup() {
        return group;
    }

    public LocalDate getLastUpd() {
        return lastUpd;
    }

    public String getLeagueId(){ return this.leagueId; }

    public void setLeagueId(String leagueId) {
        this.leagueId = leagueId;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public void setGoalsDiff(int goalsDiff) {
        this.goalsDiff = goalsDiff;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setLastUpd(LocalDate lastUpd) {
        this.lastUpd = lastUpd;
    }

    @Override
    public String toString() {
        return "StandingPosition{" +
                " leagueId=" + leagueId +
                "  rank=" + rank +
                ", teamName='" + teamName + '\'' +
                ", points=" + points +
                ", played=" + played +
                ", won=" + won +
                ", draw=" + draw +
                ", lost=" + lost +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                ", goalsDiff=" + goalsDiff +
                ", group=" + group +
                '}';
    }

}
