package com.blog.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

/**
 *     id int primary key auto_increment,
 *     title varchar(100) not null,
 *     content text not null,
 *     createtime timestamp default current_timestamp,
 *     updatetime timestamp default current_timestamp,
 *     uid int not null,
 *     rcount int not null default 1,
 *     `state` int default 1,
 */
@Data
public class Blog {
    private Integer id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createtime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime updatetime;
    private Integer uid;
    private Integer rcount;
    private Integer state;
}
