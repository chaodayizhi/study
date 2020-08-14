package com.bjpowernode.jdbc;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @Author Panda
 * @create 2020/7/7 19:27
 */
/*
    实现功能：
    1、需求：模拟用户登录功能的实现
    2、业务描述：
        程序运行的时候，提供一个输入的入口，可以让用户输入用户名和密码
        用户输入用户名和密码之后，提交信息，java程序搜集到用户信息
        java程序连接到数据库验证用户名和密码的合法性。
        合法：显示登录成功。
        不合法：显示登录失败。
    3、数据的准备：
        test.sql  文件
    4、当前程序存在的问题：
        用户名： a
        密码： a' or '1'='1
        这种现象被称为SQL注入

 */
public class JDBCTest06 {
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
        boolean loginSuccess = false;
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            //1、注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useSSL=true","root","giantpanda");
            //3、获取数据库操作对象
            stmt = conn.createStatement();
            //4、执行SQL
            String sql = "select * from t_user where loginName='"+userLoginInfo.get("loginName")+"' and loginPwd='"+userLoginInfo.get("loginPwd")+"'";
            //以上正好完成了sal的拼接，
            //正好将用户提供的“非法信息“编译进去，导致原sql语句的含义发生扭曲。

            rs = stmt.executeQuery(sql);
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
            if (stmt != null){
                try {
                    stmt.close();
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
