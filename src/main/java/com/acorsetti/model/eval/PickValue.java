package com.acorsetti.model.eval;

import com.acorsetti.utils.MathUtils;

import java.util.Objects;

public class PickValue {

    private double value;

    public PickValue(double value) {
        this.setValue(value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if ( value == 1 ) this.value = 0.99;
        if ( value > 1 ) this.value = MathUtils.round(value / 100, 2);
        else this.value = MathUtils.round(value, 2);
    }

    @Override
    public String toString() {
        return "PickValue{" +
                "value=" + value +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PickValue pickValue = (PickValue) o;
        return Double.compare(pickValue.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
