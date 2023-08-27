package com.blog.service;

import com.blog.domain.User;

public interface UserService {

    User getUserByName(String username);
    User getUserById(Integer id);

    boolean insteruser(String username, String password);
}
