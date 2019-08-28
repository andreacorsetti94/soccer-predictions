package com.acorsetti.model.odds;

import com.acorsetti.utils.MathUtils;

import java.util.Objects;

public class OddsValue {

    private double value;
    private boolean isLegit = true;

    public OddsValue(double value) {
        this.setValue(value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if ( value == 1 ) this.value = 1.01;
        else if ( value < 1 ){
            this.isLegit = false;
            this.value = Double.NaN;
        }
        else this.value = MathUtils.round(value, 2);
    }

    public boolean isLegit(){
        return this.isLegit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OddsValue oddsValue = (OddsValue) o;
        return Double.compare(oddsValue.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
