package com.example.iningke.myapplication.ndk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.iningke.myapplication.R;
import com.js2java.ndk.NDKUtils;

public class NDKHelloWorldActivity extends AppCompatActivity {
    private TextView tv_ndk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ndkhello_world);
        tv_ndk = (TextView) findViewById(R.id.tv_ndk);
        NDKUtils ndkUtils = new NDKUtils();
        tv_ndk.setText(ndkUtils.getStringFromNDK());
    }
}
