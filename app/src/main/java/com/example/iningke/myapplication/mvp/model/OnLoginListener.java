package com.example.iningke.myapplication.mvp.model;

import com.example.iningke.myapplication.mvp.bean.BeanUser;

/**
 * Created by hxy on  2016/11/7.
 */
public interface OnLoginListener {
    void loginSuccess(BeanUser beanUser);

    void loginFailed();
}
