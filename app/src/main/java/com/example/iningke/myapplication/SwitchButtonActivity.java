package com.example.iningke.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.example.hxy_baseproject.utils.LogUtils;

public class SwitchButtonActivity extends AppCompatActivity {
    private Switch button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_button);
        initView();
    }

    private int i = 0;

    private void initView() {
        button = (Switch) findViewById(R.id.button);
        button.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LogUtils.e("ischecked " + isChecked);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                LogUtils.e("i = " + i);
                if (i % 3 == 0) {
                    button.setChecked(true);
                }
                if (i % 5 == 0) {
                    button.setChecked(false);
                }
            }
        });
    }

}
