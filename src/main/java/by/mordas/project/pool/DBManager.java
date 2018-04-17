package by.mordas.project.pool;

import by.mordas.project.dao.DAOException;
import by.mordas.project.entity.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;


public class DBManager {
    private static Logger logger= org.apache.logging.log4j.LogManager.getRootLogger();

    private static ResourceBundle  resourceBundle=ResourceBundle.getBundle("configuration");


    public DBManager() {

    }
    public static String getProperty(String key){
        String value;

        try {
                value = resourceBundle.getString(key);

        } catch (MissingResourceException e) {
            logger.log(Level.FATAL, "Resource wasn't found: " + key);
            throw new RuntimeException(e);
        }
        return value;
    }

    public static void deregisterDriver(){
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                DriverManager.deregisterDriver(drivers.nextElement());
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't deregister driver: " + e.getMessage());
        }

    }
}
