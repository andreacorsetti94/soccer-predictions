package com.acorsetti.service;

import com.acorsetti.model.eval.Chance;
import com.acorsetti.model.eval.PickValue;
import com.acorsetti.model.odds.OddsValue;

public interface PickValueCalculatorService {

    PickValue calculatePickValue(Chance chance, OddsValue odds);
}
