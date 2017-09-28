package com.example.iningke.myapplication.testMVP.view;

import com.example.hxy_baseproject.base.IBaseView;
import com.example.iningke.myapplication.okhttptest.UserInfoModel;

/**
 * 描述：
 * 作者：hxy on  2017/9/28 11:41.
 */

public interface UserView extends IBaseView<UserInfoModel> {

    void showUserInfo(UserInfoModel userInfo);

    void showErrorInfo(String errorMsg);
}
