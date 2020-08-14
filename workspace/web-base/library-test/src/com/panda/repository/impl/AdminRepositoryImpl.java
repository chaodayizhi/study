package com.panda.repository.impl;

import com.panda.entity.Admin;
import com.panda.entity.Reader;
import com.panda.repository.AdminRepository;
import com.panda.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author Panda
 * @create 2020/7/22 14:03
 */
public class AdminRepositoryImpl implements AdminRepository {
    @Override
    public Admin login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookadmin where username=? and password=?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Admin admin = null;
        try {
            //预处理sql语句
            statement = connection.prepareStatement(sql);

            //给？传参
            statement.setString(1,username);
            statement.setString(2,password);

            //接受结果集,处理结果集
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                admin = new Admin(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            //释放资源
            JDBCTools.release(connection,statement,resultSet);
        }

        return admin;
    }

}
