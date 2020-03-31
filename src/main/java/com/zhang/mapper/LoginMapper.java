package com.zhang.mapper;

import com.zhang.entity.User;

/**
 * @author zhangxh
 * @date 2020/3/25 10:38
 * 用户登录的Dao映射文件
 */
public interface LoginMapper {
    public User findUserByIdAndPwd(User user);

    public int regist(User user);

    public User findUserByName(String username);

    public int findUserByPhone(String phone);
}
