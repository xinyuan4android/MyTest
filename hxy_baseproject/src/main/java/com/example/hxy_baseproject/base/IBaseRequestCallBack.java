package com.example.hxy_baseproject.base;

/**
 * 描述：
 * 作者：hxy on  2017/9/28 10:44.
 */

public interface IBaseRequestCallBack<T> {
    /**
     * 请求之前调用,可以做一些加载动画之类的操作
     */
    void beforeRequest();

    /**
     * 请求错误调用
     *
     * @param errorMsg 错误信息
     */
    void requestError(String errorMsg);

    /**
     * 请求完成调用
     */
    void requestComplete();

    /**
     * 请求成功调用
     *
     * @param data 数据
     */
    void requestSuccess(T data);
}
