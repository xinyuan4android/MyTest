package com.example.iningke.myapplication.testMVP.model;

import com.example.hxy_baseproject.base.BaseSubscriber;
import com.example.hxy_baseproject.base.IBaseRequestCallBack;
import com.example.hxy_baseproject.http.HostType;
import com.example.hxy_baseproject.http.RetrofitManager;
import com.example.iningke.myapplication.testMVP.Api;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 描述：Model实现网络请求，获取数据
 * 作者：hxy on  2017/9/28 13:38.
 */

public class UserModelImpl implements UserModel {

    @Override
    public Subscription getUserInfo(String userId, IBaseRequestCallBack callBack) {
        return RetrofitManager.getInstance(HostType.MOVIE_HOST)
                .getRetrofit().create(Api.class)
                .getUserInfo(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseSubscriber(callBack));
    }

    @Override
    public Subscription getBusinessInfo(String businessId, IBaseRequestCallBack callBack) {
        return RetrofitManager.getInstance(HostType.MOVIE_HOST)
                .getRetrofit().create(Api.class)
                .getUserInfo(businessId)
                .subscribe(new BaseSubscriber(callBack));
    }
}
