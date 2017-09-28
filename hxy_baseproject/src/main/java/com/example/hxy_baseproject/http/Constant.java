package com.example.hxy_baseproject.http;

/**
 * Created by lts on 2017/8/28.
 * Fuction: 一些常量类
 * Update:
 */

public final class Constant {

    public static final String LOCALE = "locale";
    public static final String VIDEO_NAME = "video_name";
    public static final String VIDEO_KEY = "video_key";

    private Constant(){}


//    public static final String HOST_URL = "https://api.themoviedb.org/3/";
    public static final String HOST_URL = "http://116.62.185.223/yishang/f/";

    public static final String APP_NAME = "movie";

    public static final String api_key = "ad83894ead3e1348a6d86dc37abfd695";

    public static final String movie_news_db= "movie_news.db";
    public static final String movie_list_type = "movie_list_type";
    public static final String image_base_url = "https://image.tmdb.org/t/p/";
    public static final String backdrop_size =  "w300";
    public static final String logo_size =  "w185";
    public static final String logUrl = image_base_url + logo_size;
    public static final String backgoundUrl = image_base_url + backdrop_size;
    public static final String movie_id = "movie_id";
//    public static final String LANGUGE = App.getContext().getResources().getString(R.string.language);


    /**
     * Please replace this with a valid API key which is enabled for the
     * YouTube Data API v3 service. Go to the
     * <a href="https://console.developers.google.com/">Google Developers Console</a>
     * to register a new developer key.
     */
    public static final String YOUTUBE_KEY ="AIzaSyAo7ytUfuG25wAfIxyIxx865FZrIhkTJWI";
}
