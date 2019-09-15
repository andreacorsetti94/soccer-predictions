package com.acorsetti.service.impl;

import com.acorsetti.api.APIFixtureRetriever;
import com.acorsetti.model.jpa.Fixture;
import com.acorsetti.repository.FixtureRepository;
import com.acorsetti.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FixtureServiceImpl implements FixtureService {

    @Autowired
    private FixtureRepository fixtureRepository;

    @Autowired
    private APIFixtureRetriever apiFixtureRetriever;

    public List<Fixture> fixturesByDay(LocalDate dayLike){
        return this.fixtureRepository.findByEventDate(dayLike);
    }

    public Fixture byId(String id){
        return this.fixtureRepository.findByFixtureId(id);
    }

    public List<Fixture> lastTeamMatches(String teamId, int numOfMatches, String... leaguesId){
        List<Fixture> teamMatches = this.fixtureRepository.findByTeamIdOrderByEventDateDesc(teamId);

        return teamMatches
                .stream()
                .filter(match ->{
                    if ( leaguesId == null || leaguesId.length == 0 ) return true;
                    return Arrays.asList(leaguesId).contains(match.getLeagueId());
                    }
                )
                .filter(match -> match.getEventDate().isBefore(LocalDateTime.now() ) )
                .limit(numOfMatches)
                .collect(Collectors.toList());
    }

    public boolean isCompleted(Fixture fixture){
        if ( fixture == null ) return false;
        return fixture.getStatusShort().equals("FT") || fixture.getStatus().equals("Match Finished");
    }

    public String winnerTeamId(Fixture fixture){
        try{
            int goalsHome = Integer.parseInt( fixture.getGoalsHomeTeam() );
            int goalsAway = Integer.parseInt( fixture.getGoalsAwayTeam() );

            if ( goalsHome == goalsAway ) return "";

            if ( goalsHome > goalsAway ) return fixture.getHomeTeamId();
            return fixture.getAwayTeamId();
        }
        catch (NumberFormatException e){
            return "";
        }

    }

    public String loserTeamId(Fixture fixture){
        try{
            int goalsHome = Integer.parseInt( fixture.getGoalsHomeTeam() );
            int goalsAway = Integer.parseInt( fixture.getGoalsAwayTeam() );

            if ( goalsHome == goalsAway ) return "";

            if ( goalsHome > goalsAway ) return fixture.getAwayTeamId();
            return fixture.getHomeTeamId();
        }
        catch ( NumberFormatException e){
            return "";
        }

    }

    public int getTeamGoalsFor(Fixture fixture, String teamId){
        try{
            if ( teamId.equals(fixture.getHomeTeamId()) ) return Integer.parseInt( fixture.getGoalsHomeTeam() );
            if ( teamId.equals(fixture.getAwayTeamId()) ) return Integer.parseInt( fixture.getGoalsAwayTeam() );
        }
        catch (NumberFormatException e){
            return 0;
        }

        return 0;
    }

    public int getTeamGoalsConceived(Fixture fixture, String teamId){
        try{
            if ( teamId.equals(fixture.getHomeTeamId()) ) return Integer.parseInt( fixture.getGoalsAwayTeam() );
            if ( teamId.equals(fixture.getAwayTeamId()) ) return Integer.parseInt( fixture.getGoalsHomeTeam() );
        }
        catch (NumberFormatException e){
            return 0;
        }

        return 0;
    }

    public int pointsForTeam(Fixture fixture, String teamId){
        if ( this.winnerTeamId(fixture).equals(teamId) ) return 3;

        if ( this.winnerTeamId(fixture).isEmpty() ){
            if ( this.isCompleted(fixture) ) return 1;
            return 0;
        }
        return 0;
    }

    public int goalSum(Fixture fixture){
        return this.getTeamGoalsFor(fixture, fixture.getHomeTeamId()) + this.getTeamGoalsFor(fixture, fixture.getAwayTeamId());
    }

    @Override
    public List<Fixture> fixturesInPeriodByAPI(LocalDate lowerBoundDate, LocalDate upperBoundDate) {
        List<Fixture> fixtures = new ArrayList<>();
        LocalDate tmp = lowerBoundDate;
        while( tmp.isBefore(upperBoundDate) ){
            List<Fixture> tmpDateFixtures = this.apiFixtureRetriever.byDay(tmp).getBody();
            fixtures.addAll(tmpDateFixtures);
            tmp = tmp.plusDays(1);
        }
        return fixtures;
    }

    @Override
    public List<Fixture> fixturesInPeriodByDB(LocalDate lowerBoundDate, LocalDate upperBoundDate) {
        List<Fixture> fixtures = new ArrayList<>();
        LocalDate tmp = lowerBoundDate;
        while( tmp.isBefore(upperBoundDate) ){
            List<Fixture> tmpDateFixtures = this.fixturesByDay(tmp);
            fixtures.addAll(tmpDateFixtures);
            tmp = tmp.plusDays(1);
        }
        return fixtures;
    }

}
