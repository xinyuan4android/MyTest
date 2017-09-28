package com.example.hxy_baseproject.http;

import android.util.SparseArray;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 描述：
 * 作者：hxy on  2017/9/28 11:10.
 */

public class RetrofitManager<T> {
    // 管理不同HostType的单例
    private static SparseArray<RetrofitManager> sInstanceManager = new SparseArray<>(HostType.values().length);

    private static volatile OkHttpClient sOkHttpClient;

    //    private Api mApi;
    private Retrofit retrofit;

    private RetrofitManager(HostType hostType) {
        retrofit = new Retrofit.Builder()
                .baseUrl(HostType.getHosType(hostType))
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient() {
        if (sOkHttpClient == null) {
            synchronized (RetrofitManager.class) {
                if (sOkHttpClient == null) {
                    // OkHttpClient配置是一样的,静态创建一次即可
                    // 指定缓存路径,缓存大小100Mb
//                    Cache cache = new Cache(new File(App.getContext().getCacheDir(), "HttpCache"), 1024 * 1024 * 100);

                    sOkHttpClient = new OkHttpClient.Builder()
//                            .cache(cache)
                            .retryOnConnectionFailure(true)
                            .connectTimeout(30, TimeUnit.SECONDS)
                            .readTimeout(10000L, TimeUnit.MILLISECONDS)
                            .build();


                }
            }
        }
        return sOkHttpClient;
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }

    /**
     * 获取单例
     *
     * @param hostType host类型
     * @return 实例
     */
    public static RetrofitManager getInstance(HostType hostType) {
        RetrofitManager instance = sInstanceManager.get(HostType.getIndex(hostType));
        if (instance == null) {
            instance = new RetrofitManager(hostType);
            sInstanceManager.put(HostType.getIndex(hostType), instance);
            return instance;
        } else {
            return instance;
        }
    }
}
