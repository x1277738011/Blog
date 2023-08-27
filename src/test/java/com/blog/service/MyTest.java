package com.blog.service;


import com.blog.domain.Blog;
import com.blog.domain.User;
import com.blog.mapper.BlogMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyTest {
    @Autowired
    UserService userService;
    @Test
    public void testSelectUser(){
        System.out.println(userService.getUserByName("zhangsan"));
    }
    @Autowired
    BlogMapper blogMapper;
    @Test
    public void testAddBlog(){
        Blog blog = new Blog();
        blog.setTitle("python");
        blog.setContent("hello python");
        blog.setUid(1);
        System.out.println(blogMapper.insertBlog(blog));
    }
}
