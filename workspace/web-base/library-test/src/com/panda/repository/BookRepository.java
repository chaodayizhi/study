package com.panda.repository;

import com.panda.entity.Book;

import java.util.List;

/**
 * @Author Panda
 * @create 2020/7/22 18:08
 */
public interface BookRepository {
    public List<Book> findAll(int index, int limit);
    public int count();
}
