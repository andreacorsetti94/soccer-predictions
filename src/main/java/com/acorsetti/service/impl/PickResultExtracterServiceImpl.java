package com.acorsetti.service.impl;

import com.acorsetti.model.Fixture;
import com.acorsetti.model.Markets;
import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.enums.PickResult;
import com.acorsetti.service.FixtureService;
import com.acorsetti.service.PickResultExtracterService;
import com.acorsetti.utils.FixtureUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PickResultExtracterServiceImpl implements PickResultExtracterService {

    private static final Logger logger = Logger.getLogger(PickResultExtracterServiceImpl.class);

    @Autowired
    private FixtureService fixtureService;

    public PickResultExtracterServiceImpl(){
    }

    @Override
    public PickResult pickResult(Fixture f, MarketValue marketValue) {
        if ( !Markets.validMarketValues().contains(marketValue) ) return PickResult.TO_BE_DEFINED;
        if ( marketValue == MarketValue.EMPTY_MARKET_VALUE ) return PickResult.NO;

        switch (marketValue){
            case HDA_HOME: return hda(f,0);
            case HDA_DRAW: return hda(f,1);
            case HDA_AWAY: return hda(f,2);
            case BTTS_NO: return btts(f,false);
            case BTTS_YES: return btts(f,true);
            case DC_HOME_DRAW: return dc(f,0,1);
            case DC_HOME_AWAY: return dc(f,0,2);
            case DC_DRAW_AWAY: return dc(f,1,2);
            case O1_5: return over(f,1);
            case O2_5: return over(f,2);
            case O3_5: return over(f,3);
            case O4_5: return over(f,4);
            case U1_5: return under(f,1);
            case U2_5: return under(f,2);
            case U3_5: return under(f,3);
            case U4_5: return under(f,4);
            case HDA_HOME_O1_5: return hdaOverUnder(f,0, 1, true);
            case HDA_HOME_O2_5: return hdaOverUnder(f,0, 2, true);
            case HDA_HOME_O3_5: return hdaOverUnder(f,0, 3, true);
            case HDA_HOME_O4_5: return hdaOverUnder(f,0, 4, true);
            case HDA_HOME_U1_5: return hdaOverUnder(f,0, 1, false);
            case HDA_HOME_U2_5: return hdaOverUnder(f,0, 2, false);
            case HDA_HOME_U3_5: return hdaOverUnder(f,0, 3, false);
            case HDA_HOME_U4_5: return hdaOverUnder(f,0, 4, false);
            case HDA_DRAW_O1_5: return hdaOverUnder(f,1, 1, true);
            case HDA_DRAW_O2_5: return hdaOverUnder(f,1, 2, true);
            case HDA_DRAW_O3_5: return hdaOverUnder(f,1, 3, true);
            case HDA_DRAW_O4_5: return hdaOverUnder(f,1, 4, true);
            case HDA_DRAW_U1_5: return hdaOverUnder(f,1, 1, false);
            case HDA_DRAW_U2_5: return hdaOverUnder(f,1, 2, false);
            case HDA_DRAW_U3_5: return hdaOverUnder(f,1, 3, false);
            case HDA_DRAW_U4_5: return hdaOverUnder(f,1, 4, false);
            case HDA_AWAY_O1_5: return hdaOverUnder(f,2, 1, true);
            case HDA_AWAY_O2_5: return hdaOverUnder(f,2, 2, true);
            case HDA_AWAY_O3_5: return hdaOverUnder(f,2, 3, true);
            case HDA_AWAY_O4_5: return hdaOverUnder(f,2, 4, true);
            case HDA_AWAY_U1_5: return hdaOverUnder(f,2, 1, false);
            case HDA_AWAY_U2_5: return hdaOverUnder(f,2, 2, false);
            case HDA_AWAY_U3_5: return hdaOverUnder(f,2, 3, false);
            case HDA_AWAY_U4_5: return hdaOverUnder(f,2, 4, false);
            case HDA_BTTS_HOME_YES: return bttsHda(f,true, 0);
            case HDA_BTTS_HOME_NO: return bttsHda(f,false, 0);
            case HDA_BTTS_DRAW_YES: return bttsHda(f,true, 1);
            case HDA_BTTS_DRAW_NO: return bttsHda(f,false, 1);
            case HDA_BTTS_AWAY_YES: return bttsHda(f,true, 2);
            case HDA_BTTS_AWAY_NO: return bttsHda(f,false, 2);
            default: {
                // else: marketValue has to be exact score
                return exactScore(f,marketValue.getRepresentation());
            }
        }
    }


    private PickResult exactScore(Fixture fixture, String marketRepresentation){
        if ( !fixtureService.isCompleted(fixture) ) return PickResult.TO_BE_DEFINED;

        if ( marketRepresentation == null || marketRepresentation.isEmpty() ) return PickResult.TO_BE_DEFINED;

        int matchGoalsHome = fixtureService.getTeamGoalsFor(fixture, fixture.getHomeTeamId());
        int matchGoalsAway = fixtureService.getTeamGoalsFor(fixture, fixture.getAwayTeamId());

        if ( marketRepresentation.equalsIgnoreCase("Other") ){
            if ( matchGoalsHome > 4 || matchGoalsAway > 4 ) return PickResult.YES;
            return PickResult.NO;
        }

        int goalsHome;
        int goalsAway;
        try{
            goalsHome = FixtureUtils.goalsFromScore(marketRepresentation, true);
            goalsAway = FixtureUtils.goalsFromScore(marketRepresentation, false);
        }
        catch (Exception e){
            logger.warn("No goals extracted from match: " + fixture + " Market: " + marketRepresentation);
            return PickResult.TO_BE_DEFINED;
        }

        if ( goalsHome != matchGoalsHome || goalsAway != matchGoalsAway ) return PickResult.NO;
        return PickResult.YES;
    }

    private PickResult bttsHda(Fixture fixture, boolean bttsYes, int hdaFlag){
        if ( !fixtureService.isCompleted(fixture) ) return PickResult.TO_BE_DEFINED;

        if ( btts(fixture,bttsYes) != PickResult.YES ) return PickResult.NO;
        if ( hda(fixture,hdaFlag) != PickResult.YES ) return PickResult.NO;

        return PickResult.YES;
    }

    private PickResult btts(Fixture fixture, boolean bttsYes){

        int goalsHome = fixtureService.getTeamGoalsFor(fixture, fixture.getHomeTeamId());
        int goalsAway = fixtureService.getTeamGoalsFor(fixture, fixture.getHomeTeamId());

        if ( bttsYes ){
            if (goalsAway > 0 && goalsHome > 0) return PickResult.YES;

            if ( fixtureService.isCompleted(fixture) && (goalsAway == 0 || goalsHome == 0) ) {
                return PickResult.NO;
            }
        }
        else {
            if (goalsAway > 0 && goalsHome > 0) return PickResult.NO;

            if (fixtureService.isCompleted(fixture)  && (goalsAway == 0 || goalsHome == 0)) {
                return PickResult.YES;
            }
        }
        return PickResult.TO_BE_DEFINED;

    }

    private PickResult hda(Fixture fixture, int flag){
        if ( !fixtureService.isCompleted(fixture)  ) return PickResult.TO_BE_DEFINED;

        String winnerId = fixtureService.winnerTeamId(fixture);
        if ( flag == 0 ){  //HDA_HOME
            if ( winnerId.equals(fixture.getHomeTeamId())) return PickResult.YES;
            return PickResult.NO;
        }
        else if( flag == 1 ){ //HDA_DRAW
            if ( winnerId.isEmpty() ) return PickResult.YES;
            return PickResult.NO;
        }
        else{ //flag == 2 : HDA_AWAY
            if ( winnerId.equals(fixture.getAwayTeamId() ) ) return PickResult.YES;
            return PickResult.NO;
        }
    }

    private PickResult dc(Fixture fixture, int flag1, int flag2){ //0: HOME, 1: DRAW, 2: AWAY

        if (! fixtureService.isCompleted(fixture) ) return PickResult.TO_BE_DEFINED;

        String homeId = fixture.getHomeTeamId();
        String awayId = fixture.getAwayTeamId();

        String winnerId = fixtureService.winnerTeamId(fixture);
        if ( winnerId.isEmpty() ){
            if (flag1 == 1 || flag2 == 1) return PickResult.YES;
            return PickResult.NO;
        }
        else if( winnerId.equals(homeId) ){
            if ( flag1 == 0 || flag2 == 0 ) return PickResult.YES;
            return PickResult.NO;
        }
        else if( winnerId.equals(awayId) ){
            if (flag1 == 2 || flag2 == 2) return PickResult.YES;
            return PickResult.NO;
        }
        else{
            return PickResult.TO_BE_DEFINED;
        }
    }

    private PickResult over(Fixture fixture, int overLimit){

        int goalSum = fixtureService.goalSum(fixture);

        if ( goalSum > overLimit ){
            return PickResult.YES;
        }
        else if( fixtureService.isCompleted(fixture)  ){
            return PickResult.NO;
        }
        return PickResult.TO_BE_DEFINED;
    }

    private PickResult under(Fixture fixture, int underLimit) {

        int goalSum = fixtureService.goalSum(fixture);
        if (goalSum > underLimit) {
            return PickResult.NO;
        } else if ( fixtureService.isCompleted(fixture) ) {
            return PickResult.YES;
        }
        return PickResult.TO_BE_DEFINED;
    }

    private PickResult hdaOverUnder(Fixture fixture, int hdaFlag, int limit, boolean over){
        if ( ! fixtureService.isCompleted(fixture)   ) return PickResult.TO_BE_DEFINED;

        if ( hda(fixture,hdaFlag) != PickResult.YES ) return PickResult.NO;
        PickResult underOverPickResult;
        if ( over ){
            underOverPickResult = over(fixture,limit);
        }
        else{
            underOverPickResult = under(fixture,limit);
        }
        if ( underOverPickResult != PickResult.YES ) return PickResult.NO;
        return PickResult.YES;
    }


}
