package com.acorsetti.service.impl;

import com.acorsetti.model.Fixture;
import com.acorsetti.repository.FixtureRepository;
import com.acorsetti.service.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FixtureServiceImpl implements FixtureService {

    @Autowired
    private FixtureRepository fixtureRepository;

    public List<Fixture> fixturesByDay(String dayLike){
        return this.fixtureRepository.findByEventDateOrderByDateAsc(dayLike);
    }


    public boolean isCompleted(Fixture fixture){
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
        if ( this.winnerTeamId(fixture).isEmpty() ) return 1;
        return 0;
    }

}
