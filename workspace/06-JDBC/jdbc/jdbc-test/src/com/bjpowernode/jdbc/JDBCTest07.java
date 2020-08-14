package com.bjpowernode.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author Panda
 * @create 2020/7/7 19:27
 *  1.解决SQL注入问题？
 *      只要用户提供的信息不参与SQL语句的编译过程，问题就解决了。
 *      即使用户提供的信息中含有SQL语句的关键字，但也没有参与编译，不起作用。
 *      如何让用户信息不参与编译？ 要使用java.sql.PreparedStatement
 *      PreparedStatement接口继承了java.sql.Statement
 *      PreparedStatement属于预编译的数据库操作对象。
 *
 *
 */

public class JDBCTest07 {
    public static void main(String[] args) {
        //初始化一个界面
        Map<String,String> userLoginInfo = initUI();
        //验证用户名和密码
        boolean loginSuccess = login(userLoginInfo);
        //最后输出结果
        System.out.println(loginSuccess ? "登陆成功" : "登录失败");

    }

    /**
     * 用户登录
     * @param userLoginInfo 用户登录信息
     * @return false 失败，true 成功。
     */
    private static boolean login(Map<String, String> userLoginInfo) {
        //做标记
        boolean loginSuccess = false ;
        //单独定义变量
        String loginName = userLoginInfo.get("loginName");
        String loginPwd = userLoginInfo.get("loginPwd");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useSSL=true","root","giantpanda");
            //3、获取预编译的数据库操作对象
            String sql = "select * from t_user where loginName=? and loginPwd=? ";
            //? 为占位符
            ps = conn.prepareStatement(sql);
            // 给占位符传值，下标从1开始
            ps.setString(1,loginName);
            ps.setString(2,loginPwd);
            //4、执行SQL
            rs = ps.executeQuery();
            //5、处理结果集
            if (rs.next()) loginSuccess = true;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //6、释放资源
            if (rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
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
        return loginSuccess;
    }

    /**
     * 初始化用户界面
     * @return 用户输入的用户名和密码等信息。
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);

        System.out.print("用户名：");
        String loginName = s.nextLine();

        System.out.print("密码：");
        String loginPwd = s.nextLine();

        Map<String,String> userLoginInfo = new HashMap<>();
        userLoginInfo.put("loginName",loginName);
        userLoginInfo.put("loginPwd",loginPwd);

        return userLoginInfo;
    }
}
