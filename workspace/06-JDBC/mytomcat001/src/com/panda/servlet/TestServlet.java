package com.panda.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Panda
 * @create 2020/7/17 16:52
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.getWriter().write("<html>\n" +
                "  <head>\n" +
                "    <title>Title</title>\n" +
                "  </head>\n" +
                "  <body>\n" +
                "  <h1>111111</h1>\n" +
                "  \n" +
                "  </body>\n" +
                "</html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
