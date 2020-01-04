package com.acorsetti.core.live;

import com.acorsetti.core.live.stats.*;

import java.util.Objects;

public class MatchStatistics {
    private String fixtureId;
    private ShotsOnTargetStat shotsOnTargetStat;
    private ShotsOffTargetStat shotsOffTargetStat;
    private BlockedShotsStat blockedShotsStat;
    private TotalShotsStat totalShotsStat;
    private InboxShotsStat inboxShotsStat;
    private OutboxShotsStat outboxShotsStat;
    private KeeperSavesStat keeperSavesStat;
    private PossessionStat possessionStat;
    private CornerStat cornerStat;
    private OffsideStat offsideStat;
    private DirtyPlayStat dirtyPlayStat;

    public MatchStatistics(ShotsOnTargetStat shotsOnTargetStat, ShotsOffTargetStat shotsOffTargetStat, BlockedShotsStat blockedShotsStat, TotalShotsStat totalShotsStat, InboxShotsStat inboxShotsStat, OutboxShotsStat outboxShotsStat, KeeperSavesStat keeperSavesStat, PossessionStat possessionStat, CornerStat cornerStat, OffsideStat offsideStat, DirtyPlayStat dirtyPlayStat) {
        this.shotsOnTargetStat = shotsOnTargetStat;
        this.shotsOffTargetStat = shotsOffTargetStat;
        this.blockedShotsStat = blockedShotsStat;
        this.totalShotsStat = totalShotsStat;
        this.inboxShotsStat = inboxShotsStat;
        this.outboxShotsStat = outboxShotsStat;
        this.keeperSavesStat = keeperSavesStat;
        this.possessionStat = possessionStat;
        this.cornerStat = cornerStat;
        this.offsideStat = offsideStat;
        this.dirtyPlayStat = dirtyPlayStat;
    }

    public String getFixtureId() {
        return fixtureId;
    }

    public ShotsOnTargetStat getShotsOnTargetStat() {
        return shotsOnTargetStat;
    }

    public ShotsOffTargetStat getShotsOffTargetStat() {
        return shotsOffTargetStat;
    }

    public BlockedShotsStat getBlockedShotsStat() {
        return blockedShotsStat;
    }

    public TotalShotsStat getTotalShotsStat() {
        return totalShotsStat;
    }

    public InboxShotsStat getInboxShotsStat() {
        return inboxShotsStat;
    }

    public OutboxShotsStat getOutboxShotsStat() {
        return outboxShotsStat;
    }

    public KeeperSavesStat getKeeperSavesStat() {
        return keeperSavesStat;
    }

    public PossessionStat getPossessionStat() {
        return possessionStat;
    }

    public CornerStat getCornerStat() {
        return cornerStat;
    }

    public OffsideStat getOffsideStat() {
        return offsideStat;
    }

    public DirtyPlayStat getDirtyPlayStat() {
        return dirtyPlayStat;
    }

    public void setFixtureId(String fixtureId) {
        this.fixtureId = fixtureId;
    }

    @Override
    public String toString() {
        return "MatchStatistics{" +
                "fixtureId='" + fixtureId + '\'' +
                "\n" + shotsOnTargetStat +
                "\n" + shotsOffTargetStat +
                "\n" + blockedShotsStat +
                "\n" + totalShotsStat +
                "\n" + inboxShotsStat +
                "\n" + outboxShotsStat +
                "\n" + keeperSavesStat +
                "\n" + possessionStat +
                "\n" + cornerStat +
                "\n" + offsideStat +
                "\n" + dirtyPlayStat +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchStatistics that = (MatchStatistics) o;
        return Objects.equals(fixtureId, that.fixtureId) &&
                Objects.equals(shotsOnTargetStat, that.shotsOnTargetStat) &&
                Objects.equals(shotsOffTargetStat, that.shotsOffTargetStat) &&
                Objects.equals(blockedShotsStat, that.blockedShotsStat) &&
                Objects.equals(totalShotsStat, that.totalShotsStat) &&
                Objects.equals(inboxShotsStat, that.inboxShotsStat) &&
                Objects.equals(outboxShotsStat, that.outboxShotsStat) &&
                Objects.equals(keeperSavesStat, that.keeperSavesStat) &&
                Objects.equals(possessionStat, that.possessionStat) &&
                Objects.equals(cornerStat, that.cornerStat) &&
                Objects.equals(offsideStat, that.offsideStat) &&
                Objects.equals(dirtyPlayStat, that.dirtyPlayStat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fixtureId, shotsOnTargetStat, shotsOffTargetStat, blockedShotsStat, totalShotsStat, inboxShotsStat, outboxShotsStat, keeperSavesStat, possessionStat, cornerStat, offsideStat, dirtyPlayStat);
    }
}
