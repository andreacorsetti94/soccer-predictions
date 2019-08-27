package com.acorsetti.model.eval;

import java.util.List;

public class FixtureEvals {
    private String fixtureId;
    private List<OutcomeEvaluation> outcomeEvaluations;

    public FixtureEvals(String fixtureId, List<OutcomeEvaluation> outcomeEvaluations) {
        this.fixtureId = fixtureId;
        this.outcomeEvaluations = outcomeEvaluations;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public List<OutcomeEvaluation> getOutcomeEvaluations() {
        return outcomeEvaluations;
    }

    @Override
    public String toString() {
        return "FixtureEvals{" +
                "fixtureId='" + fixtureId + '\'' +
                ", outcomeEvaluations=" + outcomeEvaluations +
                '}';
    }
}
