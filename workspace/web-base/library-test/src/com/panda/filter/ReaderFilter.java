package com.panda.filter;

import com.panda.entity.Reader;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Panda
 * @create 2020/7/23 20:49
 */
@WebFilter("/book")
public class ReaderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //判断用户是否登录
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        Reader reader = (Reader) session.getAttribute("reader");
        if (reader == null) {
            ((HttpServletResponse)servletResponse).sendRedirect("login.jsp");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
