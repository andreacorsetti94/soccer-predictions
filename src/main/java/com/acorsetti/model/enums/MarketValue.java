package com.acorsetti.model.enums;

import com.acorsetti.model.Markets;

public enum MarketValue {

        HDA_HOME(Markets.MarketType.HDA), HDA_DRAW(Markets.MarketType.HDA), HDA_AWAY(Markets.MarketType.HDA),
        BTTS_YES(Markets.MarketType.BTTS), BTTS_NO(Markets.MarketType.BTTS),
        BTTS_HT_YES(Markets.MarketType.BTTS), BTTS_HT_NO(Markets.MarketType.BTTS_HT),
        DC_HOME_DRAW(Markets.MarketType.DC), DC_DRAW_AWAY(Markets.MarketType.DC), DC_HOME_AWAY(Markets.MarketType.DC),
        DC_HT_HOME_DRAW(Markets.MarketType.DC_HT), DC_HT_DRAW_AWAY(Markets.MarketType.DC_HT), DC_HT_HOME_AWAY(Markets.MarketType.DC_HT),

        NIL_NIL(Markets.MarketType.EXACT_SCORE,"0 - 0"), ONE_NIL(Markets.MarketType.EXACT_SCORE, "1 - 0"), TWO_NIL(Markets.MarketType.EXACT_SCORE, "2 - 0"),
        THREE_NIL(Markets.MarketType.EXACT_SCORE, "3 - 0"), FOUR_NIL(Markets.MarketType.EXACT_SCORE, "4 - 0"), FOUR_ONE(Markets.MarketType.EXACT_SCORE, "4 - 1"),
        FOUR_TWO(Markets.MarketType.EXACT_SCORE, "4 - 2"), FOUR_THREE(Markets.MarketType.EXACT_SCORE, "4 - 3"), FOUR_FOUR(Markets.MarketType.EXACT_SCORE, "4 - 4"),
        THREE_TWO(Markets.MarketType.EXACT_SCORE, "3 - 2"), THREE_ONE(Markets.MarketType.EXACT_SCORE, "3 - 1"), THREE_THREE(Markets.MarketType.EXACT_SCORE, "3 - 3"),
        THREE_FOUR(Markets.MarketType.EXACT_SCORE, "3 - 4"), NIL_ONE(Markets.MarketType.EXACT_SCORE, "0 - 1"), NIL_TWO(Markets.MarketType.EXACT_SCORE, "0 - 2"),
        NIL_THREE(Markets.MarketType.EXACT_SCORE, "0 - 3"), NIL_FOUR(Markets.MarketType.EXACT_SCORE, "0 - 4"), ONE_ONE(Markets.MarketType.EXACT_SCORE, "1 - 1"),
        ONE_TWO(Markets.MarketType.EXACT_SCORE, "1 - 2"), ONE_THREE(Markets.MarketType.EXACT_SCORE, "1 - 3"), ONE_FOUR(Markets.MarketType.EXACT_SCORE, "1 - 4"),
        TWO_ONE(Markets.MarketType.EXACT_SCORE, "2 - 1"), TWO_TWO(Markets.MarketType.EXACT_SCORE, "2 - 2"), TWO_THREE(Markets.MarketType.EXACT_SCORE, "2 - 3"),
        TWO_FOUR(Markets.MarketType.EXACT_SCORE, "2 - 4"), OTHER(Markets.MarketType.EXACT_SCORE, "Other"),

        NIL_NIL_HT(Markets.MarketType.EXACT_SCORE_HT), ONE_NIL_HT(Markets.MarketType.EXACT_SCORE_HT), TWO_NIL_HT(Markets.MarketType.EXACT_SCORE_HT),
        THREE_NIL_HT(Markets.MarketType.EXACT_SCORE_HT), FOUR_NIL_HT(Markets.MarketType.EXACT_SCORE_HT), FOUR_ONE_HT(Markets.MarketType.EXACT_SCORE_HT),
        FOUR_TWO_HT(Markets.MarketType.EXACT_SCORE_HT), FOUR_THREE_HT(Markets.MarketType.EXACT_SCORE_HT), FOUR_FOUR_HT(Markets.MarketType.EXACT_SCORE_HT),
        THREE_TWO_HT(Markets.MarketType.EXACT_SCORE_HT), THREE_ONE_HT(Markets.MarketType.EXACT_SCORE_HT), THREE_THREE_HT(Markets.MarketType.EXACT_SCORE_HT),
        THREE_FOUR_HT(Markets.MarketType.EXACT_SCORE_HT), NIL_ONE_HT(Markets.MarketType.EXACT_SCORE_HT), NIL_TWO_HT(Markets.MarketType.EXACT_SCORE_HT),
        NIL_THREE_HT(Markets.MarketType.EXACT_SCORE_HT), NIL_FOUR_HT(Markets.MarketType.EXACT_SCORE_HT), ONE_ONE_HT(Markets.MarketType.EXACT_SCORE_HT),
        ONE_TWO_HT(Markets.MarketType.EXACT_SCORE_HT), ONE_THREE_HT(Markets.MarketType.EXACT_SCORE_HT), ONE_FOUR_HT(Markets.MarketType.EXACT_SCORE_HT),
        TWO_ONE_HT(Markets.MarketType.EXACT_SCORE_HT), TWO_TWO_HT(Markets.MarketType.EXACT_SCORE_HT), TWO_THREE_HT(Markets.MarketType.EXACT_SCORE_HT),
        TWO_FOUR_HT(Markets.MarketType.EXACT_SCORE_HT), OTHER_HT(Markets.MarketType.EXACT_SCORE_HT),

        FG_HOME(Markets.MarketType.FIRST_GOAL), FG_AWAY(Markets.MarketType.FIRST_GOAL), FG_NO_GOAL(Markets.MarketType.FIRST_GOAL),
        HDA_BTTS_HOME_YES(Markets.MarketType.HDA_BTTS), HDA_BTTS_HOME_NO(Markets.MarketType.HDA_BTTS), HDA_BTTS_DRAW_YES(Markets.MarketType.HDA_BTTS),
        HDA_BTTS_DRAW_NO(Markets.MarketType.HDA_BTTS), HDA_BTTS_AWAY_YES(Markets.MarketType.HDA_BTTS), HDA_BTTS_AWAY_NO(Markets.MarketType.HDA_BTTS),

        HDA_HOME_O1_5(Markets.MarketType.HDA_UNDEROVER), HDA_HOME_O2_5(Markets.MarketType.HDA_UNDEROVER), HDA_HOME_O3_5(Markets.MarketType.HDA_UNDEROVER), HDA_HOME_O4_5(Markets.MarketType.HDA_UNDEROVER),
        HDA_HOME_U1_5(Markets.MarketType.HDA_UNDEROVER), HDA_HOME_U2_5(Markets.MarketType.HDA_UNDEROVER), HDA_HOME_U3_5(Markets.MarketType.HDA_UNDEROVER), HDA_HOME_U4_5(Markets.MarketType.HDA_UNDEROVER),
        HDA_DRAW_O1_5(Markets.MarketType.HDA_UNDEROVER), HDA_DRAW_O2_5(Markets.MarketType.HDA_UNDEROVER), HDA_DRAW_O3_5(Markets.MarketType.HDA_UNDEROVER), HDA_DRAW_O4_5(Markets.MarketType.HDA_UNDEROVER),
        HDA_DRAW_U1_5(Markets.MarketType.HDA_UNDEROVER), HDA_DRAW_U2_5(Markets.MarketType.HDA_UNDEROVER), HDA_DRAW_U3_5(Markets.MarketType.HDA_UNDEROVER), HDA_DRAW_U4_5(Markets.MarketType.HDA_UNDEROVER),
        HDA_AWAY_O1_5(Markets.MarketType.HDA_UNDEROVER), HDA_AWAY_O2_5(Markets.MarketType.HDA_UNDEROVER), HDA_AWAY_O3_5(Markets.MarketType.HDA_UNDEROVER), HDA_AWAY_O4_5(Markets.MarketType.HDA_UNDEROVER),
        HDA_AWAY_U1_5(Markets.MarketType.HDA_UNDEROVER), HDA_AWAY_U2_5(Markets.MarketType.HDA_UNDEROVER), HDA_AWAY_U3_5(Markets.MarketType.HDA_UNDEROVER), HDA_AWAY_U4_5(Markets.MarketType.HDA_UNDEROVER),

        HT_FT_HOME_DRAW(Markets.MarketType.HT_FT), HT_FT_HOME_HOME(Markets.MarketType.HT_FT), HT_FT_HOME_AWAY(Markets.MarketType.HT_FT),
        HT_FT_DRAW_DRAW(Markets.MarketType.HT_FT), HT_FT_DRAW_HOME(Markets.MarketType.HT_FT), HT_FT_DRAW_AWAY(Markets.MarketType.HT_FT),
        HT_FT_AWAY_DRAW(Markets.MarketType.HT_FT), HT_FT_AWAY_HOME(Markets.MarketType.HT_FT), HT_FT_AWAY_AWAY(Markets.MarketType.HT_FT),

        HT_HOME(Markets.MarketType.HT), HT_DRAW(Markets.MarketType.HT), HT_AWAY(Markets.MarketType.HT),
        U1_5(Markets.MarketType.UO1_5), O1_5(Markets.MarketType.UO1_5),
        U2_5(Markets.MarketType.UO2_5), O2_5(Markets.MarketType.UO2_5),
        U3_5(Markets.MarketType.UO3_5), O3_5(Markets.MarketType.UO3_5),
        U4_5(Markets.MarketType.UO4_5), O4_5(Markets.MarketType.UO4_5),

        EMPTY_MARKET_VALUE(Markets.MarketType.EMPTY_MARKET_TYPE);


        private final Markets.MarketType type;
        private String representation;
        MarketValue(Markets.MarketType type){
            this.type = type;
        }

        MarketValue(Markets.MarketType type, String representation){
            this.type = type;
            this.representation = representation;
        }

        public Markets.MarketType getType() {
            return type;
        }

        public String getRepresentation(){
            return this.representation;
        }

        @Override
        public String toString(){
            if ( this.representation == null ) return super.toString();
            return this.representation;
        }
}
