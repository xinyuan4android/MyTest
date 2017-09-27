package com.example.iningke.myapplication.rxjava;

import com.example.iningke.myapplication.okhttptest.UserInfoModel;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by hxy on  2017/9/27.
 */

public interface RxApi {
    @POST("user/get")
    @FormUrlEncoded
        //表单的方式传递键值对
    Observable<UserInfoModel> getUserInfo(@Field("userId") String userId);
}
