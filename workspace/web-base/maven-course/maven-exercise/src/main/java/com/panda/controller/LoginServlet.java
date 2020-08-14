package com.panda.controller;

import com.panda.entity.User;
import com.panda.service.LoginService;
import com.panda.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author Panda
 * @create 2020/8/12 11:54
 */
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();

    /**
     * 处理登录业务
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理字符乱码问题
        //request.setCharacterEncoding("UTF-8");
        //response.setContentType("text/html;charset=utf-8");

        //获取用户名和密码
        String userName = request.getParameter("userName");
        String userPwd = request.getParameter("userPwd");

        //System.out.println(userName+userPwd);
        //response.getWriter().write(userName);

//        PrintWriter pw = response.getWriter();
//        pw.println("/login");
//        pw.println(userName+"-"+userPwd);
//        pw.flush();
//        pw.close();

        User user = loginService.login(userName, userPwd);
        if (user != null) {
            //登录成功
            PrintWriter pw = response.getWriter();
            pw.println("/login");
            pw.println("登录成功！");
            pw.println(userName+"+"+userPwd);
            pw.flush();
            pw.close();
        } else {
            //登录失败
            response.sendRedirect("login.jsp");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
