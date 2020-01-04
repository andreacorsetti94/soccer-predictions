package com.acorsetti.core.live.stats;

public class OffsideStat {

    private int homeOffsides;
    private int awayOffsides;

    public OffsideStat(int homeOffsides, int awayOffsides) {
        this.homeOffsides = homeOffsides;
        this.awayOffsides = awayOffsides;
    }

    public int getHomeOffsides() {
        return homeOffsides;
    }

    public int getAwayOffsides() {
        return awayOffsides;
    }

    @Override
    public String toString() {
        return "Offsides. Home: " + (isValid() ? homeOffsides : "NO_DATA") +
                ", Away: " + (isValid() ? awayOffsides : "NO_DATA");
    }

    public boolean isValid(){
        return homeOffsides >= 0 && awayOffsides >= 0;
    }

    public double getPressureIndexWeight(){
        return 1.5;

    }
}
