package by.mordas.admissioncommittee.util;

import by.mordas.project.util.DateConverter;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

/***
 Author: Sergei Mordas
 Date: 20.05.2018
 ***/


public class TestDateConverter {
    private DateConverter dateConverter;
    private final static String good_date="2005-02-15T11:15";
    private final static String bad_date="20-02-15T11:15";
    @BeforeMethod
    public void setDateConverter() {
        dateConverter=new DateConverter();
    }

    @Test
    public void testValidateDateGood(){
        LocalDateTime localDateTime=DateConverter.getLocaleDateTime(good_date);
        String date=localDateTime.toString();
        Assert.assertEquals(good_date,date);
    }

    @Test(expectedExceptions = DateTimeParseException.class)
    public void testValidateDateBad(){
        LocalDateTime localDateTime=DateConverter.getLocaleDateTime(bad_date);
    }


}
