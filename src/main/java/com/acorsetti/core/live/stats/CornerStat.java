package com.acorsetti.core.live.stats;

public class CornerStat {

    private int homeCorners;
    private int awayCorners;
    public CornerStat(int homeCorners, int awayCorners) {
        this.homeCorners = homeCorners;
        this.awayCorners = awayCorners;
    }

    public int getHomeCorners() {
        return homeCorners;
    }

    public int getAwayCorners() {
        return awayCorners;
    }

    @Override
    public String toString() {
        return "Corners. Home: " + (isValid() ? homeCorners : "NO_DATA")+
                ", Away: " + (isValid() ? awayCorners : "NO_DATA");
    }

    public boolean isValid(){
        return homeCorners >= 0 && awayCorners >= 0;
    }

    public double getPressureIndexWeight(){
        return 2;
    }
}
