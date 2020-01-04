package com.acorsetti.core.live.stats;


public class BlockedShotsStat {

    private int homeBlockedShots;
    private int awayBlockedShots;

    public BlockedShotsStat(int homeBlockedShots, int awayBlockedShots) {
        this.homeBlockedShots = homeBlockedShots;
        this.awayBlockedShots = awayBlockedShots;
    }

    public int getHomeBlockedShots() {
        return homeBlockedShots;
    }

    public int getAwayBlockedShots() {
        return awayBlockedShots;
    }

    @Override
    public String toString() {
        return "Blocked Shots. Home: " + (isValid() ? homeBlockedShots : "NO_DATA") +
                ", Away: " + (isValid() ? awayBlockedShots : "NO_DATA");
    }

    public boolean isValid(){
        return homeBlockedShots >= 0 && awayBlockedShots >= 0;
    }

    public double getPressureIndexWeight(){
        return 4.5;
    }
}
