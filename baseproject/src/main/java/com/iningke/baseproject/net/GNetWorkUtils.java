package com.iningke.baseproject.net;


import com.iningke.baseproject.net.callback.GNetCallback;

import org.xutils.http.RequestParams;

/**
 * Created by iningke on 2016/6/13.
 */

public class GNetWorkUtils implements GNetCallback {

    public boolean isSending = false;

    public void cancle() {

    }

    //    public void sendPost(RequestParams params, int requestcode, Class clz){
//        isSending = true;
//        new GNetWork(params,this,requestcode,clz).sendPost();
//    }
//    public void sendGet(RequestParams params,int requestcode,Class clz){
//        isSending = true;
//        new GNetWork(params,this,requestcode,clz).sendGet();
//    }
    public void post(RequestParams params, int requestcode, Class clz) {
        isSending = true;
        new GNetWork(params, this, requestcode, clz).post();
    }

    @Override
    public void success(Object obj, int requestCode) {
        isSending = false;
    }

    @Override
    public void onFaild(int requestCode, boolean isCancell, Throwable ex) {
        isSending = false;
    }

    @Override
    public void onResult(int resultCode, Object... obj) {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }
}
