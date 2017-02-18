package com.iningke.baseproject.net;

import com.alibaba.fastjson.JSON;
import com.iningke.baseproject.net.callback.GNetCallback;
import com.iningke.baseproject.net.utils.GXUtils;
import com.iningke.baseproject.utils.LogUtils;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.http.annotation.HttpResponse;
import org.xutils.x;

/**
 * Created by iningke on 2016/6/13.
 */

public class GNetWork{
    private RequestParams params;
    private int time_out;
    private int request_code;
    private GNetCallback callback;
    private Class clz;
    public GNetWork(){
        this.time_out = 5000;
    }

    public GNetWork(RequestParams params, GNetCallback callback, int request_code, Class clz) {
        this.params = params;
        this.request_code = request_code;
        this.callback = callback;
        this.clz = clz;
    }

    public GNetWork(RequestParams params,GNetCallback callback, int time_out, int request_code, Class clz) {
        this.params = params;
        this.time_out = time_out;
        this.request_code = request_code;
        this.callback = callback;
        this.clz = clz;
    }

    public void setParams(RequestParams params) {
        this.params = params;
    }

    public void setTime_out(int time_out) {
        this.time_out = time_out;
    }

    public int getRequest_code() {
        return request_code;
    }

    public void setRequest_code(int request_code) {
        this.request_code = request_code;
    }

//    public void sendPost(){
//        check();
//        params.setConnectTimeout(time_out);
//        x.http().post(params, new Callback.CommonCallback<String>() {
//            @Override
//            public void onSuccess(String result) {
//                if (result!=null){
//                    LogUtils.e("返回结果："+result.toString());
//                }
//                if (callback!=null)
//                    callback.success(JSON.parseObject(result,clz),request_code);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                LogUtils.e("错误信息：："+ex.getMessage());
//                if (callback!=null)
//                    callback.onFaild(request_code,false,ex);
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                LogUtils.e("请求取消-------------------");
//                if (callback!=null)
//                    callback.onFaild(request_code,true,null);
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
//
//    public void sendGet(){
//        check();
//        params.setConnectTimeout(time_out);
//        x.http().get(params,new Callback.CommonCallback<Object>() {
//            @Override
//            public void onSuccess(Object result) {
//                if (result!=null){
//                    LogUtils.e("返回结果："+result.toString());
//                }
//                if (callback!=null)
//                    callback.success(result,request_code);
//            }
//
//            @Override
//            public void onError(Throwable ex, boolean isOnCallback) {
//                LogUtils.e("错误信息：："+ex.getMessage());
//                if (callback!=null)
//                    callback.onFaild(request_code,false,ex);
//            }
//
//            @Override
//            public void onCancelled(CancelledException cex) {
//                LogUtils.e("请求取消-------------------");
//                if (callback!=null)
//                    callback.onFaild(request_code,true,null);
//            }
//
//            @Override
//            public void onFinished() {
//
//            }
//        });
//    }
    public void check(){
        if (params==null){
            LogUtils.e("参数为空");
        }else {
            LogUtils.e("参数为"+params.toJSONString());
        }
        if (callback==null)
            LogUtils.e("网络回调为空");
    }
    public void post(){
        GXUtils.postX(params,callback,request_code,clz);
    }



}
