package com.example.iningke.myapplication.testActivityStackStatusBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.iningke.myapplication.R;

public class Test1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test1;
    }

    public void goTo2(View view) {
        Intent intent = new Intent(this, Test2Activity.class);
        startActivity(intent);
    }
}
