package com.panda.service.impl;


import com.panda.repository.AdminRepository;
import com.panda.repository.ReaderRepository;
import com.panda.repository.impl.AdminRepositoryImpl;
import com.panda.repository.impl.ReaderRepositoryImpl;
import com.panda.service.LoginService;

/**
 * @Author Panda
 * @create 2020/7/22 12:33
 */
public class LoginServiceImpl implements LoginService {

    private ReaderRepository readerRepository = new ReaderRepositoryImpl();
    private AdminRepository adminRepository = new AdminRepositoryImpl();

    @Override
    public Object login(String username, String password, String type) {
        Object  object = null;
        switch (type){
            case "reader":
                //object = new Reader();
                object = readerRepository.login(username, password);
                break;
            case "admin" :
                //object = new Admin();
                object = adminRepository.login(username, password);
                break;
        }
        return object;
    }
}
