package com.acorsetti.utils;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    private static final Logger logger = Logger.getLogger(DateUtils.class);

    public static Date formatFixtureDate(String inputString){
        String jsonDateFormat = ".*T.*\\+.*"; //2019-02-12T20:00:00+00:00
        String dbDateFormat = "[^T]*"; //yyyy-MM-dd HH:mm
        if ( inputString.matches(jsonDateFormat) ){
            return formatJSONEventDate(inputString);
        }
        else if( inputString.matches(dbDateFormat ) ){
            return DateUtils.formatDate(inputString, "yyyy-MM-dd HH:mm");
        }
        else{
            logger.error(" String : " + inputString + " doesnt match " +
                    " this format: " +jsonDateFormat + " nor " + dbDateFormat + " NULL date returned.");
            return null;
        }
    }

    /**
     * This method formats a fixture event date in format: (example) 2019-02-12T20:00:00+00:00 to a Date object
     * @param time
     * @return
     */
    private static Date formatJSONEventDate(String time){
        int indexOf = time.indexOf("+");
        if (indexOf != -1) {
            time = time.substring(0, indexOf);
        }

        time = time.replace("T"," ");
        return DateUtils.formatDate(time, "yyyy-MM-dd HH:mm:ss");

    }

    public static Date formatDate(String inputDate, String format){
        try{
            return new SimpleDateFormat(format).parse(inputDate);
        }
        catch (ParseException ex){
            Logger.getLogger(DateUtils.class).error("Unable to parse String:" + inputDate + " with format: " + format,ex);
        }
        return null;
    }

    public static String toString(Date date, String format){
        return new SimpleDateFormat(format).format(date);
    }

}
