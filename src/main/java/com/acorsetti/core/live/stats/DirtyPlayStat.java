package com.acorsetti.core.live.stats;

public class DirtyPlayStat {
    private int homeFouls;
    private int awayFouls;
    private int homeYellowCards;
    private int awayYellowCards;
    private int homeRedCards;
    private int awayRedCards;


    public DirtyPlayStat(int homeFouls, int awayFouls, int homeYellowCards, int awayYellowCards, int homeRedCards, int awayRedCards) {
        this.homeFouls = homeFouls;
        this.awayFouls = awayFouls;
        this.homeYellowCards = homeYellowCards;
        this.awayYellowCards = awayYellowCards;
        this.homeRedCards = homeRedCards;
        this.awayRedCards = awayRedCards;
    }

    @Override
    public String toString() {
        return "Fouls. Home: " + (homeFouls == -1 ? "NO_DATA" : this.homeFouls) +
                ", Away: " + (awayFouls == -1 ? "NO_DATA" : this.awayFouls) +
                "\nYellow Cards. Home: " + (homeYellowCards == -1 ? 0 : this.homeYellowCards) +
                ", Away: " + (awayYellowCards == -1 ? 0 : this.awayYellowCards) +
                "\nRed Cards. Home: " + (homeRedCards == -1 ? 0 : this.homeRedCards) +
                ", Away: " + (awayRedCards == -1 ? 0 : this.awayRedCards);
    }

    public int getHomeFouls() {
        return homeFouls;
    }

    public int getAwayFouls() {
        return awayFouls;
    }

    public int getHomeYellowCards() {
        return homeYellowCards;
    }

    public int getAwayYellowCards() {
        return awayYellowCards;
    }

    public int getHomeRedCards() {
        return homeRedCards;
    }

    public int getAwayRedCards() {
        return awayRedCards;
    }

    public double getRedCardPressureIndexWeight(){
        return 35;

    }

    public double getYellowCardPressureIndexWeight(){
        return 3;

    }

}
