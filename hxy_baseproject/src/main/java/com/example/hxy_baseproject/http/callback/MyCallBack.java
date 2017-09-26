package com.example.hxy_baseproject.http.callback;

import okhttp3.Response;

/**
 * Created by hxy on  2017/9/25.
 */

public interface MyCallBack<T> {
    /**
     * 请求之前调用,可以做一些加载动画之类的操作
     * UI线程
     */
    void beforeRequest(int requestId);

    /**
     * 请求完成调用
     * UI Thread
     */
    void requestComplete(int requestId);

    /**
     * 请求错误调用
     *
     * @param msg 错误信息
     */
    void requestError(String msg, int requestId);

    /**
     * UI Thread
     */
    void inProgress(float progress, long total, int requestId);

    /**
     * 请求成功调用
     *
     * @param data 数据
     */
    void requestSuccess(T data, int requestId);

    /**
     * Thread Pool Thread
     *
     * @param response
     */
    T parseNetworkResponse(Response response, int id) throws Exception;


}
