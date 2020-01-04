package com.acorsetti.core.live.stats;

public class OutboxShotsStat {

    private int homeOutboxShots;
    private int awayOutboxShots;

    public OutboxShotsStat(int homeOutboxShots, int awayOutboxShots) {
        this.homeOutboxShots = homeOutboxShots;
        this.awayOutboxShots = awayOutboxShots;
    }

    public int getHomeOutboxShots() {
        return homeOutboxShots;
    }

    public int getAwayOutboxShots() {
        return awayOutboxShots;
    }

    @Override
    public String toString() {
        return "Shots Outside the Box. Home: " + (isValid() ? homeOutboxShots : "NO_DATA") +
                ", Away: " + (isValid() ? awayOutboxShots : "NO_DATA");
    }

    public boolean isValid(){
        return homeOutboxShots >= 0 && awayOutboxShots >= 0;
    }

    public double getPressureIndexWeight(){
        return 1.8;

    }
}
