package com.acorsetti.core.model.eval;

public class TeamsForm {

    private double homeTeamForm;
    private double awayTeamForm;

    public TeamsForm(double homeTeamForm, double awayTeamForm) {
        this.homeTeamForm = homeTeamForm;
        this.awayTeamForm = awayTeamForm;
    }

    public double getHomeTeamForm() {
        return homeTeamForm;
    }

    public double getAwayTeamForm() {
        return awayTeamForm;
    }

    @Override
    public String toString() {
        return "TeamsForm{" +
                "homeTeamForm=" + homeTeamForm +
                ", awayTeamForm=" + awayTeamForm +
                '}';
    }
}
