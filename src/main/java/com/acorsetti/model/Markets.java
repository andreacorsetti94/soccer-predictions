package com.acorsetti.model;

import java.util.Arrays;
import java.util.List;

public class Markets {

    ;
    public enum MarketValue {

        HDA_HOME(MarketType.HDA), HDA_DRAW(MarketType.HDA), HDA_AWAY(MarketType.HDA),
        BTTS_YES(MarketType.BTTS), BTTS_NO(MarketType.BTTS),
        BTTS_HT_YES(MarketType.BTTS), BTTS_HT_NO(MarketType.BTTS_HT),
        DC_HOME_DRAW(MarketType.DC), DC_DRAW_AWAY(MarketType.DC), DC_HOME_AWAY(MarketType.DC),
        DC_HT_HOME_DRAW(MarketType.DC_HT), DC_HT_DRAW_AWAY(MarketType.DC_HT), DC_HT_HOME_AWAY(MarketType.DC_HT),

        NIL_NIL(MarketType.EXACT_SCORE,"0 - 0"), ONE_NIL(MarketType.EXACT_SCORE, "1 - 0"), TWO_NIL(MarketType.EXACT_SCORE, "2 - 0"),
        THREE_NIL(MarketType.EXACT_SCORE, "3 - 0"), FOUR_NIL(MarketType.EXACT_SCORE, "4 - 0"), FOUR_ONE(MarketType.EXACT_SCORE, "4 - 1"),
        FOUR_TWO(MarketType.EXACT_SCORE, "4 - 2"), FOUR_THREE(MarketType.EXACT_SCORE, "4 - 3"), FOUR_FOUR(MarketType.EXACT_SCORE, "4 - 4"),
        THREE_TWO(MarketType.EXACT_SCORE, "3 - 2"), THREE_ONE(MarketType.EXACT_SCORE, "3 - 1"), THREE_THREE(MarketType.EXACT_SCORE, "3 - 3"),
        THREE_FOUR(MarketType.EXACT_SCORE, "3 - 4"), NIL_ONE(MarketType.EXACT_SCORE, "0 - 1"), NIL_TWO(MarketType.EXACT_SCORE, "0 - 2"),
        NIL_THREE(MarketType.EXACT_SCORE, "0 - 3"), NIL_FOUR(MarketType.EXACT_SCORE, "0 - 4"), ONE_ONE(MarketType.EXACT_SCORE, "1 - 1"),
        ONE_TWO(MarketType.EXACT_SCORE, "1 - 2"), ONE_THREE(MarketType.EXACT_SCORE, "1 - 3"), ONE_FOUR(MarketType.EXACT_SCORE, "1 - 4"),
        TWO_ONE(MarketType.EXACT_SCORE, "2 - 1"), TWO_TWO(MarketType.EXACT_SCORE, "2 - 2"), TWO_THREE(MarketType.EXACT_SCORE, "2 - 3"),
        TWO_FOUR(MarketType.EXACT_SCORE, "2 - 4"), OTHER(MarketType.EXACT_SCORE, "Other"),

        NIL_NIL_HT(MarketType.EXACT_SCORE_HT), ONE_NIL_HT(MarketType.EXACT_SCORE_HT), TWO_NIL_HT(MarketType.EXACT_SCORE_HT),
        THREE_NIL_HT(MarketType.EXACT_SCORE_HT), FOUR_NIL_HT(MarketType.EXACT_SCORE_HT), FOUR_ONE_HT(MarketType.EXACT_SCORE_HT),
        FOUR_TWO_HT(MarketType.EXACT_SCORE_HT), FOUR_THREE_HT(MarketType.EXACT_SCORE_HT), FOUR_FOUR_HT(MarketType.EXACT_SCORE_HT),
        THREE_TWO_HT(MarketType.EXACT_SCORE_HT), THREE_ONE_HT(MarketType.EXACT_SCORE_HT), THREE_THREE_HT(MarketType.EXACT_SCORE_HT),
        THREE_FOUR_HT(MarketType.EXACT_SCORE_HT), NIL_ONE_HT(MarketType.EXACT_SCORE_HT), NIL_TWO_HT(MarketType.EXACT_SCORE_HT),
        NIL_THREE_HT(MarketType.EXACT_SCORE_HT), NIL_FOUR_HT(MarketType.EXACT_SCORE_HT), ONE_ONE_HT(MarketType.EXACT_SCORE_HT),
        ONE_TWO_HT(MarketType.EXACT_SCORE_HT), ONE_THREE_HT(MarketType.EXACT_SCORE_HT), ONE_FOUR_HT(MarketType.EXACT_SCORE_HT),
        TWO_ONE_HT(MarketType.EXACT_SCORE_HT), TWO_TWO_HT(MarketType.EXACT_SCORE_HT), TWO_THREE_HT(MarketType.EXACT_SCORE_HT),
        TWO_FOUR_HT(MarketType.EXACT_SCORE_HT), OTHER_HT(MarketType.EXACT_SCORE_HT),

        FG_HOME(MarketType.FIRST_GOAL), FG_AWAY(MarketType.FIRST_GOAL), FG_NO_GOAL(MarketType.FIRST_GOAL),
        HDA_BTTS_HOME_YES(MarketType.HDA_BTTS), HDA_BTTS_HOME_NO(MarketType.HDA_BTTS), HDA_BTTS_DRAW_YES(MarketType.HDA_BTTS),
        HDA_BTTS_DRAW_NO(MarketType.HDA_BTTS), HDA_BTTS_AWAY_YES(MarketType.HDA_BTTS), HDA_BTTS_AWAY_NO(MarketType.HDA_BTTS),

        HDA_HOME_O1_5(MarketType.HDA_UNDEROVER), HDA_HOME_O2_5(MarketType.HDA_UNDEROVER), HDA_HOME_O3_5(MarketType.HDA_UNDEROVER), HDA_HOME_O4_5(MarketType.HDA_UNDEROVER),
        HDA_HOME_U1_5(MarketType.HDA_UNDEROVER), HDA_HOME_U2_5(MarketType.HDA_UNDEROVER), HDA_HOME_U3_5(MarketType.HDA_UNDEROVER), HDA_HOME_U4_5(MarketType.HDA_UNDEROVER),
        HDA_DRAW_O1_5(MarketType.HDA_UNDEROVER), HDA_DRAW_O2_5(MarketType.HDA_UNDEROVER), HDA_DRAW_O3_5(MarketType.HDA_UNDEROVER), HDA_DRAW_O4_5(MarketType.HDA_UNDEROVER),
        HDA_DRAW_U1_5(MarketType.HDA_UNDEROVER), HDA_DRAW_U2_5(MarketType.HDA_UNDEROVER), HDA_DRAW_U3_5(MarketType.HDA_UNDEROVER), HDA_DRAW_U4_5(MarketType.HDA_UNDEROVER),
        HDA_AWAY_O1_5(MarketType.HDA_UNDEROVER), HDA_AWAY_O2_5(MarketType.HDA_UNDEROVER), HDA_AWAY_O3_5(MarketType.HDA_UNDEROVER), HDA_AWAY_O4_5(MarketType.HDA_UNDEROVER),
        HDA_AWAY_U1_5(MarketType.HDA_UNDEROVER), HDA_AWAY_U2_5(MarketType.HDA_UNDEROVER), HDA_AWAY_U3_5(MarketType.HDA_UNDEROVER), HDA_AWAY_U4_5(MarketType.HDA_UNDEROVER),

        HT_FT_HOME_DRAW(MarketType.HT_FT), HT_FT_HOME_HOME(MarketType.HT_FT), HT_FT_HOME_AWAY(MarketType.HT_FT),
        HT_FT_DRAW_DRAW(MarketType.HT_FT), HT_FT_DRAW_HOME(MarketType.HT_FT), HT_FT_DRAW_AWAY(MarketType.HT_FT),
        HT_FT_AWAY_DRAW(MarketType.HT_FT), HT_FT_AWAY_HOME(MarketType.HT_FT), HT_FT_AWAY_AWAY(MarketType.HT_FT),

        HT_HOME(MarketType.HT), HT_DRAW(MarketType.HT), HT_AWAY(MarketType.HT),
        U1_5(MarketType.UO1_5), O1_5(MarketType.UO1_5),
        U2_5(MarketType.UO2_5), O2_5(MarketType.UO2_5),
        U3_5(MarketType.UO3_5), O3_5(MarketType.UO3_5),
        U4_5(MarketType.UO4_5), O4_5(MarketType.UO4_5),

        EMPTY_MARKET_VALUE(MarketType.EMPTY_MARKET_TYPE);


        private final MarketType type;
        private String representation;
        MarketValue(MarketType type){
            this.type = type;
        }

        MarketValue(MarketType type, String representation){
            this.type = type;
            this.representation = representation;
        }

        public MarketType getType() {
            return type;
        }

        public String getRepresentation(){
            return this.representation;
        }

        @Override
        public String toString(){
            if ( this.representation == null ) return this.toString();
            return this.representation;
        }
    }

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
