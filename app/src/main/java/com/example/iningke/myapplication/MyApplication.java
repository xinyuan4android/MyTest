package com.example.iningke.myapplication;

import com.example.hxy_baseproject.http.LoggerInterceptor;
import com.example.hxy_baseproject.http.OkHttpUtils;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;
import com.iningke.baseproject.BaseApp;

import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import io.rong.imkit.RongIM;
import okhttp3.OkHttpClient;

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

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                })
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }
}
