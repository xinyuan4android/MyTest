package com.example.hxy_baseproject.base;

/**
 * 描述：视图基类
 * 作者：hxy on  2017/9/28 10:34.
 */

public interface IBaseView<T> {

    /**
     * 网络请求请求前加载progress
     */
    void showProgress();

    /**
     * 网络请求后请求结束之后隐藏progress
     */
    void dismissProgress();

}
