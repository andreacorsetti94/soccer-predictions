package com.acorsetti.core.newodds;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.odds.MarketOdds;
import com.acorsetti.core.model.odds.OddsValue;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OddsCleaner {

    public List<OddEntity> clean(List<BookmakerOdds> bookmakerOddsList){
        if ( bookmakerOddsList == null || bookmakerOddsList.isEmpty() ) return Collections.emptyList();
        List<OddEntity> oddEntities = new ArrayList<>();
        Map<MarketValue,List<OddsValue>> map = new HashMap<>();

        String fixtureId = bookmakerOddsList.get(0).getFixtureId();
        for(BookmakerOdds bookmakerOdds: bookmakerOddsList){
            List<MarketOdds> marketOdds = bookmakerOdds.getMarketOdds();
            marketOdds.forEach( marketOdd -> {
                MarketValue mv = marketOdd.getMarketValue();
                OddsValue ov = marketOdd.getOddsValue();
                if ( map.containsKey(mv) ){
                    map.get(mv).add(ov);
                }
                else {
                    List<OddsValue> oddsValues = new ArrayList<>();
                    oddsValues.add(ov);
                    map.put(mv, oddsValues);
                }
            });
        }

        map.forEach( (marketValue, oddsValues) -> {
            double avg = oddsValues.stream().mapToDouble(OddsValue::getValue).average().orElse(0.0);
            double max = oddsValues.stream().mapToDouble(OddsValue::getValue).max().orElse(0.0);
            OddEntity oddEntity = new OddEntity(fixtureId, marketValue, new OddsValue(avg), new OddsValue(max));
            oddEntities.add(oddEntity);
        });

        return oddEntities;
    }
}
