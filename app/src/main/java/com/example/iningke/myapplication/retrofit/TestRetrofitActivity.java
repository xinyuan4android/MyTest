package com.example.iningke.myapplication.retrofit;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.iningke.myapplication.R;
import com.example.iningke.myapplication.okhttptest.UserInfoModel;
import com.google.gson.Gson;
import com.iningke.baseproject.utils.LogUtils;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TestRetrofitActivity extends AppCompatActivity {
    private API api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://116.62.185.223/yishang/f/")
                .build();
        api = retrofit.create(API.class);
    }

    public void aboutRetrofit2(View view) throws IOException {
        Call<UserInfoModel> call = api.getUserInfo("53e8d39a50c54c1395728dc77451d148");
        call.enqueue(new Callback<UserInfoModel>() {
            @Override
            public void onResponse(Call<UserInfoModel> call, Response<UserInfoModel> response) {
                LogUtils.e("onResponse" + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<UserInfoModel> call, Throwable t) {
                LogUtils.e("onFailure" + t.getMessage());
            }
        });
    }

    public void aboutRetrofit(View view) throws IOException {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.jpg");
        RequestBody body = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part headImg = MultipartBody.Part.createFormData("headImg", "test.jpg", body);
        Call<String> call = api.updateAvatar("53e8d39a50c54c1395728dc77451d148", "ComeOn", 1, headImg);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                LogUtils.e("onResponse" + response.body().toString());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                LogUtils.e("onFailure" + t.getMessage());
            }
        });
    }

}
