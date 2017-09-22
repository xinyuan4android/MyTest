package com.example.iningke.myapplication.gif;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.iningke.myapplication.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class GifActivity extends AppCompatActivity {

    private MyZoomImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif);
        initView();
        EventBus.getDefault().register(this);
    }

    private void initView() {
        imageView = (MyZoomImageView) findViewById(R.id.gif_imageView);
        Glide.with(this)
                .load(R.drawable.gif)
                .into(imageView);
//        ImagLoaderUtils.showImage("http://fanyi.baidu.com/static/translation/img/header/logo_cbfea26.png", imageView, R.mipmap.bg_fulldefault);
//        Glide.with(this)
//                .load("http://fanyi.baidu.com/static/translation/img/header/logo_cbfea26.png")
//                .into(imageView);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUserEvent(MyZoomImageView.ZoomEvent zoomEvent) {
        Log.e(getLocalClassName() + "", "finish");
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
