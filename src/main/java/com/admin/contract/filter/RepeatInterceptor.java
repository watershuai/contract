package com.admin.contract.filter;

import com.admin.contract.dto.User;
import com.admin.contract.utils.ResponseBean;
import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

@Component
public class RepeatInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        if (o instanceof ResourceHttpRequestHandler) {
            return true;
        }

        String basePath = request.getContextPath();
        String path = request.getRequestURI();
        if (!doLoginInterceptor(path, basePath)) {//是否进行登陆拦截
            return true;
        }
        if (!doLoginIn(path, basePath)){
            return false;
        }
        int flag=0;

        String token=request.getParameter("token");
        if (StringUtils.isEmpty(token)){
            flag=1;
        }else {
            User user=(User) request.getSession().getAttribute(token);
            if (user == null){
                flag=1;
            }
        }
        if (flag ==1){
            response.setStatus(HttpServletResponse.SC_OK);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json; charset=utf-8");
            ResponseBean responseBean=new ResponseBean<String>(201,"登录已过期,请重新登录");
            PrintWriter out = null ;
            out = response.getWriter();
            out.write(JSON.toJSONString(responseBean));
            out.flush();
            out.close();
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }



    private boolean doLoginInterceptor(String path,String basePath){
        path = path.substring(basePath.length());
        Set<String> notLoginPaths = new HashSet<>();
        //设置不进行登录拦截的路径：登录注册和验证码
        //notLoginPaths.add("/");
        ///notLoginPaths.add("/index");
        notLoginPaths.add("/login");
        notLoginPaths.add("/toLogin");
        //notLoginPaths.add("/register");
        notLoginPaths.add("/");
        notLoginPaths.add(".js");
        notLoginPaths.add(".css");
        notLoginPaths.add(".jpg");
        notLoginPaths.add(".png");
        //notLoginPaths.add("/sys/logout");
        //notLoginPaths.add("/loginTimeout");
        if(notLoginPaths.contains(path)) {
            return false;
        }else if (path.endsWith(".js")){
            return false;
        }else if (path.endsWith(".css")){
            return false;
        }else if (path.endsWith(".jpg")){
            return false;
        }else if (path.endsWith(".png")){
            return false;
        } else {
            return true;
        }
    }
    private boolean doLoginIn(String path,String basePath){
        path = path.substring(basePath.length());
        Set<String> notLoginPaths = new HashSet<>();
        //设置不进行登录拦截的路径：登录注册和验证码
        //notLoginPaths.add("/");
        ///notLoginPaths.add("/index");
        notLoginPaths.add("/error");
        if(notLoginPaths.contains(path)) {
            return false;
        }
       if (path.endsWith(".js")){
            return false;
        }else if (path.endsWith(".css")){
            return false;
        }else if (path.endsWith(".jpg")){
            return false;
        }else if (path.endsWith(".png")){
            return false;
        } else {
            return true;
        }
    }


}
