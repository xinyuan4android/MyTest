package com.example.iningke.myapplication.mvp.presenter;

import android.os.Handler;
import android.os.Message;

import com.example.iningke.myapplication.mvp.bean.BeanUser;
import com.example.iningke.myapplication.mvp.model.Model;
import com.example.iningke.myapplication.mvp.model.OnLoginListener;
import com.example.iningke.myapplication.mvp.view.IViewLogin;

/**
 * Created by hxy on  2016/11/7.
 */
public class Presenter {
    private Model model;
    private IViewLogin iViewLogin;

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    public Presenter(IViewLogin iViewLogin) {
        this.model = new Model();
        this.iViewLogin = iViewLogin;
    }

    public void login() {
        model.login(iViewLogin.getUserName(), iViewLogin.getPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final BeanUser beanUser) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iViewLogin.loginSuccess(beanUser);
                    }
                });

            }

            @Override
            public void loginFailed() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        iViewLogin.loginError();
                    }
                });
            }
        });
    }

    public void clear() {
        iViewLogin.clearPassword();
        iViewLogin.clearUserName();
    }
}
