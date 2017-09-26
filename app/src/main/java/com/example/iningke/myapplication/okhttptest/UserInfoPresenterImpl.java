package com.example.iningke.myapplication.okhttptest;

import com.example.hxy_baseproject.base.BasePresenterImpl;

import okhttp3.Response;

/**
 * Created by hxy on  2017/9/26.
 */

public class UserInfoPresenterImpl extends BasePresenterImpl<UserInfoView, UserInfoModel> {

    public UserInfoPresenterImpl(UserInfoView view) {
        super(view);
    }

    @Override
    public void start() {

    }

    @Override
    public void inProgress(float progress, long total, int requestId) {

    }

    @Override
    public void requestSuccess(UserInfoModel data) {
        view.showUserInfo(data);
    }

    @Override
    public UserInfoModel parseNetworkResponse(Response response, int id) throws Exception {
        return null;
    }
}
