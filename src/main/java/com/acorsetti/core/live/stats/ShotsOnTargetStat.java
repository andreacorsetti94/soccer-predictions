package com.acorsetti.core.live.stats;


public class ShotsOnTargetStat {

    private int homeShotsOnTarget;
    private int awayShotsOnTarget;


    public ShotsOnTargetStat(int homeShotsOnTarget, int awayShotsOnTarget) {
        this.homeShotsOnTarget = homeShotsOnTarget;
        this.awayShotsOnTarget = awayShotsOnTarget;
    }

    public int getHomeShotsOnTarget() {
        return homeShotsOnTarget;
    }

    public int getAwayShotsOnTarget() {
        return awayShotsOnTarget;
    }

    @Override
    public String toString() {
        return "Shots on Target. Home: " + (isValid() ? homeShotsOnTarget : "NO_DATA") +
                ", Away: " + (isValid() ? awayShotsOnTarget : "NO_DATA");
    }

    public boolean isValid(){
        return homeShotsOnTarget >= 0 && awayShotsOnTarget >= 0;
    }

    public double getPressureIndexWeight(){
        return 12;
    }
}
