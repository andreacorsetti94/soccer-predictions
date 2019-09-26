package com.acorsetti.core.api.json;

import com.acorsetti.core.model.dto.OddsDto;
import com.acorsetti.core.model.enums.MarketType;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.odds.MarketOdds;
import com.acorsetti.core.model.odds.OddsValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class JSONOddsResponse extends JsonResponse<OddsDto>{

    public JSONOddsResponse() {
    }

    public JSONOddsResponse(int results, List<OddsDto> dataList) {
        super(results, dataList);
    }

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpackNested(Map<String, Object> map){

        if ( ! (map.get("odds") instanceof Map) ) { //if there are no odds
            super.setDataList(Collections.emptyList());
            super.setResults(0);
            return;
        }
        Map<String,Object> oddsMap = (Map<String, Object>) map.get("odds");
        Map<String,Object> hda = (Map<String, Object>) oddsMap.get("Win the match");
        Map<String,Object> exactScore = (Map<String, Object>) oddsMap.get("Score exact (FullTime)");
        Map<String,Object> doubleChance = (Map<String, Object>) oddsMap.get("Double chance (FullTime)");
        Map<String,Object> hdaBtts = (Map<String, Object>) oddsMap.get("Result & The 2 teams score");
        Map<String,Object> btts = (Map<String, Object>) oddsMap.get("Both teams score");
        Map<String,Object> overUnder1_5 = (Map<String, Object>) oddsMap.get("Over/Under 1,5 goal (FullTime)");
        Map<String,Object> overUnder2_5 = (Map<String, Object>) oddsMap.get("Over/Under 2,5 goals (FullTime)");
        Map<String,Object> overUnder3_5 = (Map<String, Object>) oddsMap.get("Over/Under 3,5 goals (FullTime)");
        Map<String,Object> overUnder4_5 = (Map<String, Object>) oddsMap.get("Over/Under 4,5 goals (FullTime)");
        Map<String,Object> hdaOverUnder1_5 = (Map<String, Object>) oddsMap.get("Result & Over/Under 1,5 goals (FullTime)");
        Map<String,Object> hdaOverUnder2_5 = (Map<String, Object>) oddsMap.get("Result & Over/Under 2,5 goals (FullTime)");
        Map<String,Object> hdaOverUnder3_5 = (Map<String, Object>) oddsMap.get("Result & Over/Under 3,5 goals (FullTime)");
        Map<String,Object> hdaOverUnder4_5 = (Map<String, Object>) oddsMap.get("Result & Over/Under 4,5 goals (FullTime)");

        List<MarketOdds> marketOdds = new ArrayList<>();
        this.computeHdaMap(marketOdds, hda);
        this.computeDoubleChanceMap(marketOdds, doubleChance);
        this.computeBttsMap(marketOdds, btts);
        this.computeHdaBttsMap(marketOdds, hdaBtts);
        this.computeUnderOver1_5Map(marketOdds, overUnder1_5);
        this.computeUnderOver2_5Map(marketOdds, overUnder2_5);
        this.computeUnderOver3_5Map(marketOdds, overUnder3_5);
        this.computeUnderOver4_5Map(marketOdds, overUnder4_5);
        this.computeHdaUnderOver1_5Map(marketOdds, hdaOverUnder1_5);
        this.computeHdaUnderOver2_5Map(marketOdds, hdaOverUnder2_5);
        this.computeHdaUnderOver3_5Map(marketOdds, hdaOverUnder3_5);
        this.computeHdaUnderOver4_5Map(marketOdds, hdaOverUnder4_5);
        this.computeExactScoreMap(marketOdds, exactScore);
        super.setDataList(new ArrayList<>());
        OddsDto oddsDto = new OddsDto(null,marketOdds);
        super.getDataList().add(oddsDto);
        super.setResults(1);
    }

    @SuppressWarnings("unchecked")
    private void computeHdaMap(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        Map<String,String> oneMap = (Map<String, String>) map.get("1");
        if ( oneMap == null ) return;
        String value = oneMap.get("odd");
        double odd = Double.parseDouble( value );

        marketOdds.add(new MarketOdds(MarketType.HDA, MarketValue.HDA_HOME, new OddsValue(odd)));

        Map<String,String> xMap = (Map<String, String>) map.get("N");
        if ( xMap == null ) return;
        value = xMap.get("odd");
        odd = Double.parseDouble( value );
        marketOdds.add(new MarketOdds(MarketType.HDA, MarketValue.HDA_DRAW, new OddsValue(odd)));

        Map<String,String> twoMap = (Map<String, String>) map.get("2");
        if ( twoMap == null ) return;
        value = twoMap.get("odd");
        odd = Double.parseDouble( value );

        marketOdds.add(new MarketOdds(MarketType.HDA, MarketValue.HDA_AWAY, new OddsValue(odd)));
    }

    @SuppressWarnings("unchecked")
    private void computeDoubleChanceMap(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        Map<String,String> winDraw = (Map<String, String>) map.get("1/N");
        if ( winDraw == null ) return;
        String value = winDraw.get("odd");
        double odd = Double.parseDouble( value );

        marketOdds.add(new MarketOdds(MarketType.DC, MarketValue.DC_HOME_DRAW, new OddsValue(odd)));

        Map<String,String> drawAway = (Map<String, String>) map.get("N/2");
        if ( drawAway == null ) return;
        value = drawAway.get("odd");
        odd = Double.parseDouble( value );
        marketOdds.add(new MarketOdds(MarketType.DC, MarketValue.DC_DRAW_AWAY, new OddsValue(odd)));

        Map<String,String> homeAway = (Map<String, String>) map.get("1/2");
        if ( homeAway == null ) return;
        value = homeAway.get("odd");
        odd = Double.parseDouble( value );

        marketOdds.add(new MarketOdds(MarketType.DC, MarketValue.DC_HOME_AWAY, new OddsValue(odd)));
    }

    @SuppressWarnings("unchecked")
    private void computeHdaBttsMap(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        map.forEach( (k,v) -> {
            Map<String,String> posMap = (Map<String, String>) map.get(k);
            if ( posMap == null ) return;
            String pos = posMap.get("pos");
            String oddsString = posMap.get("odd");
            double odd = Double.parseDouble(oddsString);
            switch (pos){
                case "1":
                    marketOdds.add(new MarketOdds(MarketType.HDA_BTTS, MarketValue.HDA_BTTS_HOME_YES, new OddsValue(odd)));
                    break;
                case "2":
                    marketOdds.add(new MarketOdds(MarketType.HDA_BTTS, MarketValue.HDA_BTTS_HOME_NO, new OddsValue(odd)));
                    break;
                case "3":
                    marketOdds.add(new MarketOdds(MarketType.HDA_BTTS, MarketValue.HDA_BTTS_DRAW_YES, new OddsValue(odd)));
                    break;
                case "4":
                    marketOdds.add(new MarketOdds(MarketType.HDA_BTTS, MarketValue.HDA_BTTS_DRAW_NO, new OddsValue(odd)));
                    break;
                case "5":
                    marketOdds.add(new MarketOdds(MarketType.HDA_BTTS, MarketValue.HDA_BTTS_AWAY_YES, new OddsValue(odd)));
                    break;
                case "6":
                    marketOdds.add(new MarketOdds(MarketType.HDA_BTTS, MarketValue.HDA_BTTS_AWAY_NO, new OddsValue(odd)));
                    break;
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void computeBttsMap(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        Map<String,String> yesMap = (Map<String, String>) map.get("Yes");
        if ( yesMap == null ) return;
        String value = yesMap.get("odd");
        double odd = Double.parseDouble( value );

        marketOdds.add(new MarketOdds(MarketType.BTTS, MarketValue.BTTS_YES, new OddsValue(odd)));

        Map<String,String> noMap = (Map<String, String>) map.get("No");
        if ( noMap == null ) return;
        value = noMap.get("odd");
        odd = Double.parseDouble( value );
        marketOdds.add(new MarketOdds(MarketType.BTTS, MarketValue.BTTS_NO, new OddsValue(odd)));
    }

    @SuppressWarnings("unchecked")
    private void computeUnderOver1_5Map(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        Map<String,String> overMap = (Map<String, String>) map.get("Over 1,5");
        if ( overMap == null ) return;
        String value = overMap.get("odd");
        double odd = Double.parseDouble( value );

        marketOdds.add(new MarketOdds(MarketType.UO1_5, MarketValue.O1_5, new OddsValue(odd)));

        Map<String,String> underMap = (Map<String, String>) map.get("Under 1,5");
        if ( underMap == null ) return;
        value = underMap.get("odd");
        odd = Double.parseDouble( value );
        marketOdds.add(new MarketOdds(MarketType.UO1_5, MarketValue.U1_5, new OddsValue(odd)));
    }

    @SuppressWarnings("unchecked")
    private void computeUnderOver2_5Map(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        Map<String,String> overMap = (Map<String, String>) map.get("Over 2,5");
        if ( overMap == null ) return;
        String value = overMap.get("odd");
        double odd = Double.parseDouble( value );

        marketOdds.add(new MarketOdds(MarketType.UO2_5, MarketValue.O2_5, new OddsValue(odd)));

        Map<String,String> underMap = (Map<String, String>) map.get("Under 2,5");
        if ( underMap == null ) return;
        value = underMap.get("odd");
        odd = Double.parseDouble( value );
        marketOdds.add(new MarketOdds(MarketType.UO2_5, MarketValue.U2_5, new OddsValue(odd)));
    }

    @SuppressWarnings("unchecked")
    private void computeUnderOver3_5Map(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        Map<String,String> overMap = (Map<String, String>) map.get("Over 3,5");
        if ( overMap == null ) return;
        String value = overMap.get("odd");
        double odd = Double.parseDouble( value );

        marketOdds.add(new MarketOdds(MarketType.UO3_5, MarketValue.O3_5, new OddsValue(odd)));

        Map<String,String> underMap = (Map<String, String>) map.get("Under 3,5");
        if ( underMap == null ) return;
        value = underMap.get("odd");
        odd = Double.parseDouble( value );
        marketOdds.add(new MarketOdds(MarketType.UO3_5, MarketValue.U3_5, new OddsValue(odd)));
    }

    @SuppressWarnings("unchecked")
    private void computeUnderOver4_5Map(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        Map<String,String> overMap = (Map<String, String>) map.get("Over 4,5");
        if ( overMap == null ) return;
        String value = overMap.get("odd");
        double odd = Double.parseDouble( value );

        marketOdds.add(new MarketOdds(MarketType.UO4_5, MarketValue.O4_5, new OddsValue(odd)));

        Map<String,String> underMap = (Map<String, String>) map.get("Under 4,5");
        if ( underMap == null ) return;
        value = underMap.get("odd");
        odd = Double.parseDouble( value );
        marketOdds.add(new MarketOdds(MarketType.UO4_5, MarketValue.U4_5, new OddsValue(odd)));
    }

    @SuppressWarnings("unchecked")
    private void computeHdaUnderOver1_5Map(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        map.forEach( (k,v) -> {
            Map<String,String> posMap = (Map<String, String>) map.get(k);
            if ( posMap == null ) return;
            String pos = posMap.get("pos");
            String oddsString = posMap.get("odd");
            double odd = Double.parseDouble(oddsString);
            switch (pos){
                case "1":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_HOME_O1_5, new OddsValue(odd)));
                    break;
                case "2":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_DRAW_O1_5, new OddsValue(odd)));
                    break;
                case "3":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_AWAY_O1_5, new OddsValue(odd)));
                    break;
                case "4":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_HOME_U1_5, new OddsValue(odd)));
                    break;
                case "5":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_DRAW_U1_5, new OddsValue(odd)));
                    break;
                case "6":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_AWAY_U1_5, new OddsValue(odd)));
                    break;
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void computeHdaUnderOver2_5Map(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        map.forEach( (k,v) -> {
            Map<String,String> posMap = (Map<String, String>) map.get(k);
            if ( posMap == null ) return;
            String pos = posMap.get("pos");
            String oddsString = posMap.get("odd");
            double odd = Double.parseDouble(oddsString);
            switch (pos){
                case "1":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_HOME_O2_5, new OddsValue(odd)));
                    break;
                case "2":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_DRAW_O2_5, new OddsValue(odd)));
                    break;
                case "3":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_AWAY_O2_5, new OddsValue(odd)));
                    break;
                case "4":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_HOME_U2_5, new OddsValue(odd)));
                    break;
                case "5":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_DRAW_U2_5, new OddsValue(odd)));
                    break;
                case "6":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_AWAY_U2_5, new OddsValue(odd)));
                    break;
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void computeHdaUnderOver3_5Map(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        map.forEach( (k,v) -> {
            Map<String,String> posMap = (Map<String, String>) map.get(k);
            if ( posMap == null ) return;

            String pos = posMap.get("pos");
            String oddsString = posMap.get("odd");
            double odd = Double.parseDouble(oddsString);
            switch (pos){
                case "1":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_HOME_O3_5, new OddsValue(odd)));
                    break;
                case "2":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_DRAW_O3_5, new OddsValue(odd)));
                    break;
                case "3":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_AWAY_O3_5, new OddsValue(odd)));
                    break;
                case "4":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_HOME_U3_5, new OddsValue(odd)));
                    break;
                case "5":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_DRAW_U3_5, new OddsValue(odd)));
                    break;
                case "6":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_AWAY_U3_5, new OddsValue(odd)));
                    break;
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void computeHdaUnderOver4_5Map(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        map.forEach( (k,v) -> {
            Map<String,String> posMap = (Map<String, String>) map.get(k);
            if ( posMap == null ) return;

            String pos = posMap.get("pos");
            String oddsString = posMap.get("odd");
            double odd = Double.parseDouble(oddsString);
            switch (pos){
                case "1":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_HOME_O4_5, new OddsValue(odd)));
                    break;
                case "2":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_DRAW_O4_5, new OddsValue(odd)));
                    break;
                case "3":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_AWAY_O4_5, new OddsValue(odd)));
                    break;
                case "4":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_HOME_U4_5, new OddsValue(odd)));
                    break;
                case "5":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_DRAW_U4_5, new OddsValue(odd)));
                    break;
                case "6":
                    marketOdds.add(new MarketOdds(MarketType.HDA_UNDEROVER, MarketValue.HDA_AWAY_U4_5, new OddsValue(odd)));
                    break;
            }
        });
    }

    @SuppressWarnings("unchecked")
    private void computeExactScoreMap(List<MarketOdds> marketOdds, Map<String,Object> map){
        if ( map == null ) return;
        Map<String,String> other = (Map<String, String>) map.get("Other");
        if ( other == null ) return;

        double odds = Double.parseDouble(other.get("odd"));
        marketOdds.add(new MarketOdds(MarketType.EXACT_SCORE, MarketValue.OTHER, new OddsValue(odds)));

        for(int home = 0; home <= 4; home++){
            for(int away = 0; away <= 4; away++){
                String rep = home + " - " + away;
                Map<String,String> exactScoreMap = (Map<String, String>) map.get(rep);
                odds = Double.parseDouble(exactScoreMap.get("odd"));
                MarketValue mv = MarketValue.byRepresentation(rep);
                marketOdds.add(new MarketOdds(MarketType.EXACT_SCORE, mv, new OddsValue(odds)));
            }
        }
    }



}
