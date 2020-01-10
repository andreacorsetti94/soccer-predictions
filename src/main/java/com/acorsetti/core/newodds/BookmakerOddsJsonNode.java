package com.acorsetti.core.newodds;
import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.odds.MarketOdds;
import com.acorsetti.core.model.odds.OddsValue;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;

public class BookmakerOddsJsonNode {

    private List<BookmakerOdds> bookmakerOdds = new ArrayList<>();

    @SuppressWarnings("unchecked")
    @JsonProperty("api")
    private void unpackNested(Map<String, Object> map){
        List<Object> oddsList = (List<Object>) map.get("odds");
        if ( oddsList == null || oddsList.isEmpty()) return;

        Map oddsMap = (Map) oddsList.get(0);
        Map<String,Object> fixtureInfo = (Map<String, Object>) oddsMap.get("fixture");
        String dateString = fixtureInfo.get("updateAt").toString();
        LocalDateTime startOfMatch = LocalDateTime.ofInstant(Instant.ofEpochSecond(Long.valueOf(dateString)),
                TimeZone.getDefault().toZoneId());
        List<Object> bookmakerList = (List<Object>) oddsMap.get("bookmakers");
        for(Object bookmaker: bookmakerList){
            Map<String,Object> bookmakerInfo = (Map<String, Object>) bookmaker;
            String bookmakerName = (String) bookmakerInfo.get("bookmaker_name");
            List<MarketOdds> marketOddsList = new ArrayList<>();
            List<Object> bets = (List<Object>) bookmakerInfo.get("bets");
            for(Object object: bets){
                Map<String,Object> betObject = (Map<String, Object>) object;
                String labelName = (String) betObject.get("label_name");
                if ( !this.isLabelLegit(labelName) ) continue;
                List<Object> valuesList = (List<Object>) betObject.get("values");
                for(Object value: valuesList){
                    Map<String,String> valueMap = (Map<String, String>) value;
                    String pick = valueMap.get("value");
                    String oddVal = valueMap.get("odd");
                    double oddValue = Double.parseDouble(oddVal);
                    OddsValue oddsValue = new OddsValue(oddValue);
                    MarketValue mv = this.marketByLabel(pick);
                    if(mv == MarketValue.EMPTY_MARKET_VALUE) continue;
                    MarketOdds marketOdds = new MarketOdds(mv.getType(), mv, oddsValue);
                    marketOddsList.add(marketOdds);
                }
            }
            BookmakerOdds bookmakerOdds = new BookmakerOdds(bookmakerName, "", marketOddsList, startOfMatch);
            this.bookmakerOdds.add(bookmakerOdds);
        }
    }

    private boolean isLabelLegit(String label){
        String[] acceptedLabels = {"Match Winner","Goals Over/Under","Both Teams Score","Double Chance"};
        List<String> labels = Arrays.asList(acceptedLabels);
        return labels.contains(label);
    }

    private MarketValue marketByLabel(String label){
        switch (label){
            case "Home": return MarketValue.HDA_HOME;
            case "Draw": return MarketValue.HDA_DRAW;
            case "Away": return MarketValue.HDA_AWAY;
            case "Over 3.5": return MarketValue.O3_5;
            case "Under 3.5": return MarketValue.U3_5;
            case "Over 1.5": return MarketValue.O1_5;
            case "Under 1.5": return MarketValue.U1_5;
            case "Over 2.5": return MarketValue.O2_5;
            case "Under 2.5": return MarketValue.U2_5;
            case "Over 4.5": return MarketValue.O4_5;
            case "Under 4.5": return MarketValue.U4_5;
            case "Yes": return MarketValue.BTTS_YES;
            case "No": return MarketValue.BTTS_NO;
            case "Home/Draw": return MarketValue.DC_HOME_DRAW;
            case "Home/Away": return MarketValue.DC_HOME_AWAY;
            case "Draw/Away": return MarketValue.DC_DRAW_AWAY;
            default: return MarketValue.EMPTY_MARKET_VALUE;
        }
    }

    public List<BookmakerOdds> getBookmakerOdds() {
        return bookmakerOdds;
    }
}
