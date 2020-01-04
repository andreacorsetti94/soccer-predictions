package com.acorsetti.core.live;

import com.acorsetti.core.live.stats.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PressureIndexCalculator {

    /**
     * @return a double between 100 and -100, where 100 means home team very high pressure and -100 means
     * a very high pressure for the away team
     */
    public MatchAnalysis computePressureIndex(MatchStatistics matchStatistics){
        ShotsOnTargetStat shotsOnTargetStat = matchStatistics.getShotsOnTargetStat();
        ShotsOffTargetStat shotsOffTargetStat = matchStatistics.getShotsOffTargetStat();
        BlockedShotsStat blockedShotsStat = matchStatistics.getBlockedShotsStat();
        TotalShotsStat totalShotsStat = matchStatistics.getTotalShotsStat();
        InboxShotsStat inboxShotsStat = matchStatistics.getInboxShotsStat();
        OutboxShotsStat outboxShotsStat = matchStatistics.getOutboxShotsStat();
        KeeperSavesStat keeperSavesStat = matchStatistics.getKeeperSavesStat();
        PossessionStat possessionStat = matchStatistics.getPossessionStat();
        CornerStat cornerStat = matchStatistics.getCornerStat();
        OffsideStat offsideStat = matchStatistics.getOffsideStat();
        DirtyPlayStat dirtyPlayStat = matchStatistics.getDirtyPlayStat();

        double shotsOnTargetVal = 0.0;
        double shotsOnTargetAct = 0.0;
        if ( shotsOnTargetStat.isValid() ){
            double home = shotsOnTargetStat.getHomeShotsOnTarget();
            double away = shotsOnTargetStat.getAwayShotsOnTarget();
            double weight = shotsOnTargetStat.getPressureIndexWeight();
            if ( home + away > 0 ){
                shotsOnTargetVal = ((home - away)/(home + away)) * weight;
                shotsOnTargetAct = (home + away) * weight;
            }
        }

        double shotsOffTargetVal = 0.0;
        double shotsOffTargetAct = 0.0;
        if ( shotsOffTargetStat.isValid() ){
            double home = shotsOffTargetStat.getHomeShotsOffTarget();
            double away = shotsOffTargetStat.getAwayShotsOffTarget();
            double weight = shotsOffTargetStat.getPressureIndexWeight();
            if ( home + away > 0 ){
                shotsOffTargetVal = ((home - away)/(home + away)) * weight;
                shotsOffTargetAct = (home + away) * weight;
            }
        }

        double blockedShotsVal = 0.0;
        double blockedShotsAct = 0.0;
        if ( blockedShotsStat.isValid() ){
            double home = blockedShotsStat.getHomeBlockedShots();
            double away = blockedShotsStat.getAwayBlockedShots();
            double weight = blockedShotsStat.getPressureIndexWeight();
            if ( home + away > 0 ){
                blockedShotsVal = ((home - away)/(home + away)) * weight;
                blockedShotsAct = (home + away) * weight;
            }
        }

        double totalShotsVal = 0.0;
        double totalShotsAct = 0.0;
        if ( totalShotsStat.isValid() ){
            double home = totalShotsStat.getHomeTotalShots();
            double away = totalShotsStat.getAwayTotalShots();
            double weight = totalShotsStat.getPressureIndexWeight();
            if ( home + away > 0 ){
                totalShotsVal = ((home - away)/(home + away)) * weight;
                totalShotsAct = (home + away) * weight;
            }
        }

        double inBoxShotsVal = 0.0;
        double inboxShotsAct = 0.0;
        if ( inboxShotsStat.isValid() ){
            double home = inboxShotsStat.getHomeInboxShots();
            double away = inboxShotsStat.getAwayInboxShots();
            double weight = inboxShotsStat.getPressureIndexWeight();
            if ( home + away > 0 ){
                inBoxShotsVal = ((home - away)/(home + away)) * weight;
                inboxShotsAct = (home + away) * weight;
            }
        }

        double outboxShotsVal = 0.0;
        double outboxShotsAct = 0.0;
        if ( outboxShotsStat.isValid() ){
            double home = outboxShotsStat.getHomeOutboxShots();
            double away = outboxShotsStat.getAwayOutboxShots();
            double weight = outboxShotsStat.getPressureIndexWeight();
            if ( home + away > 0 ){
                outboxShotsVal = ((home - away)/(home + away)) * weight;
                outboxShotsAct = (home + away) * weight;
            }
        }

        double possessionStatVal = 0.0;
        double possessioneStatAct = 0.0;
        if ( possessionStat.isValid() ){
            double home = possessionStat.getHomePossession();
            double away = possessionStat.getAwayPossession();
            double weight = possessionStat.getPressureIndexWeight();
            if ( home + away > 0 ){
                possessionStatVal = ((home - away)/100) * weight;
                possessioneStatAct = (home + away) * weight;
            }
        }

        double cornerVal = 0.0;
        double cornerAct = 0.0;
        if ( cornerStat.isValid() ){
            double home = cornerStat.getHomeCorners();
            double away = cornerStat.getAwayCorners();
            double weight = cornerStat.getPressureIndexWeight();
            if ( home + away > 0 ){
                cornerVal = ((home - away)/(home + away)) * weight;
                cornerAct = (home + away) * weight;
            }
        }

        double offsidesVal = 0.0;
        double offSidesAct = 0.0;
        if ( offsideStat.isValid() ){
            double home = offsideStat.getHomeOffsides();
            double away = offsideStat.getAwayOffsides();
            double weight = offsideStat.getPressureIndexWeight();
            if ( home + away > 0 ){
                offsidesVal = ((home - away)/(home + away)) * weight;
                offSidesAct = (home + away) * weight;
            }
        }

        double keeperSavesVal = 0.0;
        double keeperSavesAct = 0.0;
        if ( keeperSavesStat.isValid() ){
            double home = keeperSavesStat.getHomeKeeperSaves();
            double away = keeperSavesStat.getAwayKeeperSaves();
            double weight = keeperSavesStat.getPressureIndexWeight();
            if ( home + away > 0 ){
                keeperSavesVal = ((home - away)/(home + away)) * weight * -1;
                keeperSavesAct = (home + away) * weight;
            }
        }

        double dirtyPlayVal = 0.0;
        double dirtyPlayAct = 0.0;
        double homeRedCards = dirtyPlayStat.getHomeRedCards();
        double awayRedCards = dirtyPlayStat.getAwayRedCards();
        double homeYelCards = dirtyPlayStat.getHomeYellowCards();
        double awayYelCards = dirtyPlayStat.getAwayYellowCards();
        double redWeigth = dirtyPlayStat.getRedCardPressureIndexWeight();
        double yelWeigth = dirtyPlayStat.getYellowCardPressureIndexWeight();
        if ( homeRedCards + awayRedCards > 0 ){
            dirtyPlayVal -= ((homeRedCards - awayRedCards)/(homeRedCards + awayRedCards)) * redWeigth;
            dirtyPlayAct += (homeRedCards + awayRedCards) * redWeigth;
        }
        if ( homeYelCards + awayYelCards > 0 ){
            dirtyPlayVal -= ((homeYelCards - awayYelCards)/(homeYelCards + awayYelCards)) * yelWeigth;
            dirtyPlayAct += (homeYelCards + awayYelCards) * yelWeigth;
        }

        double sumIndexPressure = blockedShotsVal + cornerVal + dirtyPlayVal + inBoxShotsVal + keeperSavesVal + offsidesVal+
                outboxShotsVal + possessionStatVal + shotsOffTargetVal + shotsOnTargetVal + totalShotsVal;

        double sumActivityIndex = blockedShotsAct + cornerAct + dirtyPlayAct + inboxShotsAct + keeperSavesAct
                + offSidesAct + outboxShotsAct + possessioneStatAct + shotsOffTargetAct + shotsOnTargetAct + totalShotsAct;

        double pressureIndex = 0.0;
        if ( sumIndexPressure > 0 ){
            pressureIndex = Math.min(100.0, new BigDecimal(sumIndexPressure).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }
        if ( sumIndexPressure < 0 ){
            pressureIndex = Math.max(-100.0, new BigDecimal(sumIndexPressure).setScale(2, RoundingMode.HALF_UP).doubleValue());
        }

        return new MatchAnalysis(pressureIndex, new BigDecimal(sumActivityIndex).setScale(2, RoundingMode.HALF_UP).doubleValue());

    }
}
