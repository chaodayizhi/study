package com.panda.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Author Panda
 * @create 2020/7/22 12:48
 */
public class JDBCTools {
    private static DataSource dataSource;
    static{
        dataSource = new ComboPooledDataSource("testc3p0");
    }

    /**
     * 返回数据库链接 connection
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * 释放资源
     * @param conn
     * @param statement
     * @param resultset
     */
    public static void release(Connection conn, Statement statement, ResultSet resultset){
        try {
            if (resultset != null){
                resultset.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (statement != null){
                statement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
