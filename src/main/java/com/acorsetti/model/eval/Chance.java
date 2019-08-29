package com.acorsetti.model.eval;

import com.acorsetti.utils.MathUtils;

import java.util.Objects;

public class Chance {

    private double value;

    public Chance(double value) {
        this.setValue(value);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if ( value <= 0.01 ){
            this.value = 0.01;
        }
        else if ( value > 1 && value != 100 ) {
            this.value = value / 100;
        }
        else if ( value == 100 || value == 1 ){
            this.value = 0.99;
        }
        else{
            this.value = MathUtils.round(value, 2);
        }
    }

    @Override
    public String toString() {
        return "{Chance=" + value + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chance chance = (Chance) o;
        return Double.compare(chance.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
