package com.acorsetti.core.live;

import com.acorsetti.core.api.APIFixtureRetriever;
import com.acorsetti.core.api.APILiveMatchStatRetriever;
import com.acorsetti.core.api.APIResponse;
import com.acorsetti.core.model.jpa.Fixture;
import com.acorsetti.core.model.jpa.League;
import com.acorsetti.core.service.LeagueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LiveStatsUpdaterService {

    @Autowired
    private APILiveMatchStatRetriever apiLiveMatchStatRetriever;

    @Autowired
    private APIFixtureRetriever apiFixtureRetriever;

    @Autowired
    private LeagueService leagueService;

    private List<TimedMatchStatistics> timedMatchStatisticsList = new ArrayList<>();

    private Map<Fixture,List<TimedMatchStatistics>> globalStatsMap = new HashMap<>();

    public void updateLiveStats(){

        new Thread( () -> {
            while(true){
                System.out.println("Refreshed at: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss"))+"\n");
                List<Fixture> liveMatches = this.apiFixtureRetriever.live().getBody();

                liveMatches.forEach( match -> {
                    APIResponse<MatchStatistics> apiResponse = this.apiLiveMatchStatRetriever.statsByFixtureId(match.getFixtureId());
                    if ( apiResponse.getResults() >  0){
                        MatchStatistics matchStatistics = apiResponse.getBody().get(0);
                        TimedMatchStatistics timedMatchStatistics = new TimedMatchStatistics(matchStatistics, Integer.parseInt(match.getElapsed()));

                        if(globalStatsMap.containsKey(match)){
                            List<TimedMatchStatistics> matchStatList = globalStatsMap.get(match);
                            matchStatList.add(timedMatchStatistics);
                        }
                        else{
                            List<TimedMatchStatistics> timedMatchStatisticsList = new ArrayList<>();
                            timedMatchStatisticsList.add(timedMatchStatistics);
                            globalStatsMap.put(match, timedMatchStatisticsList);
                        }
                        //this.printDiffs(match);
                    }
                });
                sleep(300000);
            }

        }).start();

        new Thread( () -> {
            while(true){
                globalStatsMap.forEach( (match, matchStatsList) -> {
                    if ( matchStatsList == null || matchStatsList.size() < 2 ) return;
                    TimedMatchStatistics last = matchStatsList.get(matchStatsList.size() - 1);
                    int lastElapsed = last.getElapsed();

                    TimedMatchStatistics toDiff = null;
                    for(int i = matchStatsList.size() - 2; i >= 0; i--){
                        TimedMatchStatistics current = matchStatsList.get(i);
                        int currentElapsed = current.getElapsed();
                        if ( toDiff == null || lastElapsed - currentElapsed >= 5 ){
                            toDiff = current;
                        }
                    }
                    ShortTimedMatchStatistics shortTimedMatchStatistics = new TimedMatchStatisticsDiffCalculator().diff(toDiff, last);
                    if ( shortTimedMatchStatistics != null ){
                        PressureIndexCalculator pressureIndexCalculator = new PressureIndexCalculator();
                        MatchAnalysis pic = pressureIndexCalculator.computePressureIndex(shortTimedMatchStatistics.getMatchStatistics());

                        League l = this.leagueService.byId(match.getLeagueId());
                        System.out.println("Match Id: " + match.getFixtureId()+ " : " + l.getCountryName() + " - " + l.getLeagueName() + " - " + match.getEventDate().toString().replace("T"," ") + " - "+
                                match.getHomeTeamName() + " vs " + match.getAwayTeamName());
                        System.out.println(shortTimedMatchStatistics);
                        System.out.println("Analysis: " + pic.toString());
                        System.out.println("--------------------------------");
                    }
                });
                sleep(30000);
            }

        }).start();
    }

    private void printDiffs(Fixture match){
        List<TimedMatchStatistics> matchStatisticsList = globalStatsMap.get(match.getFixtureId());
        if ( matchStatisticsList == null || matchStatisticsList.size() < 2 ) return;
        TimedMatchStatistics secondLast = matchStatisticsList.get(matchStatisticsList.size()-2);
        TimedMatchStatistics last = matchStatisticsList.get(matchStatisticsList.size()-1);

        TimedMatchStatistics diff = new TimedMatchStatisticsDiffCalculator().diff(secondLast, last);
        if ( diff == null ) return;
        PressureIndexCalculator pressureIndexCalculator = new PressureIndexCalculator();
        MatchAnalysis pic = pressureIndexCalculator.computePressureIndex(diff.getMatchStatistics());

        League l = this.leagueService.byId(match.getLeagueId());
        System.out.println("Match Id: " + match.getFixtureId()+ " : " + l.getCountryName() + " - " + l.getLeagueName() + " - " + match.getEventDate().toString().replace("T"," ") + " - "+
                match.getHomeTeamName() + " vs " + match.getAwayTeamName());
        System.out.println(diff);
        System.out.println("Analysis: " + pic.toString());
        System.out.println("--------------------------------");
    }

    private void sleep(int ms){
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
    }
}
