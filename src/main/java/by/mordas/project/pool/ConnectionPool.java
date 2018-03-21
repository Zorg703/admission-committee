package by.mordas.project.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ConnectionPool {
    private final static Logger logger= LogManager.getRootLogger();
    private static ArrayBlockingQueue<DBConnection> connectionsStorage;
    public static ConnectionPool instance;
    private static DBManager manager;
    private static Lock lock=new ReentrantLock();
    private static AtomicBoolean isInstance=new AtomicBoolean(false);
    private static Properties properties;
    private static int POOL_SIZE;
    static {
        manager=new DBManager();
        manager.registerDriver();
        properties=manager.getProperties();
        POOL_SIZE=Integer.parseInt(properties.getProperty("poolSize"));
        connectionsStorage=new ArrayBlockingQueue<DBConnection>(POOL_SIZE);
    }


    private ConnectionPool() {

        initializePool();

    }

    private void initializePool(){
        DBConnection dbConnection;
    for (int i=0;i<POOL_SIZE;i++){
        try {
            Connection connection=DriverManager.getConnection(properties.getProperty("url"),properties);
            dbConnection=new DBConnection(connection);
             connectionsStorage.offer(dbConnection);

        } catch (SQLException e) {
            logger.log(Level.FATAL,"Connection pool can not be initialized");
          throw new RuntimeException();
        }
    }

    }

    public static ConnectionPool getInstance() {
        if(!isInstance.get()) {
            try {
                lock.lock();
                if (!isInstance.get()) {
                    instance = new ConnectionPool();
                    isInstance.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public  DBConnection getConnection(){

            DBConnection connection = null;
            try {
                connection = connectionsStorage.take();
            } catch (InterruptedException e) {
                logger.log(Level.ERROR, "Connection can not be got from queue");
            }
            return connection;


    }
    public void closeConnection(DBConnection connection){
        connectionsStorage.offer(connection);

    }

    public static void closePool(){
        try {
            for (int i=0;i<POOL_SIZE;i++) {
             DBConnection connection= connectionsStorage.take();
             connection.closeConnection(connection);

            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR,"Connection can't do ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
