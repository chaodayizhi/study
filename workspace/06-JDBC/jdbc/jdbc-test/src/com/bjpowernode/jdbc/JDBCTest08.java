package com.bjpowernode.jdbc;

import java.sql.*;

/**
 * @Author Panda
 * @create 2020/7/8 12:27
 * java中如何操作事务
 *  重点三行代码：
 *      conn.setAutoCommit(false);//开启事务
 *      conn.commit(); //提交事务
 *      conn.rollback();//回滚事务
 */
public class JDBCTest08 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useSSL=true","root","giantpanda");
            /*
                将自动提交值修改为手动提交。
             */
            conn.setAutoCommit(false);//开启事务

            //3、获取数据库操作对象
            String sql = "update t_user set loginName=? where id=?";
            ps = conn.prepareStatement(sql);

            //给？传值
            ps.setString(1,"zhaoliu");
            ps.setInt(2,1);
            int count = ps.executeUpdate();

            //再次给？传值
            ps.setString(1,"zhaoliu");
            ps.setInt(2,2);
            count += ps.executeUpdate();

            System.out.println(count == 2 ? "成功" : "失败");
            conn.commit(); //提交事务
            //5、处理结果集

        } catch (Exception e) {
            if(conn != null){
                try {
                    conn.rollback();//回滚事务
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

            e.printStackTrace();
        }finally{
            //6、释放资源
            if (ps != null){
                try {
                    ps.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
