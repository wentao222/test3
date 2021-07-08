package com.ujiuye.utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 并发访问处理获取可以安全获取数据库连接的工具类
 */
public class ThreadDruidUtils {

    private static final ThreadLocal<Connection> th = new ThreadLocal<>();

    public static Connection getThConnection() {
        return th.get();
    }

    /**
     * 获取连接池对象
     * @return 连接池对象
     */
    public static DataSource getDataSource() {
        InputStream in = Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("druid.Properties");
        Properties p = new Properties();
        try {
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            return DruidDataSourceFactory.createDataSource(p);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过连接池对象获取数据库的连接
     * @return 数据库的连接对象
     */
    public static Connection getConnection() throws SQLException {
        // 从本地线程中获取共享变量数据库连接
        Connection conn = th.get();
        if(conn == null) { // 若获取不到
            // 从数据库获取连接存放本地变量中作为变量副本
            th.set(getDataSource().getConnection());
            conn = th.get(); // 并取出返回给调用者
        }
        return  conn;
    }

    /**
     *
     */
    public static void release(Connection connection, Statement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if(statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                th.remove();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
