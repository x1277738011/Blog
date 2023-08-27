package com.blog.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**")
                .excludePathPatterns("/editor.md/**")
                .excludePathPatterns("/img/**")
                .excludePathPatterns("/js/**")
                .excludePathPatterns("/blog/login.html")
                .excludePathPatterns("/reg.html")
                .excludePathPatterns("/blog/blog_content.html")
                .excludePathPatterns("/blog/blog_list.html")
                .excludePathPatterns("/blog/regist.html")
                .excludePathPatterns("/pic/getcheck")
                .excludePathPatterns("/blog/lastnum")
                .excludePathPatterns("/blog/allart")
                .excludePathPatterns("/blog/uprcount")
                .excludePathPatterns("/blog/detail")
                .excludePathPatterns("/blog/getuser")
                .excludePathPatterns("/blog/login")
                .excludePathPatterns("/blog/reg")
//                .excludePathPatterns("/blog/showuser")
                .excludePathPatterns("/blog/del")
                .excludePathPatterns("/blog/mylist")
                .excludePathPatterns("/blog/update")
                .excludePathPatterns("/blog/regist");
    }
}
