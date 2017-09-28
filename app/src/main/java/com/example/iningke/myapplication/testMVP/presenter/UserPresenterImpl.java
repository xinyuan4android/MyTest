package com.example.iningke.myapplication.testMVP.presenter;

import com.example.hxy_baseproject.base.BasePresenterImpl;
import com.example.iningke.myapplication.okhttptest.UserInfoModel;
import com.example.iningke.myapplication.testMVP.model.UserModelImpl;
import com.example.iningke.myapplication.testMVP.view.UserView;

/**
 * 描述：
 * 作者：hxy on  2017/9/28 11:57.
 */

public class UserPresenterImpl extends BasePresenterImpl<UserView, UserInfoModel> implements UserPresenter {

    private UserModelImpl userModel;

    public UserPresenterImpl(UserView view) {
        super(view);
        this.view = view;
        userModel = new UserModelImpl();
    }

    @Override
    public void requestSuccess(UserInfoModel data) {
        view.showUserInfo(data);
    }

    @Override
    public void requestError(String errorMsg) {
        super.requestError(errorMsg);
        view.showErrorInfo(errorMsg);
    }

    @Override
    public void getUserInfo(String userId) {
        mSubscription = userModel.getUserInfo(userId, this);
    }
}
