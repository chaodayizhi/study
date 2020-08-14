package com.panda.repository.impl;

import com.panda.entity.User;
import com.panda.repository.UserRepository;
import com.panda.utils.JDBCTools;

import java.sql.*;

/**
 * @Author Panda
 * @create 2020/8/12 20:18
 */
public class UserRepositoryImpl implements UserRepository {

    /**
     * 处理登录
     *
     * @param userName
     * @param userPwd
     * @return
     */
    @Override
    public User login(String userName, String userPwd) {
        Connection conn = JDBCTools.getConnection();
        String sql = "SELECT * FROM  users  where username=? AND userpwd=?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, userName);
            preparedStatement.setString(2, userPwd);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String Name = resultSet.getString(2);
                String Pwd = resultSet.getString(3);
                user = new User(id, Name, Pwd);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(conn, preparedStatement, resultSet);
        }
        return user;
    }


    /**
     * 纯 JDBC 连接数据库 配置 mysql connection 依赖
     */
    public void jdbc_Connection_Test(){
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");

            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_maven?useSSL=true","root","giantpanda");

            //3、获取预编译的数据库操作对象
            String sql = "SELECT * FROM  users  where id=?";
            preparedStatement = conn.prepareStatement(sql);
            // ？为占位符，给占位符传参: (下标从1开始，参数值)
            preparedStatement.setInt(1,20);

            //4、执行sql
            resultSet = preparedStatement.executeQuery(); //执行查询
            //preparedStatement.executeUpdate(); //执行对表的修改

            //5、处理结果集
            User user = null;
            if (resultSet.next()) {
                int id = resultSet.getInt(1);
                String userName = resultSet.getString(2);
                String userPwd = resultSet.getString(3);
                user = new User(id, userName, userPwd);
            }
            System.out.println(user);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            //6、释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
