package com.acorsetti.service.impl;

import com.acorsetti.model.eval.Chance;
import com.acorsetti.model.eval.PickValue;
import com.acorsetti.model.odds.OddsValue;
import com.acorsetti.service.PickValueCalculatorService;
import com.acorsetti.utils.MathUtils;
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
