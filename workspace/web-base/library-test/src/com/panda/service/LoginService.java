package com.panda.service;

import com.panda.entity.Reader;

/**
 * @Author Panda
 * @create 2020/7/22 12:26
 */
public interface LoginService {
    public Object login(String username,String password,String type);
}
