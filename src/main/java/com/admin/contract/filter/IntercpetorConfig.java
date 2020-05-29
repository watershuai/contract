/*
package com.admin.contract.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class IntercpetorConfig implements WebMvcConfigurer {
    @Autowired
    private RepeatInterceptor repeatInterceptor;


    final String[] notLoginInterceptPaths = {"/admin/login/**", "/index/**", "/register/**"};

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration loginRegistry = registry.addInterceptor(repeatInterceptor);
        // 拦截路径
        loginRegistry.addPathPatterns("/**");//.excludePathPatterns("/view/toLogin","/login","/view/toReg","/loginout");
        // 排除路径
        loginRegistry.excludePathPatterns("/static/css/*.css");
        loginRegistry.excludePathPatterns("/static/js/*.js");
        loginRegistry.excludePathPatterns("/static/images/*");
        loginRegistry.excludePathPatterns("/static/fonts/*");
        loginRegistry.excludePathPatterns("/error");
        ///loginRegistry.excludePathPatterns("/*.html");
        //loginRegistry.excludePathPatterns("/static/*.html");
    }

}
*/
