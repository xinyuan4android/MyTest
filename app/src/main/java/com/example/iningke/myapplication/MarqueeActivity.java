package com.example.iningke.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;

/**
 * 跑马等效果
 *
 * @author hxy
 * @date 2017/2/14
 */
public class MarqueeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee);
        initVIew();
    }

    private ScrollForeverTextView textView;

    private void initVIew() {
        textView = (ScrollForeverTextView) findViewById(R.id.scrollTextView);
//      marquee.setText(String.format(getResources().getString(R.string.marquee0),Consts.termno,"2010-12-28"));
//        textView.setText("上证指数3000.15 6.81(0.37%)深圳成指3000.15 6.81(0.37%)");
//      marquee.setTextColor(0xffff0000);//注意：颜色必须在这里设置，xml中设置无效！默认黑色。
        //如果想改变跑马灯的文字内容或者文字效果，则在调用完setText方法之后，需要再调用一下init(width)方法，重新进行初始化和相关参数的计算。
        textView.setSpeed(2.0f);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        //宽度height = dm.heightPixels ;//高度
        textView.init(dm.widthPixels);//width通常就是屏幕宽！
        textView.startScroll();
    }


}
