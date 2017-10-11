package com.example.iningke.myapplication.testMVP;

import com.google.gson.JsonObject;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 描述：
 * 作者：hxy on  2017/9/28 13:48.
 */

public interface Api {
    @POST("user/get")
    @FormUrlEncoded
        //表单的方式传递键值对
    Observable<JsonObject> getUserInfo(@Field("userId") String userId);
}
