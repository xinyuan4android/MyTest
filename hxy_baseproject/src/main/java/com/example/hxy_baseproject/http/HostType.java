package com.example.hxy_baseproject.http;

/**
 * Created by lts on 2017/8/28.
 * Fuction: {@link retrofit2.Retrofit} baseUrl类型
 * Update:
 */

public enum HostType {

    MOVIE_HOST(1,Constant.HOST_URL);

    private String mUrl;
    private int index;

    HostType(int index, String url) {
        this.mUrl = url;
        this.index = index;
    }

    public static String getHosType(HostType hostType) {
        return hostType.mUrl;
    }

    public static int getIndex(HostType hostType) {
        return hostType.index;
    }
}
