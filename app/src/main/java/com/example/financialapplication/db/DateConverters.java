package com.example.financialapplication.db;

import com.google.android.gms.common.util.NumberUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import androidx.room.TypeConverter;

public class DateConverters {
    @TypeConverter
    public static Calendar fromTimestamp(Long value) {
        if(value == null) {
            return null;
        }
        Calendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(value * 1000);
        return calendar;
    }

    @TypeConverter
    public static Long dateToTimestamp(Calendar calendar) {
        return calendar == null ? null : calendar.getTimeInMillis()/1000;
    }

}
