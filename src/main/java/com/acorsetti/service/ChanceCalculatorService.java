package com.acorsetti.service;

import com.acorsetti.model.eval.Chance;
import com.acorsetti.model.eval.MarketOutcome;

public interface ChanceCalculatorService {

    Chance calculate(String fixtureId, MarketOutcome marketOutcome);
}
