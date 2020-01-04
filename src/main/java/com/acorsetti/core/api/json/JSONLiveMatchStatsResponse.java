package com.acorsetti.core.api.json;

import com.acorsetti.core.api.json.JsonResponse;
import com.acorsetti.core.live.MatchStatistics;
import com.acorsetti.core.live.stats.*;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JSONLiveMatchStatsResponse extends JsonResponse<MatchStatistics> {

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
     void unpackNested(Map<String,Object> statsMap) {

       if ( ! (statsMap.get("statistics") instanceof Map) ){ //NO DATA
          super.setResults(0);
          super.setDataList(new ArrayList<>());
          return;
       }
        Map<String,Object> stats = (Map<String,Object>) statsMap.get("statistics");

        Map<String,String> onGoal = (Map<String, String>) stats.get("Shots on Goal");
        int homeOnGoal = onGoal.get("home") == null ? 0 : Integer.parseInt(onGoal.get("home"));
        int awayOnGoal = onGoal.get("away") == null ? 0 : Integer.parseInt(onGoal.get("away"));
        ShotsOnTargetStat shotsOnTargetStat = new ShotsOnTargetStat(homeOnGoal, awayOnGoal);

        Map<String,String> offGoal = (Map<String, String>) stats.get("Shots off Goal");
        int homeOffGoal = offGoal.get("home") == null ? 0 : Integer.parseInt(offGoal.get("home"));
        int awayOffGoal = offGoal.get("away") == null ? 0 : Integer.parseInt(offGoal.get("away"));
        ShotsOffTargetStat shotsOffTargetStat = new ShotsOffTargetStat(homeOffGoal, awayOffGoal);

        Map<String,String> totalShots = (Map<String, String>) stats.get("Total Shots");
        int homeTotal = totalShots.get("home") == null ? 0 : Integer.parseInt(totalShots.get("home"));
        int awayTotal = totalShots.get("away") == null ? 0 : Integer.parseInt(totalShots.get("away"));
        TotalShotsStat totalShotsStat = new TotalShotsStat(homeTotal, awayTotal);

        Map<String,String> blockedShots = (Map<String, String>) stats.get("Blocked Shots");
        int homeBlocked = blockedShots.get("home") == null ? 0 : Integer.parseInt(blockedShots.get("home"));
        int awayBlocked = blockedShots.get("away") == null ? 0 : Integer.parseInt(blockedShots.get("away"));
        BlockedShotsStat blockedShotsStat = new BlockedShotsStat(homeBlocked, awayBlocked);

        Map<String,String> inboxShots = (Map<String, String>) stats.get("Shots insidebox");
        int homeInbox = inboxShots.get("home") == null ? 0 : Integer.parseInt(inboxShots.get("home"));
        int awayInbox = inboxShots.get("away") == null ? 0 : Integer.parseInt(inboxShots.get("away"));
        InboxShotsStat inboxShotsStat = new InboxShotsStat(homeInbox, awayInbox);

        Map<String,String> outboxShots = (Map<String, String>) stats.get("Shots outsidebox");
        int homeOutbox = outboxShots.get("home") == null ? 0 : Integer.parseInt(outboxShots.get("home"));
        int awayOutbox = outboxShots.get("away") == null ? 0 : Integer.parseInt(outboxShots.get("away"));
        OutboxShotsStat outboxShotsStat = new OutboxShotsStat(homeOutbox, awayOutbox);

        Map<String,String> corner = (Map<String, String>) stats.get("Corner Kicks");
        int homeCorner = corner.get("home") == null ? 0 : Integer.parseInt(corner.get("home"));
        int awayCorner = corner.get("away") == null ? 0 : Integer.parseInt(corner.get("away"));
        CornerStat cornerStat = new CornerStat(homeCorner, awayCorner);

        Map<String,String> offsides = (Map<String, String>) stats.get("Offsides");
        int homeOffsides = offsides.get("home") == null ? 0 : Integer.parseInt(offsides.get("home"));
        int awayOffsides = offsides.get("away") == null ? 0 : Integer.parseInt(offsides.get("away"));
        OffsideStat offsideStat = new OffsideStat(homeOffsides, awayOffsides);

        Map<String,String> saves = (Map<String, String>) stats.get("Goalkeeper Saves");
        int homeSaves = saves.get("home") == null ? 0 : Integer.parseInt(saves.get("home"));
        int awaySaves = saves.get("away") == null ? 0 : Integer.parseInt(saves.get("away"));
        KeeperSavesStat keeperSavesStat = new KeeperSavesStat(homeSaves, awaySaves);

        Map<String,String> foul = (Map<String, String>) stats.get("Fouls");
        Map<String,String> yellows = (Map<String, String>) stats.get("Yellow Cards");
        Map<String,String> reds = (Map<String, String>) stats.get("Red Cards");
        int homeFoul = foul.get("home") == null ? 0 : Integer.parseInt(foul.get("home"));
        int awayFoul = foul.get("away") == null ? 0 : Integer.parseInt(foul.get("away"));
        int homeYell = yellows.get("home") == null ? 0 : Integer.parseInt(yellows.get("home"));
        int awayYell = yellows.get("away") == null ? 0 : Integer.parseInt(yellows.get("away"));
        int homeRed = reds.get("home") == null ? 0 : Integer.parseInt(reds.get("home"));
        int awayRed = reds.get("away") == null ? 0 : Integer.parseInt(reds.get("away"));
        DirtyPlayStat dirtyPlayStat = new DirtyPlayStat(homeFoul, awayFoul, homeYell, awayYell, homeRed, awayRed);

        Map<String,String> ballPoss = (Map<String, String>) stats.get("Ball Possession");
        Map<String,String> passes = (Map<String, String>) stats.get("Total passes");
        Map<String,String> accurates = (Map<String, String>) stats.get("Passes accurate");
        int homePoss = ballPoss.get("home") == null ? 0 : Integer.parseInt(ballPoss.get("home").replace("%",""));
        int awayPoss = ballPoss.get("away") == null ? 0 : Integer.parseInt(ballPoss.get("away").replace("%",""));
        int homePass = passes.get("home") == null ? 0 : Integer.parseInt(passes.get("home"));
        int awayPass = passes.get("away") == null ? 0 : Integer.parseInt(passes.get("away"));
        int homeAcc = accurates.get("home") == null ? 0 : Integer.parseInt(accurates.get("home"));
        int awayAcc = accurates.get("away") == null ? 0 : Integer.parseInt(accurates.get("away"));
        PossessionStat possessionStat = new PossessionStat(homePoss, awayPoss, homePass, awayPass, homeAcc, awayAcc);

        MatchStatistics matchStatistics = new MatchStatistics(shotsOnTargetStat, shotsOffTargetStat, blockedShotsStat,
                totalShotsStat, inboxShotsStat, outboxShotsStat, keeperSavesStat, possessionStat, cornerStat, offsideStat, dirtyPlayStat);

        List<MatchStatistics> matchStatisticsList = new ArrayList<>();
        matchStatisticsList.add(matchStatistics);
        super.setDataList(matchStatisticsList);
        super.setResults(super.getDataList().size());
    }
}
