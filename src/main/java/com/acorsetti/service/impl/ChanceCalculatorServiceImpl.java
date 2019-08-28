package com.acorsetti.service.impl;

import com.acorsetti.model.eval.Chance;
import com.acorsetti.model.eval.MarketOutcome;
import com.acorsetti.service.ChanceCalculatorService;
import org.springframework.stereotype.Service;

@Service
public class ChanceCalculatorServiceImpl implements ChanceCalculatorService {

    @Override
    public Chance calculate(String fixtureId, MarketOutcome marketOutcome) {
        //TODO
        return null;
    }
}
