package com.acorsetti.utils;

public class OddsUtils {

    public static double expectedValue(double odds, double chance){
        if ( chance == 0 ) return 0;
        return MathUtils.round((chance/100) - (1/odds), 2);
    }

}
