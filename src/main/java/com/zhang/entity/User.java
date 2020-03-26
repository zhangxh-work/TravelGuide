package com.zhang.entity;

import java.io.Serializable;

/**
 * @author zhangxh
 * @date 2020/3/24 15:57
 *
 * 用户实体类
 */
public class User implements Serializable {
    private int id;
    private String userName;
    private String password;
    private int phone;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", phone=" + phone +
                '}';
    }
}
