package com.panda.service;

import com.panda.entity.User;

/**
 * @Author Panda
 * @create 2020/8/12 22:17
 */
public interface LoginService {
    public User login(String userName, String userPwd);
}
