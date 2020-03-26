package com.zhang.service;

import com.zhang.entity.User;

/**
 * @author zhangxh
 * @date 2020/3/24 15:57
 *
 * 用户登录service层接口
 */
public interface LoginService {
    //查询用户是否存在
    public User findUserByIdAndPwd(User requestUser);
    //用户注册
    public void regist(User requestUser);
}
