package com.panda.controller;

import com.panda.entity.Book;
import com.panda.entity.Borrow;
import com.panda.entity.Reader;
import com.panda.service.BookService;
import com.panda.service.impl.BookServiceImpl;

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
 * @create 2020/7/23 11:18
 */
@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookServiceImpl();

    /**
     * 处理图书业务
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null){
            method = "findAll";
        }
        HttpSession session = req.getSession();
        Reader reader = (Reader) session.getAttribute("reader");

        switch (method) {
            case "findAll":
                String pageStr = req.getParameter("page");
                Integer page = Integer.parseInt(pageStr);
                //获取到图书list
                List<Book> list = bookService.findAll(page);
                //将list传入请求中
                req.setAttribute("list",list);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getPages());
                req.getRequestDispatcher("index.jsp").forward(req,resp);
                break;
            case "addBorrow":
                String bookidStr = req.getParameter("bookid");
                Integer bookid = Integer.parseInt(bookidStr);
                //添加借书请求
                bookService.addBorrow(bookid,reader.getId());
                resp.sendRedirect("/book?method=findAllBorrow&page=1");
                break;
            case "findAllBorrow":
                pageStr = req.getParameter("page");
                page = Integer.parseInt(pageStr);
                //展示当前用户的所有借书记录
                List<Borrow> borrowList = bookService.findAllBorrowByReaderId(reader.getId(),page);
                req.setAttribute("list",borrowList);
                req.setAttribute("dataPrePage",6);
                req.setAttribute("currentPage",page);
                req.setAttribute("pages",bookService.getBorrowPages(reader.getId()));
                req.getRequestDispatcher("borrow.jsp").forward(req,resp);
                break;
        }
    }
}
