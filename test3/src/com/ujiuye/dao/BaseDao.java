package com.ujiuye.dao;

import com.ujiuye.utils.ThreadDruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.*;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class BaseDao<T> {

    private DataSource ds = ThreadDruidUtils.getDataSource();
    private QueryRunner qr = new QueryRunner(this.getDs());

    public BaseDao() {
    }

    public BaseDao(DataSource ds) {
        this.ds = ds;
    }

    public DataSource getDs() {
        return ds;
    }

    public void setDs(DataSource ds) {
        this.ds = ds;
    }

    public QueryRunner getQr() {
        return qr;
    }

    public void setQr(QueryRunner qr) {
        this.qr = qr;
    }

    /**
     * 返回单个实体对象，也就是返回结果集的第一条记录
     * @param sql sql语句
     * @param clazz 需要返回的对象的类对象
     * @param param 填充占位符
     * @return T
     */
    public T getBean(String sql, Class<T> clazz, Object...param) {
        T t = null;
        try {
            t = qr.query(sql, new BeanHandler<>(clazz), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    // 支持手动事务
    public T getBean(Connection connection, String sql, Class<T> clazz, Object...param) {

        T t = null;
        try {
            t = qr.query(connection, sql, new BeanHandler<>(clazz), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 返回查询的结果集List（存放的元素是每行的数据封装成的对象），返回多条数据
     * @param sql sql语句
     * @param clazz 需要返回的对象的类对象
     * @param param 填充占位符
     * @return List<T>
     */
    public List<T> getBeanList(String sql, Class<T> clazz, Object...param) {
        List<T> list = null;
        try {
            list = qr.query(sql, new BeanListHandler<>(clazz), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 支持手动事务
    public List<T> getBeanList(Connection connection, String sql, Class<T> clazz, Object...param) {
        List<T> list = null;
        try {
            list = qr.query(connection, sql, new BeanListHandler<>(clazz), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 返回单个数据，主要用于聚合函数
     * @param sql sql语句
     * @param param 填充占位符
     * @return Object
     */
    public Object getScalar(String sql, Object...param) {
        Object ob = null;
        try {
            ob = qr.query(sql, new ScalarHandler<>(), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ob;
    }

    // 支持手动事务
    public Object getScalar(Connection connection, String sql, Object...param) {
        Object ob = null;
        try {
            ob = qr.query(connection, sql, new ScalarHandler<>(), param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ob;
    }

    /**
     * 增删改
     * @return 受影响行数
     */
    public int update(String sql, Object...param) {
        int row = 0;
        try {
            row = qr.update(sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    // 支持手动事务
    public int update(Connection connection, String sql, Object...param) {
        int row = 0;
        try {
            row = qr.update(connection, sql, param);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return row;
    }

    /**
     * 返回查询结果集的第一条数据的对象数组
     * @param sql
     * @param params
     * @return Object[]
     */
    public Object[] getArr(String sql, Object...params) {
        Object[] arr = new Object[0];
        try {
            arr = qr.query(sql, new ArrayHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    // 支持手动
    public Object[] getArr(Connection connection, String sql, Object...params) {
        Object[] arr = new Object[0];
        try {
            arr = qr.query(connection, sql, new ArrayHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return arr;
    }

    /**
     * 把查询到的结果集中的第一条记录作为map返回（列为key）
     * @param sql
     * @param params
     * @return Map<String, Object>
     */
    public Map<String, Object> getMap(String sql, Object...params) {
        Map<String, Object> map = null;
        try {
            map = qr.query(sql, new MapHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    // 支持手动事务

    public Map<String, Object> getMap(Connection connection, String sql, Object...params) {
        Map<String, Object> map = null;
        try {
            map = qr.query(connection, sql, new MapHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 把查询到的结果集中的所有记录的每条数据先放到Map中，再把Map放到List集合中
     * @param sql
     * @param params
     * @return List<Map<String, Object>>
     */
    public List<Map<String, Object>> getMapList(String sql, Object...params) {
        List<Map<String, Object>> list = null;
        try {
            list = qr.query(sql, new MapListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 支持手动事务
    public List<Map<String, Object>> getMapList(Connection connection, String sql, Object...params) {
        List<Map<String, Object>> list = null;
        try {
            list = qr.query(connection, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 把查询到的结果集的每条数据存放在map中，然后把map存放
     * 到另一个map中作为value值，key值为指定的列名
     * @param sql
     * @param columnName 列名
     * @param params
     * @return
     */
    public Map<String, Map<String, Object>> getKeyMap(String sql, String columnName, Object...params) {
        Map<String, Map<String, Object>> map = null;
        try {
            map = qr.query(sql, new KeyedHandler<String>(columnName), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    // 支持手动事务
    public Map<String, Map<String, Object>> getKeyMap(Connection connection, String sql, String columnName, Object...params) {
        Map<String, Map<String, Object>> map = null;
        try {
            map = qr.query(connection, sql, new KeyedHandler<String>(columnName), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 把查询到结果集中的某一列的所有值存放到list中
     * @param sql
     * @param columnName 列名
     * @param params
     * @return List<Object>
     */
    public List<Object> getColumnList(String sql, String columnName, Object...params) {
        List<Object> list = null;
        try {
            list = qr.query(sql, new ColumnListHandler<>(columnName), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 支持手动事务
    public List<Object> getColumnList(Connection connection, String sql, String columnName, Object...params) {
        List<Object> list = null;
        try {
            list = qr.query(connection, sql, new ColumnListHandler<>(columnName), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 把结果集中的每条数据放在数组中，在放到list中
     * @param sql
     * @param params
     * @return
     */
    public List<Object[]> getListArr(String sql, Object...params) {
        List<Object[]> list = null;
        try {
            list = qr.query(sql, new ArrayListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 支持手动事务
    public List<Object[]> getListArr( Connection connection, String sql, Object...params) {
        List<Object[]> list = null;
        try {
            list = qr.query(connection, sql, new ArrayListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
