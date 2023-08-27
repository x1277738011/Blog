package com.blog.service;

import com.blog.domain.Blog;

import java.util.List;

public interface BlogService {
    int deleteBlog(Integer id,Integer uid);
    Integer insertBlog(Blog blog);
    Integer updateBlog(Blog blog);
    Integer updateRcount(Integer aid);
    List<Blog> getAllArt(Integer precount, Integer start);
    Integer getNum(Integer uid);
    List<Blog> getAllDraft(Integer uid);
    Integer getMyDraftCountByUid(Integer uid);
    Integer updateDraft(Integer id, Integer uid);
    List<Blog> getMyList(Integer uid);
    Blog getBlogDetail(Integer id);
    Integer getAlllist();
}
