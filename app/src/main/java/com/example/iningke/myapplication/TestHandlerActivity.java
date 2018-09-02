package com.example.iningke.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.example.iningke.myapplication.view.MyCustomView;

/**
 * Handler
 *
 * @author hxy
 * @since 2018/5/24 9:24
 */
public class TestHandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_handler);
        LinearLayout ll = (LinearLayout) findViewById(R.id.ll_container);
        MyCustomView myCustomView = new MyCustomView(this);
        myCustomView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        ll.addView(myCustomView);

    }

}
