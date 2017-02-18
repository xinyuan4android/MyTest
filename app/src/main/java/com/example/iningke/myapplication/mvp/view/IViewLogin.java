package com.example.iningke.myapplication.mvp.view;

import com.example.iningke.myapplication.mvp.bean.BeanUser;

/**
 * Created by hxy on  2016/11/7.
 */
public interface IViewLogin {
    //获取用户名
    String getUserName();

    //获取登录密码
    String getPassword();

    //清除方法
    void clearUserName();

    void clearPassword();

    //登录成功
    void loginSuccess(BeanUser beanUser);

    //登录失败
    void loginError();
}
