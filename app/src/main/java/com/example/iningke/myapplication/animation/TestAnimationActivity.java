package com.example.iningke.myapplication.animation;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.iningke.myapplication.R;

public class TestAnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_animation);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.startAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_rotate));

    }


    public void testPropertyAnimator(View view) {
        Intent intent = new Intent(this, TestPropertyAnimationActivity.class);
        startActivity(intent);
    }
}
