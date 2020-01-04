package com.acorsetti.core.live;

import java.util.Objects;

public class TimedMatchStatistics {

    private int elapsed;
    private MatchStatistics matchStatistics;

    public TimedMatchStatistics(MatchStatistics matchStatistics, int elapsed) {
        this.elapsed = elapsed;
        this.matchStatistics = matchStatistics;
    }

    public MatchStatistics getMatchStatistics() {
        return matchStatistics;
    }

    @Override
    public String toString() {
        return "TimedMatchStatistics{" +
                " elapsed=" + elapsed +
                ", matchStatistics=\n" + matchStatistics +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimedMatchStatistics that = (TimedMatchStatistics) o;
        return elapsed == that.elapsed &&
                Objects.equals(matchStatistics, that.matchStatistics);
    }

    @Override
    public int hashCode() {
        return Objects.hash(elapsed, matchStatistics);
    }

    public int getElapsed() {
        return elapsed;
    }
}
