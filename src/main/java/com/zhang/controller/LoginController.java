package com.zhang.controller;

import com.zhang.entity.User;
import com.zhang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
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
    //查询用户是否存在
    @ResponseBody
    @RequestMapping("/regist")
    public Map<String, String> regist(String username, String password ,int phone) {
        System.out.println("controller层接收的参数值username:"+username+"- password:"+ password+"- phone:"+ phone);
        User requestUser = new User ();
        requestUser.setUserName(username);
        requestUser.setPassword(password);
        requestUser.setPhone(phone);
        User user = loginService.findUserByIdAndPwd(requestUser);
        Map<String,String> map = new HashMap<String ,String>();
        if (user==null){
            loginService.regist(requestUser);
            map.put("msg","注册成功");
        }else {
            map.put("msg","用户已存在,注册失败!");
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
            map.put("msg","这个名字太火爆了,请换一个吧!");
        }
        return map;
    }
}
