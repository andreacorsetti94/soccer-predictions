package com.acorsetti.core.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {

    public static double round(double value, int digits){
        if ( value == 0 || Double.isNaN(value) ) return 0;
        return new BigDecimal(value).setScale(digits, RoundingMode.HALF_UP).doubleValue();
    }

}
