package com.acorsetti.core.live.stats;


public class KeeperSavesStat {
    private int homeKeeperSaves;
    private int awayKeeperSaves;


    public KeeperSavesStat(int homeKeeperSaves, int awayKeeperSaves) {
        this.homeKeeperSaves = homeKeeperSaves;
        this.awayKeeperSaves = awayKeeperSaves;
    }

    public int getHomeKeeperSaves() {
        return homeKeeperSaves;
    }

    public int getAwayKeeperSaves() {
        return awayKeeperSaves;
    }

    @Override
    public String toString() {
        return "GoalKeeper Saves. Home: " + (isValid() ? homeKeeperSaves : "NO_DATA") +
                ", Away: " + (isValid() ? awayKeeperSaves : "NO_DATA");
    }

    public boolean isValid(){
        return homeKeeperSaves >= 0 && awayKeeperSaves >= 0;
    }

    public double getPressureIndexWeight(){
        return 8;

    }
}
