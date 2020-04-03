package com.zhang.controller;

import com.zhang.entity.User;
import com.zhang.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public Map<String, Object> findUserByIdAndPwd(String username, String password , HttpServletResponse response) {
        System.out.println("登录数据  username:"+username+" - password:"+ password);
        User requestUser = new User ();
        //将用户名当做用户名或手机号进行查询,可以实现(用户名or手机号)+密码登录
        requestUser.setUserName(username);
        requestUser.setPhone(username);
        requestUser.setPassword(password);
        //调用service层的查询方法
        User user = loginService.findUserByIdAndPwd(requestUser);
        //用于向前端传值的map
        Map<String,Object> map = new HashMap<String ,Object>();
        if (user==null){        //没有查到匹配的用户,登录失败
            System.out.println(username+"登录成功");
            map.put("isExsit",false);
            map.put("msg","用户名或密码错误");
        }else {                 //查到匹配的用户,登录成功
            System.out.println(username+"登录失败");
            //将用户放入cookie中用于前端其他操作
            Cookie cookie = new Cookie("username",user.getUserName());
            response.addCookie(cookie);
            map.put("isExsit",true);
            map.put("user",user);
            map.put("msg","登录成功");
        }
        //将结果数据返回给前端
        return map;
    }


    //用户注册
    @ResponseBody
    @RequestMapping("/regist")
    public Map<String, Object> regist(String username, String password,String phone) {
        System.out.println("注册数据 username:"+username+" - password:"+ password+"- phone:"+ phone);
        User user = new User (username,password,phone);
        //调用service层的注册方法
        int result = loginService.regist(user);
        Map<String,Object> map = new HashMap<String ,Object>();
        if (result == 1){       //操作成功条目为1,表明插入成功
            System.out.println("用户注册成功");
            map.put("flag",true);
            map.put("msg","注册成功");
        }else {                 //插入失败
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
        System.out.println("查询用户名 username:"+username);
        //调用service层的查询方法
        User user = loginService.findUserByName(username);
        Map<String,Object> map = new HashMap<String ,Object>();
        if (user==null){            //用户名不存在,可以使用
            map.put("isExsit",true);
            map.put("msg","*&nbsp;&nbsp;用户名可以用哦!");
        }else {                     //用户名已被占用
            map.put("isExsit",false);
            map.put("msg","*&nbsp;&nbsp;这个名字太火爆了,换一个试试吧!");
        }
        return map;
    }


    //查询手机号码是否存在
    @ResponseBody
    @RequestMapping("/findUserByPhone")
    public Map<String, Object> findUserByPhone(String phone) {
        System.out.println("查询手机号 phone:"+phone);
        Map<String,Object> map = new HashMap<String ,Object>();
        //调用service层的查询手机号方法
        int flag = loginService.findUserByPhone(phone);
        if (flag == 1){         //手机号已被占用
            map.put("isExsit",false);
            map.put("msg","*&nbsp;&nbsp;手机号码已被占用,换一个试试吧!");
        }else {                 //手机号可以使用
            map.put("isExsit",true);
            map.put("msg","*&nbsp;&nbsp;手机号码可以用哦!");
        }
        return map;
    }
}
