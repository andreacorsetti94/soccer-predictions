package com.acorsetti.model.eval;

import com.acorsetti.utils.MathUtils;

public class Chance {

    private double value;

    public Chance(double value) {
        this.setValue(value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if ( value <= 0 ) this.value = 0.0;
        else if ( value > 100 ) this.value = MathUtils.round(value / 100, 2);
        else this.value = MathUtils.round(value, 2);
    }

    @Override
    public String toString() {
        return "{Chance=" + value + '}';
    }
}
