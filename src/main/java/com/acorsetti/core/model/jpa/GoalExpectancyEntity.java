package com.acorsetti.core.model.jpa;

import com.acorsetti.core.model.eval.GoalExpectancy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goal_expectancy")
public class GoalExpectancyEntity {

    @Id
    private String fixtureId;
    private double homeGE;
    private double awayGE;

    public GoalExpectancyEntity() {
    }

    public GoalExpectancyEntity(String fixtureId, double homeGE, double awayGE) {
        this.fixtureId = fixtureId;
        this.homeGE = homeGE;
        this.awayGE = awayGE;
    }

    public GoalExpectancyEntity(String fixtureId, GoalExpectancy goalExpectancy){
        this.homeGE = goalExpectancy.getHomeValue();
        this.awayGE = goalExpectancy.getAwayValue();
        this.fixtureId = fixtureId;
    }

    @Override
    public String toString() {
        return "GoalExpectancyEntity{" +
                "fixtureId='" + fixtureId + '\'' +
                ", homeGE=" + homeGE +
                ", awayGE=" + awayGE +
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
}
