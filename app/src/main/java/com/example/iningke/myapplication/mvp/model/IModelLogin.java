package com.example.iningke.myapplication.mvp.model;

/**
 * Created by hxy on  2016/11/7.
 */
public interface IModelLogin {
    void login(String userName, String password, OnLoginListener listener);
}
