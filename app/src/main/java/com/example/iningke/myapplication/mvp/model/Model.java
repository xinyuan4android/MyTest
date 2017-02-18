package com.example.iningke.myapplication.mvp.model;

import com.example.iningke.myapplication.mvp.bean.BeanUser;

/**
 * Created by hxy on  2016/11/7.
 */
public class Model implements IModelLogin {


    @Override
    public void login(final String userName, final String password, final OnLoginListener listener) {
        //模拟子线程耗时操作
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("hxy".equals(userName) && "123".equals(password)) {
                    BeanUser user = new BeanUser();
                    user.setUserName(userName);
                    user.setPassword(password);
                    listener.loginSuccess(user);
                } else {
                    listener.loginFailed();
                }
            }
        }.start();
    }
}
