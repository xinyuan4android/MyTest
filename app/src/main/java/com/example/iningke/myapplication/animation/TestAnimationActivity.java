package com.example.iningke.myapplication.animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.example.iningke.myapplication.R;

public class TestAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);
        Button button = (Button) findViewById(R.id.button);
        button.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_rotate));

    }
}
