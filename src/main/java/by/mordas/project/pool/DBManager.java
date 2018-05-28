package by.mordas.project.pool;


import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;


import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/***
 Author: Sergei Mordas
 Date: 06.04.2018
 ***/
public class DBManager {
    private static Logger logger= org.apache.logging.log4j.LogManager.getRootLogger();

    /** The resource bundle. */
    private static ResourceBundle  resourceBundle=ResourceBundle.getBundle("configuration");

    static {
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            logger.log(Level.ERROR,e.getMessage());
        }
    }

    /**
     * Instantiates a new DB manager.
     */
    public DBManager() {

    }

    /**
     * Gets the property  to connect DB
     *
     * @param key the key
     * @return the property
     */
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

    /**
     * Deregister all drivers
     */
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
