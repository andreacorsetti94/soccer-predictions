package com.acorsetti.core.service.probabilities;

import com.acorsetti.core.model.enums.MarketValue;
import com.acorsetti.core.model.eval.Chance;

import java.util.Map;

public interface MarketValuesPoissonMapperService {

    Chance computeOver(Map<MarketValue, Chance> exactScoreChances, int overLimit);
    Chance computeHomeFT(Map<MarketValue, Chance> exactScoreChances);
    Chance computeAwayFT(Map<MarketValue, Chance> exactScoreChances);
    Chance computeGoalFT(Map<MarketValue, Chance> exactScoreChances);
}
