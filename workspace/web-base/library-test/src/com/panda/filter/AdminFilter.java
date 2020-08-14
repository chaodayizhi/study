package com.panda.filter;

import com.panda.entity.Admin;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Author Panda
 * @create 2020/7/24 17:50
 */
@WebFilter("/admin")
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //判断管理员是否登录
        HttpSession session = ((HttpServletRequest)servletRequest).getSession();
        Admin admin = (Admin)session.getAttribute("admin");
        if (admin == null) {
            ((HttpServletResponse)servletResponse).sendRedirect("login.jsp");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
