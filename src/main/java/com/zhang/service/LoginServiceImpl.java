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
@Service
public class LoginServiceImpl implements LoginService {

    //依赖于Dao
    @Autowired
    private LoginMapper loginMapper;
    public void setLoginMapper(LoginMapper loginMapper) {
        this.loginMapper = loginMapper;
    }

    //查询用户是否存在
    public User findUserByIdAndPwd(User requestUser) {
        System.out.println("用户登录service层---findUserByIdAndPwd()");
        User user = loginMapper.findUserByIdAndPwd(requestUser);
        System.out.println("service:"+user);
        return user;
    }
}