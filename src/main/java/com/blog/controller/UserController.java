package com.blog.controller;

import com.blog.common.AjaxResult;
import com.blog.common.AppVariable;
import com.blog.common.UserSessionUtils;
import com.blog.domain.Blog;
import com.blog.domain.User;
import com.blog.mapper.UserMapper;
import com.blog.service.BlogService;
import com.blog.service.UserService;

import javax.servlet.http.HttpServletRequest;

import com.blog.vo.UserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author 12777
 */
//在服务器端支持跨域访问
@RestController  //如果本类中全部都是ajax请求,则使用此注解,方法上的@ResponseBody可不写
@RequestMapping("/blog")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BlogService blogService;

    //    @PostMapping("/login")
//    public Boolean selectUser(String e, String password){
//        return userService.selectUser(e, password);
//    }
    @PostMapping("/login")
    public AjaxResult login(HttpServletRequest request, String username, String password) {
        //非空校验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return AjaxResult.fail(-1, "非法参数");
        }
        User user = userService.getUserByName(username);
        if (user != null && user.getId() > 0) {
            if (password.equals(user.getPassword())) {
                HttpSession session = (HttpSession) request.getSession();
                session.setAttribute(AppVariable.USER_SESSION_KEY, user);
                //返回前端前隐藏密码
                user.setPassword("");
                return AjaxResult.success(user);
            }
        }
        return AjaxResult.success(0, null);

    }

    @RequestMapping("/getuser")
    @ResponseBody
    public AjaxResult getUser(Integer id) {
        System.out.println(id);
        if (id == null || id <= 0) {
            return AjaxResult.fail(-1, "非法参数");
        }
        User user = userService.getUserById(id);
        user.setPassword("");
        Integer count = blogService.getNum(id);
        UserVO userVO = new UserVO();
        //把user付给userVO
        BeanUtils.copyProperties(user, userVO);
        userVO.setArtCount(count);
        return AjaxResult.success(userVO);
    }

    @RequestMapping("/showuser")
    public AjaxResult showUser(HttpServletRequest request) {
        User user = UserSessionUtils.getSessUser(request);
        if (user == null) {
            return AjaxResult.fail(-1, "非法请求");

        }
        Integer count = blogService.getNum(user.getId());
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);
        userVO.setArtCount(count);
        return AjaxResult.success(userVO);
    }

    /**
     * 注销
     *
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public AjaxResult logOut(HttpSession session) {
        session.removeAttribute(AppVariable.USER_SESSION_KEY);
        return AjaxResult.success(1);
    }

    @RequestMapping("/regist")
    public AjaxResult regist(HttpServletRequest request, String username, String password) {
        //非空校验
        if (!StringUtils.hasLength(username) || !StringUtils.hasLength(password)) {
            return AjaxResult.fail(-1, "非法参数");
        }

        boolean insteruser = userService.insteruser(username, password);
        if (insteruser) {
            return AjaxResult.success(0, "注册成功");
        }
        return AjaxResult.fail(500,"注册失败");
    }

}
