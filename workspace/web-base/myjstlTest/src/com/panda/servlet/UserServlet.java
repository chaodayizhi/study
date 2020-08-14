package com.panda.servlet;

import com.panda.entity.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Panda
 * @create 2020/7/18 14:09
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {

    private List<User> list = new ArrayList<>();

    public UserServlet(){
        list.add(new User(11,"zhangsan",90));
        list.add(new User(12,"lisi",80));
        list.add(new User(13,"wangwu",9));
        list.add(new User(14,"zhaoliu",70));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // req.setCharacterEncoding("UTF-8");
        String idstr = req.getParameter("id");
        String name = req.getParameter("name");
        String scorestr = req.getParameter("score");
        Integer id = Integer.parseInt(idstr);
        Integer score = Integer.parseInt(scorestr);
        list.add(new User(id,name,score));
        req.setAttribute("list",list);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("list",list);
        req.getRequestDispatcher("index.jsp").forward(req,resp);
    }
}
