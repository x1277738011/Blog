package com.blog.config;

import com.blog.common.AppVariable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        if (session != null && session.getAttribute(AppVariable.USER_SESSION_KEY) != null) {
            return true;
        }
        response.sendRedirect("/blog/login.html");
        return false;
    }
}
