package com.blog.mapper;

import com.blog.domain.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

//int addUser(UserInfo userInfo);
    @Select("select * from user where username=#{username}")
    User getUserByName(String username);
    @Select("select * from user where id=#{id}")
    User getUserById(Integer id);

    @Insert("insert into user(username,password) value(#{username},#{password})")
    void insterUser(String username, String password);
}
