package com.acorsetti.core.model.jpa;

import com.acorsetti.core.model.eval.GoalExpectancy;
import com.acorsetti.core.model.keys.GoalExpectedEntityKey;
import com.acorsetti.core.service.probabilities.GoalExpectancyCalculatorService;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@IdClass(GoalExpectedEntityKey.class)
@Table(name = "goal_expectancy")
public class GoalExpectancyEntity {

    @Id
    private String fixtureId;

    @Id
    private String calculator;

    private double homeGE;
    private double awayGE;


    public GoalExpectancyEntity() {
    }

    public GoalExpectancyEntity(String fixtureId, double homeGE, double awayGE, String calculator) {
        this.fixtureId = fixtureId;
        this.homeGE = homeGE;
        this.awayGE = awayGE;
        this.calculator = calculator;
    }

    public GoalExpectancyEntity(String fixtureId, GoalExpectancy goalExpectancy, String calculator){
        this.homeGE = goalExpectancy.getHomeValue();
        this.awayGE = goalExpectancy.getAwayValue();
        this.fixtureId = fixtureId;
        this.calculator = calculator;
    }

    @Override
    public String toString() {
        return "GoalExpectancyEntity{" +
                "fixtureId='" + fixtureId + '\'' +
                ", homeGE=" + homeGE +
                ", awayGE=" + awayGE +
                ", calculator=" + calculator +
                '}';
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public void setFixtureId(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    public double getHomeGE() {
        return homeGE;
    }

    public void setHomeGE(double homeGE) {
        this.homeGE = homeGE;
    }

    public double getAwayGE() {
        return awayGE;
    }

    public void setAwayGE(double awayGE) {
        this.awayGE = awayGE;
    }

    public String getCalculator() {
        return calculator;
    }

    public void setCalculator(String calculator) {
        this.calculator = calculator;
    }
}
