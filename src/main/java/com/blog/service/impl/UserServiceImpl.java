package com.blog.service.impl;

import com.blog.domain.User;
import com.blog.mapper.UserMapper;
import com.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public User getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

    @Override
    public boolean insteruser(String username, String password) {
        boolean isSuccess = false;
        try {
            userMapper.insterUser(username,password);
            isSuccess=true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }


}
