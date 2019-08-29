package com.acorsetti.model.eval;

import java.util.Objects;

public class GoalExpectancy {

    private double homeValue;
    private double awayValue;

    public GoalExpectancy(double homeValue, double awayValue) {
        this.setHomeValue(homeValue);
        this.setAwayValue(awayValue);
    }

    public double getHomeValue() {
        return homeValue;
    }

    public double getAwayValue() {
        return awayValue;
    }

    public void setHomeValue(double homeValue) {
        this.homeValue = homeValue;
    }

    public void setAwayValue(double awayValue) {
        this.awayValue = awayValue;
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
