package by.mordas.project.pool;

import org.apache.logging.log4j.Level;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

public class DBManager {
   /*private final String URL;
   private final String NAME;
   private final String PASSWORD;
   private final int POOL_SIZE;*/
   private Properties properties;
   private final String PROPERTIES_PATH="E:\\EPAMLabs2\\admissioncommittee\\src\\main\\resources\\config.properties";

    public Properties getProperties() {
        return properties;
    }

    public DBManager() {
        properties = new Properties();
        readProperties();
        properties.put("autoReconnect", "true");
        properties.put("useUnicode", "true");
        properties.put("characterEncoding", "UTF-8");
    }
    public void readProperties(){
        try(FileInputStream fileInputStream=new FileInputStream(PROPERTIES_PATH)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void registerDriver() {

        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deregisterDriver(){
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                DriverManager.deregisterDriver(drivers.nextElement());
            }
        } catch (SQLException e) {
           // logger.log(Level.ERROR, "Can't deregister driver: " + e.getMessage());
        }

    }
}
