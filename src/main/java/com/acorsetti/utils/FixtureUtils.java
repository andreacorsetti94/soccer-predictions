package com.acorsetti.utils;

import org.apache.log4j.Logger;

public class FixtureUtils {
    private static Logger logger = Logger.getLogger(FixtureUtils.class);

    public static int goalsFromScore(String score, boolean home) {
        try{
            if ( home ){
                return Integer.parseInt(score.split("-")[0].trim());
            }
            else{
                return Integer.parseInt(score.split("-")[1].trim());
            }

        }
        catch(Exception e){
            logger.warn("Goals from score string: " + score + " not retrieved");
            throw e;
        }
    }

}
