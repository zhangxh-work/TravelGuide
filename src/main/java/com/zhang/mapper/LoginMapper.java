package com.zhang.mapper;

import com.zhang.entity.User;

/**
 * @author zhangxh
 * @date 2020/3/25 10:38
 */
public interface LoginMapper {
    public User findUserByIdAndPwd(User user);
}
