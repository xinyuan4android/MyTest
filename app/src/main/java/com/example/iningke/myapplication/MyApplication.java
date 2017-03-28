package com.example.iningke.myapplication;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.iningke.baseproject.BaseApp;

import io.rong.imkit.RongIM;

/**
 * Created by iningke on 2016/7/21.
 */
public class MyApplication extends BaseApp {
    private static MyApplication myApplication;

    public static MyApplication getMyApplication() {
        return myApplication;
    }

    public static void setMyApplication(MyApplication myApplication) {
        MyApplication.myApplication = myApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        myApplication = this;
        /**
         * 初始化融云
         */
        RongIM.init(this);
        //
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=58a50b41");
    }
}
