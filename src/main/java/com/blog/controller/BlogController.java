package com.blog.controller;

import com.blog.common.AjaxResult;
import com.blog.common.AppVariable;
import com.blog.common.UserSessionUtils;
import com.blog.domain.Blog;
import com.blog.domain.User;
import com.blog.service.BlogService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;
    @RequestMapping("/allart")
    public AjaxResult getAllArt(Integer precount,Integer start) {
        precount = AppVariable.PRE_PAGE_COUNT;
        if (AppVariable.PRE_PAGE_COUNT < 0) {
            AppVariable.PRE_PAGE_COUNT = 1;
        }
        //start表明现在是第几页，第一页时start=0
        if (start == null || start < 0) {
            start = 0;
        }
        start = start * AppVariable.PRE_PAGE_COUNT;
        return AjaxResult.success(blogService.getAllArt(precount,start));
    }
    @RequestMapping("/lastnum")
    public AjaxResult getNum() {
        Integer count = blogService.getAlllist();
//        if (count <= AppVariable.PRE_PAGE_COUNT) {
//            return AjaxResult.success(0);
//        }
        Integer tmp = count % AppVariable.PRE_PAGE_COUNT;
        Integer res = count / AppVariable.PRE_PAGE_COUNT;
        if (tmp == 0 && res != 0) {
            res -= 1;
        }
        return AjaxResult.success(res);
    }
    @PostMapping("/detail")
    public AjaxResult getBlogDetail(HttpServletRequest request,Integer id){
        if (id == null || id<0) {
            return AjaxResult.fail(-1,"非法访问");
        }
        Blog blog = blogService.getBlogDetail(id);
        if (blog == null) {
            return AjaxResult.success(null);
        }
        if (blog.getState() == 1) {
            return AjaxResult.success(blog);
        }else if (blog.getState() == 0) {
            //未发布的草稿
            User user = UserSessionUtils.getSessUser(request);
            //检验登录用户是否合法
            if (user == null || user.getId() <=0) {
                return AjaxResult.success(null);
            }
            //检查用户是否为草稿的拥有者
            if (Objects.equals(blog.getUid(),user.getId())) {
                return AjaxResult.success(blog);
            }
            return AjaxResult.success(null);
        }
        return AjaxResult.fail(-1,"非法访问");
    }
    @RequestMapping("/uprcount")
    public AjaxResult updateRcount(Integer id) {
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "非法参数");
        }
        return AjaxResult.success(blogService.updateRcount(id));
    }

    /**
     * 展示我的博客
     * @param request
     * @return
     */
    @RequestMapping("/mylist")
    public AjaxResult getMyList(HttpServletRequest request){
        User user = UserSessionUtils.getSessUser(request);
        if (user == null || user.getId() <= 0) {
            return AjaxResult.fail(-1,"非法访问");
        }
        List<Blog> list = blogService.getMyList(user.getId());
        return AjaxResult.success(list);
    }


    /**
     * 添加博客
     * @param request
     * @param blog
     * @return
     */
    @RequestMapping("/add")
    public AjaxResult insertBlog(HttpServletRequest request,Blog blog){
        //非空校验
        if (blog == null || !StringUtils.hasLength(blog.getTitle()) || !StringUtils.hasLength(blog.getContent())) {
            return AjaxResult.fail(-1,"非法访问!");
        }
        User user = UserSessionUtils.getSessUser(request);
        if (user == null || user.getId() <= 0) {
            return AjaxResult.fail(-1,"非法访问!");
        }
        blog.setUid(user.getId());
        blog.setState(1);
        return AjaxResult.success(blogService.insertBlog(blog));
    }

    /**
     * 修改
     * @param request
     * @param blog
     * @return
     */
    @RequestMapping("/update")
    public AjaxResult updateBlog(HttpServletRequest request,Blog blog){
        //非空校验
        if (blog == null ||
                !StringUtils.hasLength(blog.getTitle()) ||
                !StringUtils.hasLength(blog.getContent()) ||
                blog.getId() <= 0
        ) {
            return AjaxResult.fail(-1,"非法访问!");
        }
        User user = UserSessionUtils.getSessUser(request);
        if (user == null || user.getId() <=0) {
            return AjaxResult.fail(-2,"非法访问!");
        }
        blog.setUid(user.getId());
        return AjaxResult.success(blogService.updateBlog(blog));
    }

    /**
     * 提交草稿
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("subdraft")
    public AjaxResult submitDraft(HttpServletRequest request,Integer id){
        User user = UserSessionUtils.getSessUser(request);
        if (user == null || user.getId() <= 0) {
            return AjaxResult.fail(-1, "非法访问");

        }
        return AjaxResult.success(blogService.updateDraft(id,user.getId()));
    }

    /**
     * 保存草稿
     * @param request
     * @param blog
     * @return
     */
    @RequestMapping("/addsave")
    public AjaxResult saveDraft(HttpServletRequest request,Blog blog){
        User user = UserSessionUtils.getSessUser(request);
        if (user == null || user.getId() <= 0) {
            return AjaxResult.fail(-1, "非法访问");

        }
        blog.setState(0);
        System.out.println("------------"+blog.getState()+"--------------");
        blog.setUid(user.getId());
        Integer count = blogService.insertBlog(blog);
        if (count <= 0){
            return AjaxResult.fail(-1, "保存失败");
        }
        return AjaxResult.success("保存成功！");
    }

    /**
     * 获取草稿
     * @param request
     * @return
     */
    @RequestMapping("/mydraftlist")
    public AjaxResult getAllDraft(HttpServletRequest request){
        User user = UserSessionUtils.getSessUser(request);
        if (user == null || user.getId() <= 0) {
            return AjaxResult.fail(-1, "非法访问");

        }
        return AjaxResult.success(blogService.getAllDraft(user.getId()));
    }
    @RequestMapping("/del")
    public AjaxResult deleteBlog(HttpServletRequest request,Integer id){
        User user = UserSessionUtils.getSessUser(request);
        if (user == null || user.getId() <= 0) {
            return AjaxResult.fail(-1, "非法访问");

        }
        if (id != null && id > 0) {
            return AjaxResult.success(blogService.deleteBlog(id, user.getId()));
        }
        return AjaxResult.fail(-1, "非法访问");
    }
}
