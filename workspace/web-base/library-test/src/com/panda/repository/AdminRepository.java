package com.panda.repository;

import com.panda.entity.Admin;

/**
 * @Author Panda
 * @create 2020/7/22 14:01
 */
public interface AdminRepository {
    public Admin login(String username, String password);

}
