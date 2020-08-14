package com.panda.repository;

import com.panda.entity.Reader;

/**
 * @Author Panda
 * @create 2020/7/22 12:37
 */
public interface ReaderRepository {
    public Reader login(String username,String password);
}
