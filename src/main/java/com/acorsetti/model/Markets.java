package com.acorsetti.model;

import com.acorsetti.model.enums.MarketValue;

import java.util.Arrays;
import java.util.List;

public class Markets {

    public enum MarketType {
        HDA, BTTS, BTTS_HT, DC, DC_HT, EXACT_SCORE, EXACT_SCORE_HT, FIRST_GOAL, HDA_BTTS, HDA_UNDEROVER,
        HT_FT, HT, UO1_5, UO2_5, UO3_5, UO4_5, EMPTY_MARKET_TYPE
    }

    public static MarketValue getMarketValueByRepresentation(String representation){
        for(MarketValue mv: Markets.validMarketValues() ){
            String mvRepresentation = mv.getRepresentation();
            if ( mvRepresentation == null || mvRepresentation.isEmpty() ) continue;
            if ( mvRepresentation.equalsIgnoreCase(representation) ) return mv;
        }
        return MarketValue.EMPTY_MARKET_VALUE;
    }

    /**
     * It returns market values for which we calculate odds (example: EXACT_SCORE_HT is not returned because
     * currently we dont calculate these chances for a fixture
     * @return
     */
    public static List<MarketValue> validMarketValues(){
        return Arrays.asList(
                MarketValue.HDA_HOME, MarketValue.HDA_DRAW, MarketValue.HDA_AWAY, MarketValue.DC_HOME_DRAW,
                MarketValue.DC_DRAW_AWAY, MarketValue.DC_HOME_AWAY, MarketValue.BTTS_YES, MarketValue.BTTS_NO,
                MarketValue.O1_5, MarketValue.O2_5, MarketValue.O3_5, MarketValue.O4_5,
                MarketValue.U1_5, MarketValue.U2_5, MarketValue.U3_5, MarketValue.U4_5,
                MarketValue.HDA_HOME_O1_5, MarketValue.HDA_HOME_O2_5, MarketValue.HDA_HOME_O3_5, MarketValue.HDA_HOME_O4_5,
                MarketValue.HDA_HOME_U1_5, MarketValue.HDA_HOME_U2_5, MarketValue.HDA_HOME_U3_5, MarketValue.HDA_HOME_U4_5,
                MarketValue.HDA_DRAW_O1_5, MarketValue.HDA_DRAW_O2_5, MarketValue.HDA_DRAW_O3_5, MarketValue.HDA_DRAW_O4_5,
                MarketValue.HDA_DRAW_U1_5, MarketValue.HDA_DRAW_U2_5, MarketValue.HDA_DRAW_U3_5, MarketValue.HDA_DRAW_U4_5,
                MarketValue.HDA_AWAY_O1_5, MarketValue.HDA_AWAY_O2_5, MarketValue.HDA_AWAY_O3_5, MarketValue.HDA_AWAY_O4_5,
                MarketValue.HDA_AWAY_U1_5, MarketValue.HDA_AWAY_U2_5, MarketValue.HDA_AWAY_U3_5, MarketValue.HDA_AWAY_U4_5,
                MarketValue.HDA_BTTS_HOME_YES, MarketValue.HDA_BTTS_DRAW_YES, MarketValue.HDA_BTTS_AWAY_YES, MarketValue.HDA_BTTS_HOME_NO,
                MarketValue.HDA_BTTS_DRAW_NO, MarketValue.HDA_BTTS_AWAY_NO, MarketValue.NIL_NIL, MarketValue.ONE_NIL,
                MarketValue.TWO_NIL, MarketValue.THREE_NIL, MarketValue.FOUR_NIL, MarketValue.FOUR_ONE,
                MarketValue.FOUR_TWO, MarketValue.FOUR_THREE, MarketValue.FOUR_FOUR, MarketValue.THREE_TWO,
                MarketValue.THREE_ONE, MarketValue.THREE_THREE, MarketValue.THREE_FOUR, MarketValue.NIL_ONE,
                MarketValue.NIL_TWO, MarketValue.NIL_THREE, MarketValue.NIL_FOUR, MarketValue.ONE_ONE,
                MarketValue.ONE_TWO, MarketValue.ONE_THREE, MarketValue.ONE_FOUR, MarketValue.TWO_ONE,
                MarketValue.TWO_TWO, MarketValue.TWO_THREE, MarketValue.TWO_FOUR, MarketValue.OTHER
        );
    }

    public static MarketValue byName(String name){
        for(MarketValue mv: MarketValue.values()){
            if (name.equalsIgnoreCase(mv.toString())) return mv;
        }
        return MarketValue.EMPTY_MARKET_VALUE;
    }
}
