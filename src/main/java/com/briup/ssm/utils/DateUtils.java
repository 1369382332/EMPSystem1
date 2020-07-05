package com.briup.ssm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
    public static String date2String(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String str = sdf.format(date);
        return str;
    }

    public static Date string2Date(String dateStr,String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch ( ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
