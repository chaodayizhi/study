package com.panda.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Panda
 * @create 2020/8/12 12:50
 */
public class CharacterFilter implements Filter {
    public void destroy() {
    }

    /**
     * 过滤器：处理字符乱码问题
     * @param req
     * @param resp
     * @param chain
     * @throws ServletException
     * @throws IOException
     */
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        req.setCharacterEncoding("Utf-8");
        resp.setContentType("text/html;charset=utf-8");
        chain.doFilter(req,resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
