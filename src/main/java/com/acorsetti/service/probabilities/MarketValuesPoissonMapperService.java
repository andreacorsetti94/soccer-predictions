package com.acorsetti.service.probabilities;

import com.acorsetti.model.enums.MarketValue;
import com.acorsetti.model.eval.Chance;

import java.util.Map;

public interface MarketValuesPoissonMapperService {

    Chance computeOver(Map<MarketValue, Chance> exactScoreChances, int overLimit);
    Chance computeHomeFT(Map<MarketValue, Chance> exactScoreChances);
    Chance computeAwayFT(Map<MarketValue, Chance> exactScoreChances);
    Chance computeGoalFT(Map<MarketValue, Chance> exactScoreChances);
}
