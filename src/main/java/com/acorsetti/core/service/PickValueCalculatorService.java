package com.acorsetti.core.service;

import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.PickValue;
import com.acorsetti.core.model.odds.OddsValue;

public interface PickValueCalculatorService {

    PickValue calculatePickValue(Chance chance, OddsValue odds);
}
