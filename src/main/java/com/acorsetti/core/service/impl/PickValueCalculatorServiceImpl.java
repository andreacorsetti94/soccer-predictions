package com.acorsetti.core.service.impl;

import com.acorsetti.core.model.eval.Chance;
import com.acorsetti.core.model.eval.PickValue;
import com.acorsetti.core.model.odds.OddsValue;
import com.acorsetti.core.service.PickValueCalculatorService;
import com.acorsetti.core.utils.MathUtils;
import org.springframework.stereotype.Service;

@Service
public class PickValueCalculatorServiceImpl implements PickValueCalculatorService {

    @Override
    public PickValue calculatePickValue(Chance chance, OddsValue odds) {
        if ( chance.getValue() == 0 ) return new PickValue(0.0);
        if ( ! odds.isLegit() ) return new PickValue(0.0);
        double value = MathUtils.round(chance.getValue() - (1/odds.getValue()), 2);
        return new PickValue(value);
    }

}
