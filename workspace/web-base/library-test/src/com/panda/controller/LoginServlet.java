package com.panda.controller;

import com.panda.entity.Admin;
import com.panda.entity.Book;
import com.panda.entity.Reader;
import com.panda.service.BookService;
import com.panda.service.LoginService;
import com.panda.service.impl.BookServiceImpl;
import com.panda.service.impl.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @Author Panda
 * @create 2020/7/22 12:11
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private LoginService loginService = new LoginServiceImpl();
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理登录的业务逻辑
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取用户输入的 用户名，密码。
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");

        Object object = loginService.login(username, password, type);
        if (object != null){
            //登录成功：记录session
            HttpSession session = req.getSession();
            //session.setAttribute("reader", reader);
            switch (type){
                case "reader":
                    Reader reader = (Reader) object;
                    session.setAttribute("reader", reader);
                    //跳转到reader首页
                    resp.sendRedirect("/book?page=1");
                    break;
                case "admin":
                    Admin admin = (Admin) object;
                    session.setAttribute("admin", admin);
                    //跳转到admin首页
                    resp.sendRedirect("/admin?method=findAllBorrow&page=1");

                    break;
            }
        }else {
            //登录失败：跳转登录页面
            resp.sendRedirect("login.asp");
        }
    }
}
