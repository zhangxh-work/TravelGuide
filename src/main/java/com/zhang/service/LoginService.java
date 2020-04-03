package com.zhang.service;

import com.zhang.entity.User;

/**
 * @author zhangxh
 * @date 2020/3/24 15:57
 *
 * 用户登录service层接口
 */
public interface LoginService {
    //登录查询
    public User findUserByIdAndPwd(User requestUser);

    //注册插入
    public int regist(User user);

    //查询用户名是否存在
    public User findUserByName(String username);

    //查询手机号码是否被占用
    public int findUserByPhone(String phone);
}
