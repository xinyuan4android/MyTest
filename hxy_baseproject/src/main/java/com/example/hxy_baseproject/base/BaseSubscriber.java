package com.example.hxy_baseproject.base;

import com.google.gson.JsonObject;

import rx.Subscriber;

/**
 * 描述：网络请求回调基类,在这对返回错误做统一处理
 * 作者：hxy on  2017/9/28 11:22.
 */
// @param <T>网络请求返回的类

public class BaseSubscriber extends Subscriber<JsonObject> {

    private IBaseRequestCallBack requestCallBack;

    public BaseSubscriber(IBaseRequestCallBack requestCallBack) {
        this.requestCallBack = requestCallBack;

    }

    @Override
    public void onStart() {
        super.onStart();
        if (requestCallBack != null) {
            requestCallBack.beforeRequest();
        }
    }

    @Override
    public void onCompleted() {
        if (requestCallBack != null) {
            requestCallBack.requestComplete();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (requestCallBack != null) {
            requestCallBack.requestError(e.getMessage());
            //TODO 网络请求的错误信息统一处理
        }
    }

    @Override
    public void onNext(JsonObject t) {
        if (requestCallBack != null) {
            requestCallBack.requestSuccess(t);
        }
    }
}
