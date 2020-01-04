package com.acorsetti.core.live;

public class MatchAnalysis {
    private double pressureIndex;
    private double activityIndex;

    public MatchAnalysis(double pressureIndex, double activityIndex) {
        this.pressureIndex = pressureIndex;
        this.activityIndex = activityIndex;
    }

    public double getPressureIndex() {
        return pressureIndex;
    }

    public double getActivityIndex() {
        return activityIndex;
    }

    @Override
    public String toString() {
        return "PressureIndex: " + pressureIndex +
                ", activityIndex: " + activityIndex;
    }
}
