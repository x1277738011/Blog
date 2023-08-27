package com.blog.common;

import com.blog.domain.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UserSessionUtils {
    public static User getSessUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute(AppVariable.USER_SESSION_KEY) != null) {
            return (User) session.getAttribute(AppVariable.USER_SESSION_KEY);
        }
        return null;
    }
}
