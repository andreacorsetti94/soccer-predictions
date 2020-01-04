package com.acorsetti.core.live.stats;

public class TotalShotsStat {

    private int homeTotalShots;
    private int awayTotalShots;

    public TotalShotsStat(int homeTotalShots, int awayTotalShots) {
        this.homeTotalShots = homeTotalShots;
        this.awayTotalShots = awayTotalShots;
    }

    public int getHomeTotalShots() {
        return homeTotalShots;
    }

    public int getAwayTotalShots() {
        return awayTotalShots;
    }

    @Override
    public String toString() {
        return "Total Shots. Home: " + (isValid() ? homeTotalShots : "NO_DATA") +
                ", Away: " + (isValid() ? awayTotalShots : "NO_DATA");
    }

    public boolean isValid(){
        return homeTotalShots >= 0 && awayTotalShots >= 0;
    }

    public double getPressureIndexWeight(){
        return 9;
    }
}
