package com.acorsetti.core.model.eval;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.jpa.Fixture;

import java.util.Map;

public class MatchProbability {

    private Map<MarketValue,Chance> probabilities;
    private Fixture fixture;

    public MatchProbability(Fixture fixture,Map<MarketValue, Chance> probabilities) {
        this.probabilities = probabilities;
        this.fixture = fixture;
    }

    public Map<MarketValue, Chance> getProbabilities() {
        return probabilities;
    }

    public Fixture getFixture() {
        return fixture;
    }

    public Chance getMarketChance(MarketValue marketValue){
        return this.probabilities.get(marketValue);
    }

    @Override
    public String toString() {
        return "MatchProbability{" +
                "fixture=" + fixture.getFixtureId() + "," +
                "probabilities={" +
                "\nHome,Draw,Away=" + getMarketChance(MarketValue.HDA_HOME) + "," +getMarketChance(MarketValue.HDA_DRAW) + "," +getMarketChance(MarketValue.HDA_AWAY) +
                "\nBoth teams to Score:YES/NO=" + getMarketChance(MarketValue.BTTS_YES) + ","+ getMarketChance(MarketValue.BTTS_NO) +
                "\nDoubleChance: HomeDraw/DrawAway/HomeAway=" + getMarketChance(MarketValue.DC_HOME_DRAW) + ","+ getMarketChance(MarketValue.DC_DRAW_AWAY) + ","+ getMarketChance(MarketValue.DC_HOME_AWAY)+
                "\nHome & 'Both teams to Score:YES'" + getMarketChance(MarketValue.HDA_BTTS_HOME_YES) +
                "\nHome & 'Both teams to Score:NO'" + getMarketChance(MarketValue.HDA_BTTS_HOME_NO) +
                "\nDraw & 'Both teams to Score:YES'" + getMarketChance(MarketValue.HDA_BTTS_DRAW_YES) +
                "\nDraw & 'Both teams to Score:NO'" + getMarketChance(MarketValue.HDA_BTTS_DRAW_NO) +
                "\nAway & 'Both teams to Score:YES'" + getMarketChance(MarketValue.HDA_BTTS_AWAY_YES) +
                "\nAway & 'Both teams to Score:NO'" + getMarketChance(MarketValue.HDA_BTTS_AWAY_NO) +
                "\nHome & 'Over1,5 / 2,5 / 3,5 / 4,5'=" + getMarketChance(MarketValue.HDA_HOME_O1_5) +getMarketChance(MarketValue.HDA_HOME_O2_5) +getMarketChance(MarketValue.HDA_HOME_O3_5) +getMarketChance(MarketValue.HDA_HOME_O4_5) +
                "\nHome & 'Under1,5 / 2,5 / 3,5 / 4,5'=" + getMarketChance(MarketValue.HDA_HOME_U1_5) +getMarketChance(MarketValue.HDA_HOME_U2_5) +getMarketChance(MarketValue.HDA_HOME_U3_5) +getMarketChance(MarketValue.HDA_HOME_U4_5) +
                "\nDraw & 'Over1,5 / 2,5 / 3,5 / 4,5'=" + getMarketChance(MarketValue.HDA_DRAW_O1_5) +getMarketChance(MarketValue.HDA_DRAW_O2_5) +getMarketChance(MarketValue.HDA_DRAW_O3_5) +getMarketChance(MarketValue.HDA_DRAW_O4_5) +
                "\nDraw & 'Under1,5 / 2,5 / 3,5 / 4,5'=" + getMarketChance(MarketValue.HDA_DRAW_U1_5) +getMarketChance(MarketValue.HDA_DRAW_U2_5) +getMarketChance(MarketValue.HDA_DRAW_U3_5) +getMarketChance(MarketValue.HDA_DRAW_U4_5) +
                "\nAway & 'Over1,5 / 2,5 / 3,5 / 4,5'=" + getMarketChance(MarketValue.HDA_AWAY_O1_5) +getMarketChance(MarketValue.HDA_AWAY_O2_5) +getMarketChance(MarketValue.HDA_AWAY_O3_5) +getMarketChance(MarketValue.HDA_AWAY_O4_5) +
                "\nAway & 'Under1,5 / 2,5 / 3,5 / 4,5'=" + getMarketChance(MarketValue.HDA_AWAY_U1_5) +getMarketChance(MarketValue.HDA_AWAY_U2_5) +getMarketChance(MarketValue.HDA_AWAY_U3_5) +getMarketChance(MarketValue.HDA_AWAY_U4_5) +
                "\nUnder / Over 1,5: UNDER / OVER" +  getMarketChance(MarketValue.U1_5) + "," +getMarketChance(MarketValue.O1_5) +
                "\nUnder / Over 2,5: UNDER / OVER" +  getMarketChance(MarketValue.U2_5) + "," +getMarketChance(MarketValue.O2_5) +
                "\nUnder / Over 3,5: UNDER / OVER" +  getMarketChance(MarketValue.U3_5) + "," +getMarketChance(MarketValue.O3_5) +
                "\nUnder / Over 4,5: UNDER / OVER" +  getMarketChance(MarketValue.U4_5) + "," +getMarketChance(MarketValue.O4_5) +
                "\n0 - 0" +  getMarketChance(MarketValue.NIL_NIL) +
                "\n1 - 0" +  getMarketChance(MarketValue.ONE_NIL) +
                "\n2 - 0" +  getMarketChance(MarketValue.TWO_NIL) +
                "\n3 - 0" +  getMarketChance(MarketValue.THREE_NIL) +
                "\n4 - 0" +  getMarketChance(MarketValue.FOUR_NIL) +
                "\n1 - 1" +  getMarketChance(MarketValue.ONE_ONE) +
                "\n2 - 1" +  getMarketChance(MarketValue.TWO_ONE) +
                "\n2 - 2" +  getMarketChance(MarketValue.TWO_TWO) +
                "\n3 - 1" +  getMarketChance(MarketValue.THREE_ONE) +
                "\n3 - 2" +  getMarketChance(MarketValue.THREE_TWO) +
                "\n3 - 3" +  getMarketChance(MarketValue.THREE_THREE) +
                "\n4 - 1" +  getMarketChance(MarketValue.FOUR_ONE) +
                "\n4 - 2" +  getMarketChance(MarketValue.FOUR_TWO) +
                "\n4 - 3" +  getMarketChance(MarketValue.FOUR_THREE) +
                "\n4 - 4" +  getMarketChance(MarketValue.FOUR_FOUR) +
                "\n0 - 1" +  getMarketChance(MarketValue.NIL_ONE) +
                "\n0 - 2" +  getMarketChance(MarketValue.NIL_TWO) +
                "\n0 - 3" +  getMarketChance(MarketValue.NIL_THREE) +
                "\n0 - 4" +  getMarketChance(MarketValue.NIL_FOUR) +
                "\n1 - 2" +  getMarketChance(MarketValue.ONE_TWO) +
                "\n1 - 3" +  getMarketChance(MarketValue.ONE_THREE) +
                "\n2 - 3" +  getMarketChance(MarketValue.TWO_THREE) +
                "\n1 - 4" +  getMarketChance(MarketValue.ONE_FOUR) +
                "\n2 - 4" +  getMarketChance(MarketValue.TWO_FOUR) +
                "\n3 - 4" +  getMarketChance(MarketValue.THREE_FOUR) +
                "\nOther" +  getMarketChance(MarketValue.OTHER) +
                "\n}}";
    }


}
