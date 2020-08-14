package com.panda.servlet;

import com.panda.entity.User;
import net.sf.json.JSONObject;
import netscape.javascript.JSObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Panda
 * @create 2020/7/20 19:40
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(1,"张三",90);
        //将java对象转换成Json格式
        resp.setCharacterEncoding("UTF-8");
        JSONObject jsonObject = JSONObject.fromObject(user);
        resp.getWriter().write(jsonObject.toString());
    }
}
