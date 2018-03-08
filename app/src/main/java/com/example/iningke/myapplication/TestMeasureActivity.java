package com.example.iningke.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * 测试 measure
 *
 * @author hxy
 * @since 2017/12/20 18:01
 */

public class TestMeasureActivity extends AppCompatActivity {
    private LinearLayout ll_container;
    private ImageView ivEnterprise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_measure);
        initView();
    }

    private void initView() {
        ll_container = (LinearLayout) findViewById(R.id.ll_container);
        ivEnterprise = (ImageView) findViewById(R.id.iv_enterprise);

        ll_container.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
//        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.EXACTLY);
//        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
//        ll_container.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//        ll_container.measure(widthMeasureSpec, heightMeasureSpec);
        int measuredHeight = ll_container.getMeasuredHeight();
        ViewGroup.LayoutParams layoutParams = ivEnterprise.getLayoutParams();
        layoutParams.height = measuredHeight;
        layoutParams.width = measuredHeight;

        ivEnterprise.setImageResource(R.mipmap.bg_guide1);
    }
}
