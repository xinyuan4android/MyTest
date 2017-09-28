package com.example.iningke.myapplication.testMVP.model;


import com.example.hxy_baseproject.base.IBaseRequestCallBack;

import rx.Subscription;

/**
 * 描述：
 * 作者：hxy on  2017/9/28 13:34.
 */

public interface UserModel<T> {

    Subscription getUserInfo(String userId, IBaseRequestCallBack<T> callBack);
}
