package com.acorsetti.core.live.stats;

public class ShotsOffTargetStat {

    private int homeShotsOffTarget;
    private int awayShotsOffTarget;


    public ShotsOffTargetStat(int homeShotsOffTarget, int awayShotsOffTarget) {
        this.homeShotsOffTarget = homeShotsOffTarget;
        this.awayShotsOffTarget = awayShotsOffTarget;
    }

    public int getHomeShotsOffTarget() {
        return homeShotsOffTarget;
    }

    public int getAwayShotsOffTarget() {
        return awayShotsOffTarget;
    }

    @Override
    public String toString() {
        return "Shots Off Target. Home: " + (isValid() ? homeShotsOffTarget : "NO_DATA") +
                ", Away: "+ (isValid() ? awayShotsOffTarget : "NO_DATA");
    }

    public boolean isValid(){
        return homeShotsOffTarget >= 0 && awayShotsOffTarget >= 0;
    }

    public double getPressureIndexWeight(){
        return 4;

    }
}
