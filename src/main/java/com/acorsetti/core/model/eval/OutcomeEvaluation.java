package com.acorsetti.core.model.eval;

public class OutcomeEvaluation {

    private MarketOutcome marketOutcome;
    private Chance chance;

    public OutcomeEvaluation(MarketOutcome marketOutcome, Chance chance) {
        this.marketOutcome = marketOutcome;
        this.chance = chance;
    }

    public MarketOutcome getMarketOutcome() {
        return marketOutcome;
    }

    public Chance getChance() {
        return chance;
    }

    @Override
    public String toString() {
        return "OutcomeEvaluation{" +
                "marketOutcome=" + marketOutcome +
                ", chance=" + chance +
                '}';
    }
}
