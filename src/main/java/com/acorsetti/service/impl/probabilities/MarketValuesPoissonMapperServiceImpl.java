package com.acorsetti.service.impl.probabilities;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.eval.Chance;
import com.acorsetti.service.probabilities.MarketValuesPoissonMapperService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
class MarketValuesPoissonMapperServiceImpl implements MarketValuesPoissonMapperService {

    public Chance computeOver(Map<MarketValue, Chance> exactScoreChances, int overLimit){
        double chance = 0.0;
        for(MarketValue mv: exactScoreChances.keySet()){
            double mvChance = exactScoreChances.get(mv).getValue();
            String score = mv.getRepresentation();

            if(!score.contains("-")){
                chance += mvChance;
                continue;
            }

            int homeGoals = extractHomeGoals(score);
            int awayGoals = extractAwayGoals(score);

            if(homeGoals + awayGoals > overLimit){
                chance += mvChance;
            }
        }
        return new Chance(chance);
    }

    public Chance computeHomeFT(Map<MarketValue, Chance> exactScoreChances){
        double chance = 0.0;
        for(MarketValue mv: exactScoreChances.keySet()){
            double mvChance = exactScoreChances.get(mv).getValue();
            String score = mv.getRepresentation();

            if(!score.contains("-")) continue;

            int homeGoals = extractHomeGoals(score);
            int awayGoals = extractAwayGoals(score);

            if(homeGoals > awayGoals){
                chance += mvChance;
            }
        }
        return new Chance(chance);
    }

    public Chance computeAwayFT(Map<MarketValue, Chance> exactScoreChances){
        double chance = 0.0;
        for(MarketValue mv: exactScoreChances.keySet()){
            double mvChance = exactScoreChances.get(mv).getValue();
            String score = mv.getRepresentation();

            if(!score.contains("-")) continue;

            int homeGoals = extractHomeGoals(score);
            int awayGoals = extractAwayGoals(score);

            if(homeGoals < awayGoals){
                chance += mvChance;
            }
        }
        return new Chance(chance);
    }

    public Chance computeGoalFT(Map<MarketValue, Chance> exactScoreChances) {
        double chance = 0.0;
        for(MarketValue mv: exactScoreChances.keySet()){
            double mvChance = exactScoreChances.get(mv).getValue();
            String score = mv.getRepresentation();

            if (!score.contains("-")) continue;

            int homeGoals = extractHomeGoals(score);
            int awayGoals = extractAwayGoals(score);

            if (homeGoals > 0 && awayGoals > 0) {
                chance += mvChance;
            }
        }
        return new Chance(chance);
    }

    private static int extractHomeGoals(String result){
        String substring = result.substring(0,result.indexOf("-")).trim();
        return Integer.parseInt(substring);
    }

    private static int extractAwayGoals(String result){
        String substring = result.substring(result.indexOf("-") + 1).trim();
        return Integer.parseInt(substring);
    }

}
