package com.zhang.service;

import com.zhang.entity.User;
import com.zhang.mapper.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangxh
 * @date 2020/3/24 15:55
 *
 * 用户登录service层
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {

    //依赖于Dao
    @Autowired
    private LoginMapper loginMapper;

    //登录查询
    @Override
    public User findUserByIdAndPwd(User requestUser) { return loginMapper.findUserByIdAndPwd(requestUser);}

    //注册插入
    @Override
    public int regist(User user) {return loginMapper.regist(user);}

    //查询用户名是否被占用
    @Override
    public User findUserByName(String username) { return loginMapper.findUserByName(username);}

    //查询手机号是否被占用
    @Override
    public int findUserByPhone(String  phone) {return loginMapper.findUserByPhone(phone);}
}
