package by.mordas.project.pool;

import by.mordas.project.dao.DAOException;
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
    private static final String URL=DBManager.getProperty("url");
    private static final String PASSWORD=DBManager.getProperty("password");
    private static final String USER=DBManager.getProperty("user");
    private static ArrayBlockingQueue<DBConnection> connectionsStorage;
    private static ConnectionPool instance;
    private static Lock lock=new ReentrantLock();
    private static AtomicBoolean isInstance=new AtomicBoolean(false);
    private static int POOL_SIZE=Integer.parseInt(DBManager.getProperty("poolSize"));

    private ConnectionPool() {
        connectionsStorage=new ArrayBlockingQueue<>(POOL_SIZE);
        initializePool();
    }

    private void initializePool(){
        DBConnection dbConnection;
    for (int i=0;i<POOL_SIZE;i++){
        try {
             DriverManager.registerDriver(new com.mysql.jdbc.Driver());
             dbConnection=new DBConnection(DriverManager.getConnection(URL, USER,PASSWORD));

             connectionsStorage.offer(dbConnection);

//        } catch (DAOException e) {
//            logger.log(Level.ERROR, e.getMessage());
        } catch (SQLException e) {
            logger.log(Level.ERROR, e.getMessage());
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
         logger.log(Level.ERROR,"Can't take connection from connection pool");
        }

        return connection;

    }

    public void closeConnection(DBConnection connection){
        connectionsStorage.offer(connection);
    }

    public static void closePool() {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                DBConnection connection = connectionsStorage.take();
                connection.closeConnection();
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Problems with take connection from connection pool");
        } catch (SQLException e) {
            logger.log(Level.ERROR,"Problems with close connection");
            DBManager.deregisterDriver();
        }
    }

}
