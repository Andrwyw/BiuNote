package com.biunote.wyw.biunote.util;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wang on 2014/8/16.
 */
public class TimeUtils {

    public static final String DEFAULT_TIME_FORMAT_STRING = "yyyy-MM-dd HH:mm:ss";

    public static String currentTimeByDefaultFormat(){

        DateFormat format = new SimpleDateFormat(DEFAULT_TIME_FORMAT_STRING);
        Date date = new Date();
        String currentTime = format.format(date);
        return currentTime;
    }

    private TimeUtils(){
    }
}
