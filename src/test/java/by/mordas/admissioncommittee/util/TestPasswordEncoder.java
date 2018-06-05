package by.mordas.admissioncommittee.util;

import by.mordas.project.util.PasswordEncoder;
import org.testng.Assert;
import org.testng.annotations.Test;

/***
 Author: Sergei Mordas
 Date: 20.05.2018
 ***/

public class TestPasswordEncoder {
    private static String password="Admin1";
    private static String encodePassword="080-48107-7764-11750-97-87-96-9097-22112-76";

    @Test
    public void testEncodePassword(){
        String pass= PasswordEncoder.encodePassword(password);
        Assert.assertEquals(encodePassword,pass);
    }
}
