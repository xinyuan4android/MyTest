package com.example.iningke.myapplication.okhttptest;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iningke.myapplication.R;
import com.iningke.baseproject.utils.LogUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 请求参数：{"userId":"53e8d39a50c54c1395728dc77451d148"}
 * url：http://116.62.185.223/yishang/f/get
 * 成功结果：{"result":{"comment":0,"confirm":0,"createDate":1501827832000,"goodsNum":0,"headImg":"/static/upload/shop/authentication/20170804/53e8d39a50c54c1395728dc77451d148.jpg","id":"53e8d39a50c54c1395728dc77451d148","loginType":"WeiBo","magezineNum":0,"newsNum":0,"nickName":"ComeOn新元","pay":0,"phone":"18396728449","refund":0,"role":1,"send":0,"sex":0,"shopMoney":0,"shopNum":0,"state":-1,"threeState":0},"success":true}
 */
public class TestOkHttpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_okhttp);
        LogUtils.e("thread" + Thread.currentThread().getName());
    }

    public void aboutOkHttp(View view) {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();

        RequestBody requestBody = new FormBody.Builder()
//                    .addEncoded(URLEncoder.encode("userId", "UTF-8"),
//                            URLEncoder.encode("53e8d39a50c54c1395728dc77451d148", "UTF-8"))
                .add("userId", "53e8d39a50c54c1395728dc77451d148")
                .build();
        RequestBody requestBody1 = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userId", "53e8d39a50c54c1395728dc77451d148")
                .build();
        RequestBody requestBody2 = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userId", "53e8d39a50c54c1395728dc77451d148")
                .addFormDataPart("headImg", "test.jpg",
                        RequestBody.create(MediaType.parse("image/jpeg"),
                                new File(Environment.getExternalStorageDirectory() + File.separator + "test.jpg")))
                .addFormDataPart("nickName", "Come_On")
                .addFormDataPart("sex", "1")
                .build();
//        RequestBody requestBody3 = new MultipartBody.Builder()
//                .setType(MediaType.parse("application/json"))
//                .build();
        Request request = new Request.Builder()
                .url("http://116.62.185.223/yishang/f/user/get")
//                .url("http://116.62.185.223/yishang/f/user/update")
                .post(requestBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogUtils.e("fail" + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    LogUtils.e("success" + response.body().string());
                } else
                    LogUtils.e(response.code() + response.body().string());
                LogUtils.e("thread response" + Thread.currentThread().getName());
            }
        });
//        call.cancel();
//        LogUtils.e("call status" + call.isCanceled());
    }
    //http://116.62.185.223/yishang/f/user/update
    //headImg
    //nickName
    //sex
}
