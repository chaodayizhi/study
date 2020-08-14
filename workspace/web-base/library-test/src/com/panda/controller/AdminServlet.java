package com.panda.controller;

import com.panda.entity.Admin;
import com.panda.entity.Borrow;
import com.panda.service.BookService;
import com.panda.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Panda
 * @create 2020/7/24 16:38
 */
@WebServlet("/admin")
public class AdminServlet extends HttpServlet {
    BookService bookService = new BookServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if(method == null) {
            method = "findAllBorrow";
        }
        switch (method) {
            case "findAllBorrow":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                //Model
                List<Borrow> list = bookService.findAllBorrowByState(0,page);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPagesByState(0));
                req.getRequestDispatcher("admin.jsp").forward(req,resp);
                break;
            case "handle":
                HttpSession session = req.getSession();
                Admin admin = (Admin) session.getAttribute("admin");
                String borrowIdStr = req.getParameter("id");
                Integer borrowId = Integer.parseInt(borrowIdStr);
                String stateStr = req.getParameter("state");
                Integer state = Integer.parseInt(stateStr);
                bookService.handleBorrow(borrowId, state, admin.getId());
                pageStr = req.getParameter("page");
                if (pageStr == null){ pageStr = "1";}
                page = Integer.parseInt(pageStr);
                if(state == 1 || state == 2){
                    resp.sendRedirect("/admin?page="+page);
                }
                if(state == 3){
                    resp.sendRedirect("/admin?method=getBorrowed&page="+page);
                }
                break;
            case "getBorrowed":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                //Model
                list = bookService.findAllBorrowByState(1, page);
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPagesByState(1));
                req.getRequestDispatcher("return.jsp").forward(req,resp);
                break;
        }
    }
}
