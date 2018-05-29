package by.mordas.project.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/***
 Author: Sergei Mordas
 Date: 20.04.2018
 ***/
public class DateConverter {

    private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Gets the time stamp from locale date time
     *
     * @param localDateTime the local date time
     * @return the time stamp
     */
    public static Timestamp getTimestamp(LocalDateTime localDateTime){
        return Timestamp.valueOf(localDateTime);
    }

    /**
     * Gets local date time on pattern from string
     *
     * @param dateTime the date time
     * @return the locale date time
     */
    public static LocalDateTime getLocaleDateTime(String dateTime){
        dateTime=dateTime.replace('T',' ');
        return LocalDateTime.parse(dateTime,formatter);
    }


}
