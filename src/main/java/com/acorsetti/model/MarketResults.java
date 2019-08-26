package com.acorsetti.model;

import com.acorsetti.service.FixtureService;
import com.acorsetti.utils.FixtureUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class MarketResults {
    private static Logger logger = Logger.getLogger(MarketResults.class);

    private final Fixture fixture;

    @Autowired
    private FixtureService fixtureService;

    public MarketResults(Fixture fixture){
        this.fixture = fixture;
    }

    public enum Result {
        TO_BE_DEFINED, YES, NO;

        public static Result byName(String name){
            switch (name){
                case "TO_BE_DEFINED": return TO_BE_DEFINED;
                case "YES": return YES;
                case "NO": return NO;
                default: return TO_BE_DEFINED;
            }
        }
    }

    public Result result(Markets.MarketValue marketValue){
        if ( !Markets.validMarketValues().contains(marketValue) ) return Result.TO_BE_DEFINED;
        if ( marketValue == Markets.MarketValue.EMPTY_MARKET_VALUE ) return Result.NO;

        switch (marketValue){
            case HDA_HOME: return hda(0);
            case HDA_DRAW: return hda(1);
            case HDA_AWAY: return hda(2);
            case BTTS_NO: return btts(false);
            case BTTS_YES: return btts(true);
            case DC_HOME_DRAW: return dc(0,1);
            case DC_HOME_AWAY: return dc(0,2);
            case DC_DRAW_AWAY: return dc(1,2);
            case O1_5: return over(1);
            case O2_5: return over(2);
            case O3_5: return over(3);
            case O4_5: return over(4);
            case U1_5: return under(1);
            case U2_5: return under(2);
            case U3_5: return under(3);
            case U4_5: return under(4);
            case HDA_HOME_O1_5: return hdaOverUnder(0, 1, true);
            case HDA_HOME_O2_5: return hdaOverUnder(0, 2, true);
            case HDA_HOME_O3_5: return hdaOverUnder(0, 3, true);
            case HDA_HOME_O4_5: return hdaOverUnder(0, 4, true);
            case HDA_HOME_U1_5: return hdaOverUnder(0, 1, false);
            case HDA_HOME_U2_5: return hdaOverUnder(0, 2, false);
            case HDA_HOME_U3_5: return hdaOverUnder(0, 3, false);
            case HDA_HOME_U4_5: return hdaOverUnder(0, 4, false);
            case HDA_DRAW_O1_5: return hdaOverUnder(1, 1, true);
            case HDA_DRAW_O2_5: return hdaOverUnder(1, 2, true);
            case HDA_DRAW_O3_5: return hdaOverUnder(1, 3, true);
            case HDA_DRAW_O4_5: return hdaOverUnder(1, 4, true);
            case HDA_DRAW_U1_5: return hdaOverUnder(1, 1, false);
            case HDA_DRAW_U2_5: return hdaOverUnder(1, 2, false);
            case HDA_DRAW_U3_5: return hdaOverUnder(1, 3, false);
            case HDA_DRAW_U4_5: return hdaOverUnder(1, 4, false);
            case HDA_AWAY_O1_5: return hdaOverUnder(2, 1, true);
            case HDA_AWAY_O2_5: return hdaOverUnder(2, 2, true);
            case HDA_AWAY_O3_5: return hdaOverUnder(2, 3, true);
            case HDA_AWAY_O4_5: return hdaOverUnder(2, 4, true);
            case HDA_AWAY_U1_5: return hdaOverUnder(2, 1, false);
            case HDA_AWAY_U2_5: return hdaOverUnder(2, 2, false);
            case HDA_AWAY_U3_5: return hdaOverUnder(2, 3, false);
            case HDA_AWAY_U4_5: return hdaOverUnder(2, 4, false);
            case HDA_BTTS_HOME_YES: return bttsHda(true, 0);
            case HDA_BTTS_HOME_NO: return bttsHda(false, 0);
            case HDA_BTTS_DRAW_YES: return bttsHda(true, 1);
            case HDA_BTTS_DRAW_NO: return bttsHda(false, 1);
            case HDA_BTTS_AWAY_YES: return bttsHda(true, 2);
            case HDA_BTTS_AWAY_NO: return bttsHda(false, 2);
            default: {
                // else: marketValue has to be exact score
                return exactScore(marketValue.getRepresentation());
            }
        }
    }

    private Result exactScore(String marketRepresentation){
        if ( !this.fixtureService.isCompleted(this.fixture) ) return Result.TO_BE_DEFINED;

        if ( marketRepresentation == null || marketRepresentation.isEmpty() ) return Result.TO_BE_DEFINED;

        int matchGoalsHome = this.fixtureService.getTeamGoalsFor(this.fixture, this.fixture.getHomeTeamId());
        int matchGoalsAway = this.fixtureService.getTeamGoalsFor(this.fixture, this.fixture.getAwayTeamId());

        if ( marketRepresentation.equalsIgnoreCase("Other") ){
            if ( matchGoalsHome > 4 || matchGoalsAway > 4 ) return Result.YES;
            return Result.NO;
        }

        int goalsHome;
        int goalsAway;
        try{
            goalsHome = FixtureUtils.goalsFromScore(marketRepresentation, true);
            goalsAway = FixtureUtils.goalsFromScore(marketRepresentation, false);
        }
        catch (Exception e){
            logger.warn("No goals extracted from match: " + this.fixture + " Market: " + marketRepresentation);
            return Result.TO_BE_DEFINED;
        }

        if ( goalsHome != matchGoalsHome || goalsAway != matchGoalsAway ) return Result.NO;
        return Result.YES;
    }

    private Result bttsHda(boolean bttsYes, int hdaFlag){
        if ( !this.fixtureService.isCompleted(this.fixture) ) return Result.TO_BE_DEFINED;

        if ( btts(bttsYes) != Result.YES ) return Result.NO;
        if ( hda(hdaFlag) != Result.YES ) return Result.NO;

        return Result.YES;
    }

    private Result btts(boolean bttsYes){

        int goalsHome = this.fixtureService.getTeamGoalsFor(this.fixture, this.fixture.getHomeTeamId());
        int goalsAway = this.fixtureService.getTeamGoalsFor(this.fixture, this.fixture.getHomeTeamId());

        if ( bttsYes ){
            if (goalsAway > 0 && goalsHome > 0) return Result.YES;

            if ( this.fixtureService.isCompleted(this.fixture) && (goalsAway == 0 || goalsHome == 0) ) {
                return Result.NO;
            }
        }
        else {
            if (goalsAway > 0 && goalsHome > 0) return Result.NO;

            if (this.fixtureService.isCompleted(this.fixture)  && (goalsAway == 0 || goalsHome == 0)) {
                return Result.YES;
            }
        }
        return Result.TO_BE_DEFINED;

    }

    private Result hda(int flag){
        if ( !this.fixtureService.isCompleted(this.fixture)  ) return Result.TO_BE_DEFINED;

        String winnerId = this.fixtureService.winnerTeamId(this.fixture);
        if ( flag == 0 ){  //HDA_HOME
            if ( winnerId.equals(this.fixture.getHomeTeamId())) return Result.YES;
            return Result.NO;
        }
        else if( flag == 1 ){ //HDA_DRAW
            if ( winnerId.isEmpty() ) return Result.YES;
            return Result.NO;
        }
        else{ //flag == 2 : HDA_AWAY
            if ( winnerId.equals(this.fixture.getAwayTeamId() ) ) return Result.YES;
            return Result.NO;
        }
    }

    private Result dc(int flag1, int flag2){ //0: HOME, 1: DRAW, 2: AWAY

        if (! this.fixtureService.isCompleted(this.fixture) ) return Result.TO_BE_DEFINED;

        String homeId = this.fixture.getHomeTeamId();
        String awayId = this.fixture.getAwayTeamId();

        String winnerId = this.fixtureService.winnerTeamId(this.fixture);
        if ( winnerId.isEmpty() ){
            if (flag1 == 1 || flag2 == 1) return Result.YES;
            return Result.NO;
        }
        else if( winnerId.equals(homeId) ){
            if ( flag1 == 0 || flag2 == 0 ) return Result.YES;
            return Result.NO;
        }
        else if( winnerId.equals(awayId) ){
            if (flag1 == 2 || flag2 == 2) return Result.YES;
            return Result.NO;
        }
        else{
            return Result.TO_BE_DEFINED;
        }
    }

    private Result over(int overLimit){
        String home = this.fixture.getHomeTeamId();
        String away = this.fixture.getAwayTeamId();

        int goalSum = this.fixtureService.goalSum(this.fixture);

        if ( goalSum > overLimit ){
            return Result.YES;
        }
        else if( this.fixtureService.isCompleted(this.fixture)  ){
            return Result.NO;
        }
        return Result.TO_BE_DEFINED;
    }

    private Result under(int underLimit) {
        String home = this.fixture.getHomeTeamId();
        String away = this.fixture.getAwayTeamId();

        int goalSum = this.fixtureService.goalSum(this.fixture);
        if (goalSum > underLimit) {
            return Result.NO;
        } else if ( this.fixtureService.isCompleted(this.fixture) ) {
            return Result.YES;
        }
        return Result.TO_BE_DEFINED;
    }

    private Result hdaOverUnder(int hdaFlag, int limit, boolean over){
        if ( ! this.fixtureService.isCompleted(this.fixture)   ) return Result.TO_BE_DEFINED;

        if ( hda(hdaFlag) != Result.YES ) return Result.NO;
        Result underOverResult;
        if ( over ){
            underOverResult = over(limit);
        }
        else{
            underOverResult = under(limit);
        }
        if ( underOverResult != Result.YES ) return Result.NO;
        return Result.YES;
    }

}
