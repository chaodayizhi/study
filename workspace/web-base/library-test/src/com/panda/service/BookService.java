package com.panda.service;

import com.panda.entity.Book;
import com.panda.entity.Borrow;

import java.util.List;

/**
 * @Author Panda
 * @create 2020/7/22 17:30
 */
public interface BookService {
    public List<Book> findAll(int page);

    public int getPages();

    public void addBorrow(Integer bookid, Integer readerid);

    public List<Borrow> findAllBorrowByReaderId(Integer id, Integer page);

    public int getBorrowPages(Integer readerId);

    public List<Borrow> findAllBorrowByState(Integer state, Integer page);

    public int getBorrowPagesByState(Integer state);

    public void handleBorrow(Integer borrowId, Integer state, Integer adminId);


}