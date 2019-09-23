package com.acorsetti.model.eval;

import java.util.Objects;

public class GoalExpectancy {
    private static final double MIN_LEGIT_VALUE = 0.3;

    private double homeValue;
    private double awayValue;
    private boolean isLegit = true;

    public GoalExpectancy(double homeValue, double awayValue) {
        this.setHomeValue(homeValue);
        this.setAwayValue(awayValue);
    }


    public GoalExpectancy(double homeValue, double awayValue, boolean isLegit) {
        this.setHomeValue(homeValue);
        this.setAwayValue(awayValue);
        this.isLegit = isLegit;
    }

    public double getHomeValue() {
        return homeValue;
    }

    public double getAwayValue() {
        return awayValue;
    }

    public void setHomeValue(double homeValue) {
        if ( homeValue <= MIN_LEGIT_VALUE ){
            this.homeValue = MIN_LEGIT_VALUE;
        }
        else{
            this.homeValue = homeValue;
        }
    }

    public void setAwayValue(double awayValue) {
        if ( awayValue <= MIN_LEGIT_VALUE ){
            this.awayValue = MIN_LEGIT_VALUE;
        }
        else{
            this.awayValue = awayValue;
        }
    }

    public boolean isLegit() {
        return isLegit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoalExpectancy that = (GoalExpectancy) o;
        return Double.compare(that.homeValue, homeValue) == 0 &&
                Double.compare(that.awayValue, awayValue) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeValue, awayValue);
    }

    @Override
    public String toString() {
        return "GoalExpectancy{" +
                "homeValue=" + homeValue +
                ", awayValue=" + awayValue +
                '}';
    }
}
