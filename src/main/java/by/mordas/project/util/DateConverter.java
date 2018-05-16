package by.mordas.project.util;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateConverter {
    private static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static Timestamp getTimestamp(LocalDateTime localDateTime){
        return Timestamp.valueOf(localDateTime);
    }
    public static LocalDateTime getLocaleDateTime(String dateTime){
        return LocalDateTime.parse(dateTime,formatter);
    }


}
