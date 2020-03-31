package com.zhang.controller;

import com.zhang.entity.User;
import com.zhang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
    //注入service层
    @Resource(name="loginService")
    private LoginService loginService;

    //查询用户是否存在
    @ResponseBody
    @RequestMapping("/findUserByIdAndPwd")
    public Map<String, Object> findUserByIdAndPwd(String username, String password ) {
        System.out.println("controller层接收的参数值username:"+username+"- password:"+ password);
        User requestUser = new User ();
        requestUser.setUserName(username);
        requestUser.setPassword(password);
        User user = loginService.findUserByIdAndPwd(requestUser);
        Map<String,Object> map = new HashMap<String ,Object>();
        if (user==null){
            map.put("isExsit",false);
            map.put("msg","用户名或密码错误");
        }else {
            map.put("isExsit",true);
            map.put("msg","登录成功");
        }
        return map;
    }
    //注册
    @ResponseBody
    @RequestMapping("/regist")
    public Map<String, Object> regist(String username, String password,String phone) {
        System.out.println("注册数据 username:"+username+"- password:"+ password+"- phone:"+ phone);
        User user = new User (username,password,phone);
        int result = loginService.regist(user);
        Map<String,Object> map = new HashMap<String ,Object>();
        if (result == 1){
            System.out.println("用户注册成功");
            map.put("flag",true);
            map.put("msg","注册成功");
        }else {
            System.out.println("用户注册失败");
            map.put("flag",false);
            map.put("msg","注册失败");
        }
        return map;
    }
    //查询用户名是否存在
    @ResponseBody
    @RequestMapping("/findUserByName")
    public Map<String, Object> findUserByName(String username) {
        System.out.println("controller层接收的参数值username:"+username);
        User user = loginService.findUserByName(username);
        Map<String,Object> map = new HashMap<String ,Object>();
        if (user==null){
            map.put("isExsit",true);
            map.put("msg","用户名可以用哦!");
        }else {
            map.put("isExsit",false);
            map.put("msg","这个名字太火爆了,换一个试试吧!");
        }
        return map;
    }


    //查询手机号码是否存在
    @ResponseBody
    @RequestMapping("/findUserByPhone")
    public Map<String, Object> findUserByPhone(String phone) {
        System.out.println("controller层接收的参数值phone:"+phone);
        Map<String,Object> map = new HashMap<String ,Object>();
        int flag = loginService.findUserByPhone(phone);
        if (flag == 1){
            map.put("isExsit",true);
            map.put("msg","*&nbsp;&nbsp;手机号码可以用哦!");
        }else {
            map.put("isExsit",false);
            map.put("msg","*&nbsp;&nbsp;手机号码已被占用,换一个试试吧!");
        }
        return map;
    }
}
