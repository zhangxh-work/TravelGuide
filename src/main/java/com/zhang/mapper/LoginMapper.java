package com.zhang.mapper;

import com.zhang.entity.User;

/**
 * @author zhangxh
 * @date 2020/3/25 10:38
 * 用户登录的Dao映射文件
 */
public interface LoginMapper {
    //登录查询
    public User findUserByIdAndPwd(User user);

    //注册插入
    public int regist(User user);

    //查找用户名是否被占用
    public User findUserByName(String username);

    //查找手机号是否被占用
    public int findUserByPhone(String phone);
}
