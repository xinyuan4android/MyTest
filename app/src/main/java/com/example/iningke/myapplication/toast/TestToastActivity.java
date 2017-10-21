package com.example.iningke.myapplication.toast;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.iningke.myapplication.R;

/**
 * 测试Toast
 *
 * @author hxy
 * @since 2017/10/21 9:21
 */

public class TestToastActivity extends AppCompatActivity {

    private TextView content;
    private ToastUtil toastUtil;
    private ExToast exToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toast);
    }

    public void modifyBackground(View view) {
        ToastUtil toastUtil = new ToastUtil();
        toastUtil.Short(this, "自定义message字体、背景色")
                .setToastColor(Color.WHITE, getResources().getColor(R.color.colorAccent))
                .show();
    }

    private int num = 10;

    public void custom(View view) {
        exToast = ExToast.makeText(this, "10", 10000);
        View view1 = exToast.getView();
        content = (TextView) view1.findViewById(android.R.id.message);
        exToast.show();
        handler.sendEmptyMessageDelayed(100, 1000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            num -= 1;
            content.setText("" + num);
            if (num > 0) {
                handler.sendEmptyMessageDelayed(100, 1000);
            } else {
                exToast.hide();
            }
        }
    };
}
