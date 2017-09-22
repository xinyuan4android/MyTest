package com.example.iningke.myapplication.retrofit;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by hxy on  2017/9/22.
 */

/**
 * 请求参数：{"userId":"53e8d39a50c54c1395728dc77451d148"}
 * url：http://116.62.185.223/yishang/f/
 * 成功结果：{"result":{"comment":0,"confirm":0,"createDate":1501827832000,"goodsNum":0,"headImg":"/static/upload/shop/authentication/20170804/53e8d39a50c54c1395728dc77451d148.jpg","id":"53e8d39a50c54c1395728dc77451d148","loginType":"WeiBo","magezineNum":0,"newsNum":0,"nickName":"ComeOn新元","pay":0,"phone":"18396728449","refund":0,"role":1,"send":0,"sex":0,"shopMoney":0,"shopNum":0,"state":-1,"threeState":0},"success":true}
 */
public interface API {
    @POST("user/get")
    @FormUrlEncoded
        //表单的方式传递键值对
    Call<String> getUserInfo(@Field("userId") String userId);

}
