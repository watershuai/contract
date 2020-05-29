package com.admin.contract.controller;

import com.admin.contract.dto.User;
import com.admin.contract.service.UserService;
import com.admin.contract.utils.ResponseBean;
import com.admin.contract.utils.RundomUtils;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author 13
 * @qq交流群 796794009
 * @email 2449207463@qq.com
 * @link https://github.com/newbee-ltd
 */
@Controller
@RequestMapping()
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String tologin() {
        return "/login";
    }

    @GetMapping({"/test"})
    public String test() {
        return "admin/test";
    }


    @GetMapping({"", "/","/index", "/index.html"})
    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return "redirect:login.html";
    }

    @ResponseBody
    @PostMapping(value = "/toLogin")
    public ResponseBean<Object> login(@RequestParam("userName") String userName,
                        @RequestParam("password") String password,
                        HttpServletRequest request) {
        if (StringUtils.isEmpty(userName) || StringUtils.isEmpty(password)) {
            return new ResponseBean<Object>(202,"用户名或密码不能为空");
        }
        User user = userService.login(userName, password);
        if (user == null){
            return new ResponseBean<Object>(203,"账号密码不正确");
        }
        if (user.getStatus() == 0){
            return new ResponseBean<Object>(205,"该账号已禁用");
        }
        String token= RundomUtils.getToken();
        request.getSession().setAttribute(token, user);
        //session过期时间设置为1800秒 即半小时
        request.getSession().setMaxInactiveInterval(30 * 60);
        return new ResponseBean<Object>(200,token,user);
    }
    @PostMapping("/register")
    @ResponseBody
    public ResponseBean<String> register(HttpServletRequest request){
        User user=new User();
        user.setPassword(request.getParameter("password"));
        user.setNickName(request.getParameter("name"));
        user.setUsername(request.getParameter("userName"));
        user.setPhone(request.getParameter("userName"));
        user.setCreateTime(RundomUtils.getNowTime());
        user.setUserId(RundomUtils.rundomSix());
        int rum= userService.register(user);
        if (rum > 0 ){
            return new ResponseBean<String>(200,"创建成功");
        }else {
            return new ResponseBean<String>(201,"创建失败");
        }
    }
    @PostMapping("/userList")
    @ResponseBody
    public ResponseBean<Object> userList(HttpServletRequest request){
        List<User> users = userService.select(request.getParameter("phone"));
        return new ResponseBean<Object>(200,"查询成功",users);
    }
    @GetMapping("/personInfo")
    @ResponseBody
    public ResponseBean<Object> personInfo(HttpServletRequest request){
        String id=request.getParameter("id");
      /*  HttpSession session=request.getSession();
        User userInfo=(User)session.getAttribute(token);*/
        User users=userService.personInfo(Long.parseLong(id));
        return new ResponseBean<Object>(200,"查询成功",users);
    }

   /* @PostMapping("/profile/password")
    @ResponseBody
    public String passwordUpdate(HttpServletRequest request, @RequestParam("originalPassword") String originalPassword,
                                 @RequestParam("newPassword") String newPassword) {
        if (StringUtils.isEmpty(originalPassword) || StringUtils.isEmpty(newPassword)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updatePassword(loginUserId, originalPassword, newPassword)) {
            //修改成功后清空session中的数据，前端控制跳转至登录页
            request.getSession().removeAttribute("loginUserId");
            request.getSession().removeAttribute("loginUser");
            request.getSession().removeAttribute("errorMsg");
            return ServiceResultEnum.SUCCESS.getResult();
        } else {
            return "修改失败";
        }
    }

    @PostMapping("/profile/name")
    @ResponseBody
    public String nameUpdate(HttpServletRequest request, @RequestParam("loginUserName") String loginUserName,
                             @RequestParam("nickName") String nickName) {
        if (StringUtils.isEmpty(loginUserName) || StringUtils.isEmpty(nickName)) {
            return "参数不能为空";
        }
        Integer loginUserId = (int) request.getSession().getAttribute("loginUserId");
        if (adminUserService.updateName(loginUserId, loginUserName, nickName)) {
            return ServiceResultEnum.SUCCESS.getResult();
        } else {
            return "修改失败";
        }
    }*/
    @ResponseBody
    @GetMapping("/logout")
    public ResponseBean<String> logout(HttpServletRequest request) {
        String token=request.getHeader("Authorization");
        HttpSession session=request.getSession();
        session.removeAttribute(token);
        Object userInfo=session.getAttribute(token);
        if (userInfo == null){
            return new ResponseBean<String>(200,"退出登陆成功");
        }else {
            return new ResponseBean<String>(201,"退出登陆失败");
        }
    }
    @RequestMapping("/login")
    public String login(){
        return "/login";
    }
}
