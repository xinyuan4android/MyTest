package com.example.iningke.myapplication.rxjava;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.iningke.myapplication.R;
import com.example.iningke.myapplication.okhttptest.UserInfoModel;
import com.google.gson.Gson;
import com.iningke.baseproject.utils.LogUtils;

import java.io.File;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class TestRxJavaActivity extends AppCompatActivity {
    private ImageView imageView;

    private RxApi rxApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_rx_java);
        imageView = (ImageView) findViewById(R.id.imageView);
        aboutRetrofit();
    }

    private void aboutRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl("http://116.62.185.223/yishang/f/")
//                .baseUrl("http://app.jiakaojingling.com/jkjl/api/")
                .build();
        rxApi = retrofit.create(RxApi.class);

    }

    public void onClick3(View view) {
        Subscriber<UserInfoModel> userSubscriber = new Subscriber<UserInfoModel>() {
            @Override
            public void onCompleted() {
                LogUtils.e("onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("onError" + e.getMessage());
            }

            @Override
            public void onNext(UserInfoModel userInfoModel) {
                LogUtils.e("onNEXT" + new Gson().toJson(userInfoModel));
            }
        };
        rxApi.getUserInfo("53e8d39a50c54c1395728dc77451d148")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userSubscriber);
    }

    public void onClick(View view) {
        //订阅
        observable.subscribe(observer);
    }

    public void onClick2(View view) {
        Observable.create(new Observable.OnSubscribe<Bitmap>() {

            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                Bitmap bitmap = BitmapFactory.decodeFile(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "test.jpg");
                subscriber.onNext(bitmap);
            }
        }).subscribe(new Observer<Bitmap>() {
            @Override
            public void onCompleted() {
                LogUtils.e("completed");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Bitmap o) {
                LogUtils.e("onNext");
                imageView.setImageBitmap(o);
            }
        });
    }

    //观察者
    Observer<String> observer = new Observer<String>() {
        @Override
        public void onCompleted() {
            LogUtils.e("completed");
        }

        @Override
        public void onError(Throwable e) {
            LogUtils.e("onError" + e.getMessage());
        }

        @Override
        public void onNext(String s) {
            LogUtils.e("onNext" + s);
        }
    };

    //被观察者
    Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
        //被观察者 被订阅的时候 自动调用的方法
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onStart();
            subscriber.onNext("1111111");
            subscriber.onCompleted();
        }
    });


}
