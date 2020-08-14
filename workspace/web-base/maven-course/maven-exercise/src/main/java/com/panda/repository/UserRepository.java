package com.panda.repository;

import com.panda.entity.User;

/**
 * @Author Panda
 * @create 2020/8/12 20:14
 */
public interface UserRepository {

    /**
     * 处理登录
     * @param userName
     * @param userPwd
     * @return
     */
    public User login(String userName, String userPwd);
}
