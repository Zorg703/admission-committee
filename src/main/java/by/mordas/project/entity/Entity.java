package by.mordas.project.entity;


import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Entity implements Serializable{
    public static void main(String[] args) {
        String date="2018-05-12T05:05";
        String date3=date.replace('T',' ');
        System.out.println(date3);

        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime=LocalDateTime.parse(date3,formatter);
        System.out.println(localDateTime);





    }
}
