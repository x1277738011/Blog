package com.blog.vo;

import com.blog.domain.User;
import lombok.Data;

@Data
public class UserVO extends User {
    //文章发表个数
    private Integer artCount;

}