package com.zhang.controller;

import com.zhang.entity.User;
import com.zhang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhangxh
 * @date 2020/3/24 15:51
 *
 * 用户登录controller层
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }
    //判断是否可以登录成功
    private boolean flag = true;
    //查询用户是否存在-登录

    @ResponseBody
    @RequestMapping("/findUserByIdAndPwd")
    public Map<String, String> findUserByIdAndPwd(String username, String password ) {
        System.out.println("controller层接收的参数值username:"+username+"- password:"+ password);
        User requestUser = new User ();
        requestUser.setUserName(username);
        requestUser.setPassword(password);
        User user = loginService.findUserByIdAndPwd(requestUser);
        Map<String,String> map = new HashMap<String ,String>();
        if (user==null){
            map.put("msg","用户不存在");
        }else {
            map.put("msg","用户存在");
        }
        return map;
    }
}
