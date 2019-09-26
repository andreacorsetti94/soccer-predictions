package com.acorsetti.core.model.dto;

public class TeamStatisticsDto {
    private int matchPlayedTotal;
    private int matchPlayedHome;
    private int matchPlayedAway;

    private int winsTotal;
    private int winsHome;
    private int winsAway;

    private int drawsTotal;
    private int drawsHome;
    private int drawsAway;

    private int losesTotal;
    private int losesHome;
    private int losesAway;

    private int goalsForHome;
    private int goalsForAway;
    private int goalsForTotal;

    private int goalsAgainstHome;
    private int goalsAgainstAway;
    private int goalsAgainstTotal;

    private double avgGoalsForHome;
    private double avgGoalsForAway;
    private double avgGoalsForTotal;
    private double avgGoalsAgainstHome;
    private double avgGoalsAgainstAway;
    private double avgGoalsAgainstTotal;

    public TeamStatisticsDto() {
    }

    public TeamStatisticsDto(int matchPlayedTotal, int matchPlayedHome, int matchPlayedAway, int winsTotal, int winsHome, int winsAway, int drawsTotal, int drawsHome, int drawsAway, int losesTotal, int losesHome, int losesAway, int goalsForHome, int goalsForAway, int goalsForTotal, int goalsAgainstHome, int goalsAgainstAway, int goalsAgainstTotal, double avgGoalsForHome, double avgGoalsForAway, double avgGoalsForTotal, double avgGoalsAgainstHome, double avgGoalsAgainstAway, double avgGoalsAgainstTotal) {
        this.matchPlayedTotal = matchPlayedTotal;
        this.matchPlayedHome = matchPlayedHome;
        this.matchPlayedAway = matchPlayedAway;
        this.winsTotal = winsTotal;
        this.winsHome = winsHome;
        this.winsAway = winsAway;
        this.drawsTotal = drawsTotal;
        this.drawsHome = drawsHome;
        this.drawsAway = drawsAway;
        this.losesTotal = losesTotal;
        this.losesHome = losesHome;
        this.losesAway = losesAway;
        this.goalsForHome = goalsForHome;
        this.goalsForAway = goalsForAway;
        this.goalsForTotal = goalsForTotal;
        this.goalsAgainstHome = goalsAgainstHome;
        this.goalsAgainstAway = goalsAgainstAway;
        this.goalsAgainstTotal = goalsAgainstTotal;
        this.avgGoalsForHome = avgGoalsForHome;
        this.avgGoalsForAway = avgGoalsForAway;
        this.avgGoalsForTotal = avgGoalsForTotal;
        this.avgGoalsAgainstHome = avgGoalsAgainstHome;
        this.avgGoalsAgainstAway = avgGoalsAgainstAway;
        this.avgGoalsAgainstTotal = avgGoalsAgainstTotal;
    }

    public int getMatchPlayedTotal() {
        return matchPlayedTotal;
    }

    public void setMatchPlayedTotal(int matchPlayedTotal) {
        this.matchPlayedTotal = matchPlayedTotal;
    }

    public int getMatchPlayedHome() {
        return matchPlayedHome;
    }

    public void setMatchPlayedHome(int matchPlayedHome) {
        this.matchPlayedHome = matchPlayedHome;
    }

    public int getMatchPlayedAway() {
        return matchPlayedAway;
    }

    public void setMatchPlayedAway(int matchPlayedAway) {
        this.matchPlayedAway = matchPlayedAway;
    }

    public int getWinsTotal() {
        return winsTotal;
    }

    public void setWinsTotal(int winsTotal) {
        this.winsTotal = winsTotal;
    }

    public int getWinsHome() {
        return winsHome;
    }

    public void setWinsHome(int winsHome) {
        this.winsHome = winsHome;
    }

    public int getWinsAway() {
        return winsAway;
    }

    public void setWinsAway(int winsAway) {
        this.winsAway = winsAway;
    }

    public int getDrawsTotal() {
        return drawsTotal;
    }

    public void setDrawsTotal(int drawsTotal) {
        this.drawsTotal = drawsTotal;
    }

    public int getDrawsHome() {
        return drawsHome;
    }

    public void setDrawsHome(int drawsHome) {
        this.drawsHome = drawsHome;
    }

    public int getDrawsAway() {
        return drawsAway;
    }

    public void setDrawsAway(int drawsAway) {
        this.drawsAway = drawsAway;
    }

    public int getLosesTotal() {
        return losesTotal;
    }

    public void setLosesTotal(int losesTotal) {
        this.losesTotal = losesTotal;
    }

    public int getLosesHome() {
        return losesHome;
    }

    public void setLosesHome(int losesHome) {
        this.losesHome = losesHome;
    }

    public int getLosesAway() {
        return losesAway;
    }

    public void setLosesAway(int losesAway) {
        this.losesAway = losesAway;
    }

    public int getGoalsForHome() {
        return goalsForHome;
    }

    public void setGoalsForHome(int goalsForHome) {
        this.goalsForHome = goalsForHome;
    }

    public int getGoalsForAway() {
        return goalsForAway;
    }

    public void setGoalsForAway(int goalsForAway) {
        this.goalsForAway = goalsForAway;
    }

    public int getGoalsForTotal() {
        return goalsForTotal;
    }

    public void setGoalsForTotal(int goalsForTotal) {
        this.goalsForTotal = goalsForTotal;
    }

    public int getGoalsAgainstHome() {
        return goalsAgainstHome;
    }

    public void setGoalsAgainstHome(int goalsAgainstHome) {
        this.goalsAgainstHome = goalsAgainstHome;
    }

    public int getGoalsAgainstAway() {
        return goalsAgainstAway;
    }

    public void setGoalsAgainstAway(int goalsAgainstAway) {
        this.goalsAgainstAway = goalsAgainstAway;
    }

    public int getGoalsAgainstTotal() {
        return goalsAgainstTotal;
    }

    public void setGoalsAgainstTotal(int goalsAgainstTotal) {
        this.goalsAgainstTotal = goalsAgainstTotal;
    }

    public double getAvgGoalsForHome() {
        return avgGoalsForHome;
    }

    public void setAvgGoalsForHome(double avgGoalsForHome) {
        this.avgGoalsForHome = avgGoalsForHome;
    }

    public double getAvgGoalsForAway() {
        return avgGoalsForAway;
    }

    public void setAvgGoalsForAway(double avgGoalsForAway) {
        this.avgGoalsForAway = avgGoalsForAway;
    }

    public double getAvgGoalsForTotal() {
        return avgGoalsForTotal;
    }

    public void setAvgGoalsForTotal(double avgGoalsForTotal) {
        this.avgGoalsForTotal = avgGoalsForTotal;
    }

    public double getAvgGoalsAgainstHome() {
        return avgGoalsAgainstHome;
    }

    public void setAvgGoalsAgainstHome(double avgGoalsAgainstHome) {
        this.avgGoalsAgainstHome = avgGoalsAgainstHome;
    }

    public double getAvgGoalsAgainstAway() {
        return avgGoalsAgainstAway;
    }

    public double getAvgGoalsAgainstTotal() {
        return avgGoalsAgainstTotal;
    }

    public void setAvgGoalsAgainstAway(double avgGoalsAgainstAway) {
        this.avgGoalsAgainstAway = avgGoalsAgainstAway;
    }

    public void setAvgGoalsAgainstTotal(double avgGoalsAgainstTotal) {
        this.avgGoalsAgainstTotal = avgGoalsAgainstTotal;
    }
}
