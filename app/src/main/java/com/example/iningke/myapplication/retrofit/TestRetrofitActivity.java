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
import java.util.HashMap;

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
    private String userId = "53e8d39a50c54c1395728dc77451d148";
    private String userName = "ComeOn";
    private int sex = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl("http://116.62.185.223/yishang/f/")
                .baseUrl("http://app.jiakaojingling.com/jkjl/api/")
                .build();
        api = retrofit.create(API.class);
    }


    public void aboutRetrofit(View view) {
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

    public void aboutRetrofit10(View view) {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.jpg");
        RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
//        RequestBody body = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("headImg", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
//                .build();
        MultipartBody.Part headImg = MultipartBody.Part.createFormData("headImg", file.getName(), body);
        Call<BaseModel> call = api.updateAvatar("53e8d39a50c54c1395728dc77451d148", "ComeOn", 1, headImg);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                LogUtils.e("URL" + call.request().url());
                LogUtils.e("onResponse" + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                LogUtils.e("onFailure" + t.getMessage());
            }
        });
    }

    //{"uid":"37309","equitment":"ffffffff-f39c-63e4-4954-f43b43bcb11f","phone":"18363852860","schoolId":"17"}
    public void aboutRetrofit1(View view) {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.jpg");
        RequestBody body = RequestBody.create(MultipartBody.FORM, file);
//        RequestBody body = new MultipartBody.Builder()
//                .setType(MultipartBody.FORM)
//                .addFormDataPart("headImg", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
//                .build();
        MultipartBody.Part headImg = MultipartBody.Part.createFormData("uploadFile", file.getName(), body);
        Call<JKModel> call = api.updateAvatarJK("37309", "ffffffff-f39c-63e4-4954-f43b43bcb11f", "18363852860", headImg);
        call.enqueue(new Callback<JKModel>() {
            @Override
            public void onResponse(Call<JKModel> call, Response<JKModel> response) {
                LogUtils.e("URL" + call.request().url());
                try {
                    okio.Buffer buffer = new okio.Buffer();
                    call.request().body().writeTo(buffer);
                    LogUtils.e("请求 参数" + buffer.readUtf8());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                LogUtils.e("onResponse" + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<JKModel> call, Throwable t) {
                LogUtils.e("onFailure" + t.getMessage());
            }
        });
    }

    public void aboutRetrofit2(View view) {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.jpg");
        RequestBody body = RequestBody.create(MultipartBody.FORM, file);
        MultipartBody.Part headImg = MultipartBody.Part.createFormData("uploadFile", file.getName(), body);

        RequestBody userIdRequest = RequestBody.create(MultipartBody.FORM, "37309");
        RequestBody userNameRequest = RequestBody.create(MultipartBody.FORM, "ffffffff-f39c-63e4-4954-f43b43bcb11f");
        RequestBody sexRequest = RequestBody.create(MultipartBody.FORM, "18363852860");

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("uid", userIdRequest);
        map.put("equitment", userNameRequest);
        map.put("phone", sexRequest);
        Call<BaseModel> call = api.updateAvatarJK2(map, headImg);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                LogUtils.e("URL" + call.request().url());
                try {
                    okio.Buffer buffer = new okio.Buffer();
                    call.request().body().writeTo(buffer);
                    LogUtils.e("请求 参数" + buffer.readUtf8());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                LogUtils.e("onResponse" + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                LogUtils.e("onFailure" + t.getMessage());
            }
        });
    }

    public void aboutRetrofit22(View view) {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.jpg");
        RequestBody body = RequestBody.create(MultipartBody.FORM, file);
        MultipartBody.Part headImg = MultipartBody.Part.createFormData("headImg", file.getName(), body);

        RequestBody userIdRequest = RequestBody.create(MultipartBody.FORM, userId);
        RequestBody userNameRequest = RequestBody.create(MultipartBody.FORM, userName);
        RequestBody sexRequest = RequestBody.create(MultipartBody.FORM, sex + "");

        HashMap<String, RequestBody> map = new HashMap<>();
        map.put("userId", userIdRequest);
        map.put("nickName", userNameRequest);
        map.put("sex", sexRequest);
        Call<BaseModel> call = api.updateAvatar2(map, headImg);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                LogUtils.e("URL" + call.request().url());
                LogUtils.e("请求 参数" + new Gson().toJson(call.request().body()));
                LogUtils.e("onResponse" + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                LogUtils.e("onFailure" + t.getMessage());
            }
        });
    }

    public void aboutRetrofit3(View view) {
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.jpg");
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("userId", userId)
                .addFormDataPart("nickName", userName)
                .addFormDataPart("sex", sex + "")
                .addFormDataPart("headImg", file.getName(), RequestBody.create(MediaType.parse("image/*"), file))
                .build();
        Call<BaseModel> call = api.updateAvatar3(requestBody);
        call.enqueue(new Callback<BaseModel>() {
            @Override
            public void onResponse(Call<BaseModel> call, Response<BaseModel> response) {
                LogUtils.e("onResponse" + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<BaseModel> call, Throwable t) {
                LogUtils.e("onFailure" + t.getMessage());
            }
        });
    }
}
