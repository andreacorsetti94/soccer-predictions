package com.acorsetti.core.live.stats;

public class InboxShotsStat {

    private int homeInboxShots;
    private int awayInboxShots;


    public InboxShotsStat(int homeInboxShots, int awayInboxShots) {
        this.homeInboxShots = homeInboxShots;
        this.awayInboxShots = awayInboxShots;
    }

    public int getHomeInboxShots() {
        return homeInboxShots;
    }

    public int getAwayInboxShots() {
        return awayInboxShots;
    }

    @Override
    public String toString() {
        return "Shots Inside the Box. Home: " + (isValid() ? homeInboxShots : "NO_DATA")  +
                ", Away: " + (isValid() ? awayInboxShots : "NO_DATA");
    }

    public boolean isValid(){
        return homeInboxShots >= 0 && awayInboxShots >= 0;
    }

    public double getPressureIndexWeight(){
        return 11;
    }
}
