package me.uwu.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeUtils {

    public static String dateAndTime(){

        return " at " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(Calendar.getInstance().getTime());

    }
}
