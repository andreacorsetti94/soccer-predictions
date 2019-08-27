package com.acorsetti.model.enums;

public enum MarketValue {

        HDA_HOME(MarketType.HDA,"Home"), HDA_DRAW(MarketType.HDA,"Draw"), HDA_AWAY(MarketType.HDA,"Away"),
        BTTS_YES(MarketType.BTTS,"Goal"), BTTS_NO(MarketType.BTTS,"No Goal"),
        DC_HOME_DRAW(MarketType.DC,"Home or Draw"), DC_DRAW_AWAY(MarketType.DC, "Draw or Away"), DC_HOME_AWAY(MarketType.DC, "Home or Away"),

        NIL_NIL(MarketType.EXACT_SCORE,"0 - 0"), ONE_NIL(MarketType.EXACT_SCORE, "1 - 0"), TWO_NIL(MarketType.EXACT_SCORE, "2 - 0"),
        THREE_NIL(MarketType.EXACT_SCORE, "3 - 0"), FOUR_NIL(MarketType.EXACT_SCORE, "4 - 0"), FOUR_ONE(MarketType.EXACT_SCORE, "4 - 1"),
        FOUR_TWO(MarketType.EXACT_SCORE, "4 - 2"), FOUR_THREE(MarketType.EXACT_SCORE, "4 - 3"), FOUR_FOUR(MarketType.EXACT_SCORE, "4 - 4"),
        THREE_TWO(MarketType.EXACT_SCORE, "3 - 2"), THREE_ONE(MarketType.EXACT_SCORE, "3 - 1"), THREE_THREE(MarketType.EXACT_SCORE, "3 - 3"),
        THREE_FOUR(MarketType.EXACT_SCORE, "3 - 4"), NIL_ONE(MarketType.EXACT_SCORE, "0 - 1"), NIL_TWO(MarketType.EXACT_SCORE, "0 - 2"),
        NIL_THREE(MarketType.EXACT_SCORE, "0 - 3"), NIL_FOUR(MarketType.EXACT_SCORE, "0 - 4"), ONE_ONE(MarketType.EXACT_SCORE, "1 - 1"),
        ONE_TWO(MarketType.EXACT_SCORE, "1 - 2"), ONE_THREE(MarketType.EXACT_SCORE, "1 - 3"), ONE_FOUR(MarketType.EXACT_SCORE, "1 - 4"),
        TWO_ONE(MarketType.EXACT_SCORE, "2 - 1"), TWO_TWO(MarketType.EXACT_SCORE, "2 - 2"), TWO_THREE(MarketType.EXACT_SCORE, "2 - 3"),
        TWO_FOUR(MarketType.EXACT_SCORE, "2 - 4"), OTHER(MarketType.EXACT_SCORE, "Other"),

        HDA_BTTS_HOME_YES(MarketType.HDA_BTTS, "Home and Goal"), HDA_BTTS_HOME_NO(MarketType.HDA_BTTS, "Home and No Goal"), HDA_BTTS_DRAW_YES(MarketType.HDA_BTTS, "Draw and Goal"),
        HDA_BTTS_DRAW_NO(MarketType.HDA_BTTS, "Draw and No Goal"), HDA_BTTS_AWAY_YES(MarketType.HDA_BTTS, "Away and Goal"), HDA_BTTS_AWAY_NO(MarketType.HDA_BTTS, "Away and No Goal"),

        HDA_HOME_O1_5(MarketType.HDA_UNDEROVER, "Home and Over 1,5"), HDA_HOME_O2_5(MarketType.HDA_UNDEROVER, "Home and Over 2,5"), HDA_HOME_O3_5(MarketType.HDA_UNDEROVER, "Home and Over 3,5"), HDA_HOME_O4_5(MarketType.HDA_UNDEROVER,"Home and Over 4,5"),
        HDA_HOME_U1_5(MarketType.HDA_UNDEROVER, "Home and Under 1,5"), HDA_HOME_U2_5(MarketType.HDA_UNDEROVER, "Home and Under 2,5"), HDA_HOME_U3_5(MarketType.HDA_UNDEROVER, "Home and Under 3,5"), HDA_HOME_U4_5(MarketType.HDA_UNDEROVER, "Home and Under 4,5"),
        HDA_DRAW_O1_5(MarketType.HDA_UNDEROVER, "Draw and Over 1,5"), HDA_DRAW_O2_5(MarketType.HDA_UNDEROVER, "Draw and Over 2,5"), HDA_DRAW_O3_5(MarketType.HDA_UNDEROVER, "Draw and Over 3,5"), HDA_DRAW_O4_5(MarketType.HDA_UNDEROVER, "Draw and Over 4,5"),
        HDA_DRAW_U1_5(MarketType.HDA_UNDEROVER, "Draw and Under 1,5"), HDA_DRAW_U2_5(MarketType.HDA_UNDEROVER, "Draw and Under 2,5"), HDA_DRAW_U3_5(MarketType.HDA_UNDEROVER, "Draw and Under 3,5"), HDA_DRAW_U4_5(MarketType.HDA_UNDEROVER, "Draw and Under 4,5"),
        HDA_AWAY_O1_5(MarketType.HDA_UNDEROVER, "Away and Over 1,5"), HDA_AWAY_O2_5(MarketType.HDA_UNDEROVER, "Away and Over 2,5"), HDA_AWAY_O3_5(MarketType.HDA_UNDEROVER, "Away and Over 3,5"), HDA_AWAY_O4_5(MarketType.HDA_UNDEROVER, "Away and Over 4,5"),
        HDA_AWAY_U1_5(MarketType.HDA_UNDEROVER, "Away and Under 1,5"), HDA_AWAY_U2_5(MarketType.HDA_UNDEROVER, "Away and Under 2,5"), HDA_AWAY_U3_5(MarketType.HDA_UNDEROVER, "Away and Under 3,5"), HDA_AWAY_U4_5(MarketType.HDA_UNDEROVER, "Away and Under 4,5"),

        U1_5(MarketType.UO1_5, "Under 1,5"), O1_5(MarketType.UO1_5, "Over 1,5"),
        U2_5(MarketType.UO2_5, "Under 2,5"), O2_5(MarketType.UO2_5,"Over 2,5"),
        U3_5(MarketType.UO3_5,"Under 3,5"), O3_5(MarketType.UO3_5,"Over 3,5"),
        U4_5(MarketType.UO4_5,"Under 4,5"), O4_5(MarketType.UO4_5,"Over 4,5"),

        EMPTY_MARKET_VALUE(MarketType.EMPTY_MARKET_TYPE,"");

    private final MarketType type;
    private String representation;

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

    /**
     * Returns the MarketValue from a representation string
     * @param representation
     * @return
     */
    public MarketValue byRepresentation(String representation){
        for(MarketValue mv: MarketValue.values()){
            if ( mv.getRepresentation().equals(representation) ) return mv;
        }
        return MarketValue.EMPTY_MARKET_VALUE;
    }

    /**
     *
     * @param name in the format of (i.e. 'U4,5', 'HDA_HOME', etc.)
     * @return
     */
    public MarketValue byName(String name){
        for(MarketValue mv: MarketValue.values()){
            if ( mv.toString().equals(representation) ) return mv;
        }
        return MarketValue.EMPTY_MARKET_VALUE;
    }
}
