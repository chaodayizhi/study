package com.panda.service.impl;

import com.panda.entity.User;
import com.panda.repository.UserRepository;
import com.panda.repository.impl.UserRepositoryImpl;
import com.panda.service.LoginService;

/**
 * @Author Panda
 * @create 2020/8/12 22:19
 */
public class LoginServiceImpl implements LoginService {
    private UserRepository userRepository = new UserRepositoryImpl();

    @Override
    public User login(String userName, String userPwd) {
        return userRepository.login(userName, userPwd);
    }
}
