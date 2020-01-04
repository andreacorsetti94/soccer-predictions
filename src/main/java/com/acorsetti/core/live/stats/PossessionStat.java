package com.acorsetti.core.live.stats;

public class PossessionStat {

    private int homePossession;
    private int awayPossession;
    private int homePasses;
    private int awayPasses;
    private int homePassAccuracy;
    private int awayPassAccuracy;

    public PossessionStat(int homePossession, int awayPossession, int homePasses, int awayPasses, int homePassAccuracy, int awayPassAccuracy) {
        this.homePossession = homePossession;
        this.awayPossession = awayPossession;
        this.homePasses = homePasses;
        this.awayPasses = awayPasses;
        this.homePassAccuracy = homePasses == 0 ? 0 : homePassAccuracy*100/homePasses;
        this.awayPassAccuracy = awayPasses == 0 ? 0 : awayPassAccuracy*100/awayPasses;
    }

    public int getHomePossession() {
        return homePossession;
    }

    public int getAwayPossession() {
        return awayPossession;
    }

    public int getHomePasses() {
        return homePasses;
    }

    public int getAwayPasses() {
        return awayPasses;
    }

    public int getHomePassAccuracy() {
        return homePassAccuracy;
    }

    public int getAwayPassAccuracy() {
        return awayPassAccuracy;
    }

    @Override
    public String toString() {
        return "Possession. Home: " + (homePossession <= 0 ? "NO_DATA" : this.homePossession+"%") +
                ", Away: " + (awayPossession <= 0  ? "NO_DATA" : this.awayPossession)+"%" +
                "\nPasses. Home: " + (homePasses <= 0  ? "NO_DATA" : this.homePasses) +
                ", Away: " + (awayPasses <= 0  ? "NO_DATA" : this.awayPasses) +
                "\nAccuracy. Home: "+ (homePassAccuracy <= 0  ? "NO_DATA" : this.homePassAccuracy+"%") +
                ", Away: " + (awayPassAccuracy <= 0  ? "NO_DATA" : this.awayPassAccuracy+"%");
    }

    public boolean isValid(){
        return homePossession >= 0 && awayPossession >= 0;
    }

    public double getPressureIndexWeight(){
        return 4;

    }
}
