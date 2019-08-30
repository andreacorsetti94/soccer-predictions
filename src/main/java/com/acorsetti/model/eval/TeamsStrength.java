package com.acorsetti.model.eval;

public class TeamsStrength {

    private double homeAttackStrength;
    private double homeDefenceStrength;
    private double awayAttackStrenth;
    private double awayDefenceStrenth;

    public TeamsStrength(double homeAttackStrength, double homeDefenceStrength, double awayAttackStrenth, double awayDefenceStrenth) {
        this.homeAttackStrength = homeAttackStrength;
        this.homeDefenceStrength = homeDefenceStrength;
        this.awayAttackStrenth = awayAttackStrenth;
        this.awayDefenceStrenth = awayDefenceStrenth;
    }

    public double getHomeAttackStrength() {
        return homeAttackStrength;
    }

    public double getHomeDefenceStrength() {
        return homeDefenceStrength;
    }

    public double getAwayAttackStrenth() {
        return awayAttackStrenth;
    }

    public double getAwayDefenceStrenth() {
        return awayDefenceStrenth;
    }

    @Override
    public String toString() {
        return "TeamsStrength{" +
                "homeAttackStrength=" + homeAttackStrength +
                ", homeDefenceStrength=" + homeDefenceStrength +
                ", awayAttackStrenth=" + awayAttackStrenth +
                ", awayDefenceStrenth=" + awayDefenceStrenth +
                '}';
    }
}
