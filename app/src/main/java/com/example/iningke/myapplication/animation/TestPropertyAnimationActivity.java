package com.example.iningke.myapplication.animation;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.iningke.myapplication.R;
import com.example.iningke.myapplication.utils.UIUtils;

public class TestPropertyAnimationActivity extends AppCompatActivity {
    private ImageView imageView;
    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_property_animation);
        imageView = (ImageView) findViewById(R.id.imageView);
        setAnimator();
        Button button1 = (Button) findViewById(R.id.btn1);
        Button button2 = (Button) findViewById(R.id.btn2);
        Button button3 = (Button) findViewById(R.id.btn3);
        Button button4 = (Button) findViewById(R.id.btn4);
        Button button5 = (Button) findViewById(R.id.btn5);
        Button button6 = (Button) findViewById(R.id.btn6);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);

        setTranslationAnimator(button1);
//        setTranslationAnimator(button2);
//        setTranslationAnimator(button3);
//        setTranslationAnimator(button4);
//        setTranslationAnimator(button5);
//        setTranslationAnimator(button6);
    }

    public void setTranslationAnimator(final View view) {
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            relativeLayout.getChildAt(i).setVisibility(View.INVISIBLE);
            relativeLayout.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
            //最后表示其实坐标的参数的单位为 px像素。
            ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(relativeLayout.getChildAt(i), "translationY", UIUtils.dip2px(relativeLayout.getMeasuredHeight()), 0)
                    .setDuration(1000);
            objectAnimator.setStartDelay(500 * i);
            final int finalI = i;
            objectAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    //动画开始前
                    relativeLayout.getChildAt(finalI).setVisibility(View.VISIBLE);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    //动画结束
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    //动画取消
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    //动画进行中
                }
            });
            objectAnimator.start();
        }

    }

    /**
     * ObjectAnimator类提供了ofInt、ofFloat、ofObject这个三个常用的方法，
     * 这些方法都是设置动画作用的元素、属性、开始、结束等任意属性值。
     * 当属性值（上面方法的参数）只设置一个时就把通过getXXX反射获取的值作为起点，
     * 设置的值作为终点；如果设置两个（参数），那么一个是开始、另一个是结束。
     * 特别注意：ObjectAnimator的动画原理是不停的调用setXXX方法更新属性值，
     * 所有使用ObjectAnimator更新属性时的前提是Object必须声明有getXXX和setXXX方法。
     */
    public void setAnimator() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                ObjectAnimator.ofFloat(imageView, "rotationY", 0.0f, 1440.0f),
                ObjectAnimator.ofFloat(imageView, "rotationX", 0.0f, 1440.0f),
                ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 1440.0f)
        );
        animatorSet.setDuration(20000).start();

//        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0.0f, 1440.0f);
////        objectAnimator.ofFloat(imageView,"rotationY",0.0f,360.0f);
//        objectAnimator.setRepeatCount(-1);
//        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
//        objectAnimator.setDuration(20000)
//                .start();
    }

    public void showProgressBar(View view) {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
}
