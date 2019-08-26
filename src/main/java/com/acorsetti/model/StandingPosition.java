package com.acorsetti.model;

import com.acorsetti.model.keys.StandingPositionCompositeKey;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(StandingPositionCompositeKey.class)
public class StandingPosition {

    @Id
    private String leagueId;

    @Id
    private String rank;

    @Id
    private String teamName;

    private int played;
    private int wonMatches;
    private int drawMatches;
    private int lostMatches;
    private int goalsScored;
    private int goalsReceived;
    private int goalsDiff;
    private int points;
    private String group;
    private String lastUpd;

    public StandingPosition() {
    }

    public StandingPosition(String leagueId, String rank, String teamName, int played, int wonMatches, int drawMatches, int lostMatches,
                            int goalsScored, int goalsReceived, int goalsDiff, int points, String group, String lastUpd) {

        this.leagueId = leagueId;
        this.rank = rank;
        this.teamName = teamName;
        this.played = played;
        this.wonMatches = wonMatches;
        this.drawMatches = drawMatches;
        this.lostMatches = lostMatches;
        this.goalsScored = goalsScored;
        this.goalsReceived = goalsReceived;
        this.goalsDiff = goalsDiff;
        this.points = points;
        this.group = group;
        this.lastUpd = lastUpd;
    }

    public String getRank() {
        return rank;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getPlayed() {
        return played;
    }

    public int getWonMatches() {
        return wonMatches;
    }

    public int getDrawMatches() {
        return drawMatches;
    }

    public int getLostMatches() {
        return lostMatches;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public int getGoalsReceived() {
        return goalsReceived;
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

    public String getLastUpd() {
        return lastUpd;
    }

    public String getLeagueId(){ return this.leagueId; }

    @Override
    public String toString() {
        return "StandingPosition{" +
                " leagueId=" + leagueId +
                "  rank=" + rank +
                ", teamName='" + teamName + '\'' +
                ", points=" + points +
                ", played=" + played +
                ", wonMatches=" + wonMatches +
                ", drawMatches=" + drawMatches +
                ", lostMatches=" + lostMatches +
                ", goalsScored=" + goalsScored +
                ", goalsReceived=" + goalsReceived +
                ", goalsDiff=" + goalsDiff +
                ", group=" + group +
                '}';
    }

}
