package com.panda.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Panda
 * @create 2020/7/17 20:53
 */
public class LoginServlet extends HttpServlet {
    //成员变量

    private String myusername;
    private String mypassword;

    /**
     * 获取默认数据
     * @param config ：web.xml内的数据
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        this.myusername = config.getInitParameter("username");
        this.mypassword = config.getInitParameter("password");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username.equals(myusername) && password.equals(mypassword)){
            //成功
            HttpSession session = req.getSession();
//            req.setAttribute("username",username);
//            req.getRequestDispatcher("welcome.jsp").forward(req,resp);
            /*
                登录成功，使用session记录
             */
            session.setAttribute("username",username);
            resp.sendRedirect("welcome.jsp");
        }else{
            //失败
            //跳转到登录界面
            resp.sendRedirect("login.jsp");
        }
    }
}
