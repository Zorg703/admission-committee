package by.mordas.admissioncommittee.pool;

import by.mordas.project.pool.ConnectionPool;
import by.mordas.project.pool.PooledConnection;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestConnectionPool {
    @Test
    public void getInstanceBySize() throws Exception {
        int expectedSize = 30;
        int actualSize = ConnectionPool.getInstance().getPoolSize();
        Assert.assertEquals(expectedSize, actualSize);
    }

    @Test
    public void getInstance() throws Exception {
        Assert.assertNotNull(ConnectionPool.getInstance());
    }

    @Test
    public void getConnection() throws Exception {
        PooledConnection connection = ConnectionPool.getInstance().getConnection();
        Assert.assertNotNull(connection);
        if (connection != null) {
            connection.close();
        }
    }
}
