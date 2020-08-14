package com.panda.repository.impl;

import com.panda.entity.Reader;
import com.panda.repository.ReaderRepository;
import com.panda.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Panda
 * @create 2020/7/22 12:39
 */
public class ReaderRepositoryImpl implements ReaderRepository {
    @Override
    public Reader login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader where username=? and password=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Reader reader = null;
        try {
            //预处理sql语句
            statement = connection.prepareStatement(sql);

            //给？传参
            statement.setString(1,username);
            statement.setString(2,password);

            //接受结果集,处理结果集
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                reader = new Reader(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JDBCTools.release(connection,statement,resultSet);
        }

        return reader;
    }
}
