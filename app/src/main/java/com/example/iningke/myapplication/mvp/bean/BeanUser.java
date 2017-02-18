package com.example.iningke.myapplication.mvp.bean;

import java.io.Serializable;

/**
 * Created by hxy on  2016/11/7.
 */
public class BeanUser implements Serializable {
    private String userName;
    private String password;

    public BeanUser() {
    }

    public BeanUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
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
}
