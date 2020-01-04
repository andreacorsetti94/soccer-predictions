package com.acorsetti.core.live;

import com.acorsetti.core.live.stats.*;

public class TimedMatchStatisticsDiffCalculator {

    public ShortTimedMatchStatistics diff(TimedMatchStatistics past, TimedMatchStatistics current){
        MatchStatistics m1 = past.getMatchStatistics();
        MatchStatistics m2 = current.getMatchStatistics();
        if (! m1.getFixtureId().equals(m2.getFixtureId()) ){
            System.out.println("WARN. Trying to diff different matches aborted.");
            return null;
        }
        if ( past.getElapsed() >= current.getElapsed() && past.getElapsed() < 90 ) {
            System.out.println("WARN. Past elapsed >= current elapsed AND Past elapsed < 90");
            return null;
        }

        ShotsOnTargetStat shotsOnTargetStat = new ShotsOnTargetStat(m2.getShotsOnTargetStat().getHomeShotsOnTarget()-m1.getShotsOnTargetStat().getHomeShotsOnTarget(), m2.getShotsOnTargetStat().getAwayShotsOnTarget()-m1.getShotsOnTargetStat().getAwayShotsOnTarget());
        ShotsOffTargetStat shotsOffTargetStat = new ShotsOffTargetStat(m2.getShotsOffTargetStat().getHomeShotsOffTarget()-m1.getShotsOffTargetStat().getHomeShotsOffTarget(), m2.getShotsOffTargetStat().getAwayShotsOffTarget()-m1.getShotsOffTargetStat().getAwayShotsOffTarget());
        BlockedShotsStat blockedShotsStat = new BlockedShotsStat(m2.getBlockedShotsStat().getHomeBlockedShots()-m1.getBlockedShotsStat().getHomeBlockedShots(), m2.getBlockedShotsStat().getAwayBlockedShots()-m1.getBlockedShotsStat().getAwayBlockedShots());
        TotalShotsStat totalShotsStat = new TotalShotsStat(m2.getTotalShotsStat().getHomeTotalShots()-m1.getTotalShotsStat().getHomeTotalShots(), m2.getTotalShotsStat().getAwayTotalShots()-m1.getTotalShotsStat().getAwayTotalShots());
        InboxShotsStat inboxShotsStat = new InboxShotsStat(m2.getInboxShotsStat().getHomeInboxShots()-m1.getInboxShotsStat().getHomeInboxShots(), m2.getInboxShotsStat().getAwayInboxShots()-m1.getInboxShotsStat().getAwayInboxShots());
        OutboxShotsStat outboxShotsStat = new OutboxShotsStat(m2.getOutboxShotsStat().getHomeOutboxShots()-m1.getOutboxShotsStat().getHomeOutboxShots(), m2.getOutboxShotsStat().getAwayOutboxShots()-m1.getOutboxShotsStat().getAwayOutboxShots());
        KeeperSavesStat keeperSavesStat = new KeeperSavesStat(m2.getKeeperSavesStat().getHomeKeeperSaves()-m1.getKeeperSavesStat().getHomeKeeperSaves(), m2.getKeeperSavesStat().getAwayKeeperSaves()-m1.getKeeperSavesStat().getAwayKeeperSaves());
        CornerStat cornerStat = new CornerStat(m2.getCornerStat().getHomeCorners()-m1.getCornerStat().getHomeCorners(), m2.getCornerStat().getAwayCorners()-m1.getCornerStat().getAwayCorners());
        OffsideStat offsideStat = new OffsideStat(m2.getOffsideStat().getHomeOffsides()-m1.getOffsideStat().getHomeOffsides(), m2.getOffsideStat().getAwayOffsides()-m1.getOffsideStat().getAwayOffsides());
        PossessionStat possessionStat = new PossessionStat(0,0,0,0,0,0);

        int foulsHome = m2.getDirtyPlayStat().getHomeFouls() - m1.getDirtyPlayStat().getHomeFouls();
        int foulsAway = m2.getDirtyPlayStat().getAwayFouls() - m1.getDirtyPlayStat().getAwayFouls();
        int yelHome = m2.getDirtyPlayStat().getHomeYellowCards() - m1.getDirtyPlayStat().getHomeYellowCards();
        int yelAway = m2.getDirtyPlayStat().getAwayYellowCards() - m1.getDirtyPlayStat().getAwayYellowCards();
        int redHome = m2.getDirtyPlayStat().getHomeRedCards() - m1.getDirtyPlayStat().getHomeRedCards();
        int redAway = m2.getDirtyPlayStat().getAwayRedCards() - m1.getDirtyPlayStat().getAwayRedCards();
        DirtyPlayStat dirtyPlayStat = new DirtyPlayStat(foulsHome, foulsAway, yelHome, yelAway, redHome, redAway);
        MatchStatistics diffMs = new MatchStatistics(shotsOnTargetStat, shotsOffTargetStat, blockedShotsStat, totalShotsStat, inboxShotsStat, outboxShotsStat, keeperSavesStat, possessionStat, cornerStat, offsideStat, dirtyPlayStat);
        diffMs.setFixtureId(m1.getFixtureId());
        return new ShortTimedMatchStatistics(diffMs, current.getElapsed(), current.getElapsed() - past.getElapsed());
    }
}
