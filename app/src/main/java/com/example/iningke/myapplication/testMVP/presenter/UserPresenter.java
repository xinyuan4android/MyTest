package com.example.iningke.myapplication.testMVP.presenter;

import com.example.hxy_baseproject.base.IBasePresenter;

/**
 * 描述：
 * 作者：hxy on  2017/9/28 11:36.
 */

public interface UserPresenter extends IBasePresenter {

    //获取用户信息
    void getUserInfo(String userId);


    //获取商户信息
    void getBusinessInfo(String businessId);
}
