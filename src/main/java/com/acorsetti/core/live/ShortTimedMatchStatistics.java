package com.acorsetti.core.live;

public class ShortTimedMatchStatistics extends TimedMatchStatistics {

    private int lastXMinutes;
    public ShortTimedMatchStatistics(MatchStatistics matchStatistics, int elapsed, int lastXMinutes) {
        super(matchStatistics, elapsed);
        this.lastXMinutes = lastXMinutes;
    }

    public int getLastXMinutes() {
        return lastXMinutes;
    }

    @Override
    public String toString() {
        return "ShortTimedMatchStatistics{" +
                "lastXMinutes=" + lastXMinutes +
                "} " + super.toString();
    }


}
