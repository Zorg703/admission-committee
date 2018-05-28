package by.mordas.project.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/***
 Author: Sergei Mordas
 Date: 06.04.2018
 ***/
public class ConnectionPool {
    private final static Logger logger= LogManager.getLogger(ConnectionPool.class);
    private static final String URL=DBManager.getProperty("url");
    private static final String PASSWORD=DBManager.getProperty("password");
    private static final String USER=DBManager.getProperty("user");
    private ArrayBlockingQueue<PooledConnection> connectionsStorage;
    private static ConnectionPool instance;
    private static Lock lock=new ReentrantLock();
    private static AtomicBoolean isInstance=new AtomicBoolean(false);
    private static int POOL_SIZE=Integer.parseInt(DBManager.getProperty("poolSize"));

    /**
     * Instantiates a new connection pool.
     */
    private ConnectionPool() {
        if (instance != null) {
            throw new RuntimeException("Reflection is forbidden");
        }
        connectionsStorage=new ArrayBlockingQueue<>(POOL_SIZE);
        initializePool();
    }


    /**
     * Initialization connection pool
     */
    private void initializePool(){
        PooledConnection pooledConnection;
    for (int i=0;i<POOL_SIZE;i++){
        try {
             pooledConnection =new PooledConnection(DriverManager.getConnection(URL, USER,PASSWORD));
             connectionsStorage.offer(pooledConnection);

//        } catch (DAOException e) {
//            logger.log(Level.ERROR, e.getMessage());
        } catch (SQLException e) {
            logger.error(e.getMessage(), e);
        }
    }

    }

    /**
     * Gets the single instance of ConnectionPool.
     *
     * @return single instance of ConnectionPool
     */
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


    /**
     * Gets the connection.
     *
     * @return the connection
     */
    public PooledConnection getConnection(){
            PooledConnection connection = null;
        try {
            connection = connectionsStorage.take();
        } catch (InterruptedException e) {
         logger.log(Level.ERROR,"Can't take connection from connection pool");
        }

        return connection;

    }

    /**
     * Close connection.
     * Return connection in the connections storage.
     *
     * @param connection the connection
     */
    public void closeConnection(PooledConnection connection){
        connectionsStorage.offer(connection);
    }

    /**
     * return pool size
     *
     * @return  int pool size
     */
    public int getPoolSize() {
        return connectionsStorage.size();
    }


    /**
     * Close connection pool.
     */
    public void closePool() {
        try {
            for (int i = 0; i < POOL_SIZE; i++) {
                PooledConnection connection = connectionsStorage.take();
                connection.closeConnection();
            }
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Problems with take connection from connection pool");
        } catch (SQLException e) {
            logger.log(Level.ERROR,"Problems with close connection");
            DBManager.deregisterDriver();
        }
    }
    /* (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
