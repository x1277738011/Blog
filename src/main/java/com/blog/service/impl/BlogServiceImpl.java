package com.blog.service.impl;

import com.blog.common.AppVariable;
import com.blog.config.HTMLUtils;
import com.blog.config.MarkdownToHtmlUtils;
import com.blog.domain.Blog;
import com.blog.mapper.BlogMapper;
import com.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogMapper blogMapper;
    @Override
    public int deleteBlog(Integer id,Integer uid) {
        return blogMapper.deleteBlog(id,uid);
    }
    @Override
    public Integer insertBlog(Blog blog) {
        return blogMapper.insertBlog(blog);
    }

    @Override
    public Integer updateBlog(Blog blog) {

        return blogMapper.updateBlog(blog);
    }

    @Override
    public List<Blog> getAllArt( Integer precount,Integer start) {
        precount = AppVariable.PRE_PAGE_COUNT;
        List<Blog> blogs = blogMapper.getAllArt(precount,start);
        for (Blog blog : blogs) {
            String content = blog.getContent();
            if (blog.getContent().length() > 200) {
                content = content.substring(0, 200);
            }
            //将markdown转换成html
            content = MarkdownToHtmlUtils.markdownToHtmlExtensions(content);
            //去除html标签
            content = HTMLUtils.delHTMLTag(content);
            blog.setContent(content);
        }
        return blogs;
    }


    @Override
    public Integer getNum(Integer uid) {
        return blogMapper.getNum(uid);
    }

    @Override
    public List<Blog> getAllDraft(Integer uid) {
        List<Blog> blogs = blogMapper.getAllDraft(uid);
     for (Blog blog : blogs){
         String content = blog.getContent();
         if (blog.getContent().length() > 200) {
             content = content.substring(0,200);
         }
         //将markdown转换成html
         content = MarkdownToHtmlUtils.markdownToHtmlExtensions(content);
         //去除html标签
         content = HTMLUtils.delHTMLTag(content);
         blog.setContent(content);
        }
        return blogs;
    }

    @Override
    public Integer getMyDraftCountByUid(Integer uid) {
        return blogMapper.getMyDraftCountByUid(uid);
    }

    @Override
    public Integer updateDraft(Integer id, Integer uid) {
        return blogMapper.updateDraft(id, uid);
    }

    @Override
    public List<Blog> getMyList(Integer uid) {
        List<Blog> blogs = blogMapper.getMyList(uid);
        for (Blog blog : blogs){
            String content = blog.getContent();
            if (blog.getContent().length()>200) {
                content = content.substring(0,200);
            }
            //将markdown转换成html
            content = MarkdownToHtmlUtils.markdownToHtmlExtensions(content);
            //去除html标签
            content = HTMLUtils.delHTMLTag(content);
            blog.setContent(content);
        }
        return blogs;
    }

    @Override
    public Blog getBlogDetail(Integer id) {
        return blogMapper.getBlogDetail(id);
    }

    @Override
    public Integer getAlllist() {
        return blogMapper.getAllist();
    }

    @Override
    public Integer updateRcount(Integer id) {
        return blogMapper.updateRcount(id);
    }

}
