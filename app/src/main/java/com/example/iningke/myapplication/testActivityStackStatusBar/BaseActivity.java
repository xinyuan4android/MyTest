package com.example.iningke.myapplication.testActivityStackStatusBar;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.iningke.baseproject.utils.LogUtils;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityStack.getScreenManager().pushActivity(this);
        setContentView(getLayoutResId());

        //想要实现 透明/半透明 状态栏的效果 Android版本必须大于4.4

        setStatusBarColor(0xFFFF00FF);
    }

    protected abstract int getLayoutResId();

    //改变状态栏颜色
    private void setStatusBarColor(int color) {


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //android API5.0以上
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(color);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ViewGroup contentView = (ViewGroup) findViewById(android.R.id.content);
            if (contentView == null) {
                return;
            }
            View parentView = contentView.getChildAt(0);
            parentView.setFitsSystemWindows(true);
            //android API4.4-5.0
            //首先设置 状态栏 透明
            steepStatusBar();
            //实现改变状态栏颜色方法
            //方法1.直接在 加载布局文件的 父容器中（id为content的父控件）来动态添加一个和状态栏高度一样的view
            int defaultColor = 0xFFFF00FF;
            View statusBarView = new View(this);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    getStatusBarHeight(this));
            statusBarView.setBackgroundColor(defaultColor);
            contentView.addView(statusBarView, lp);
            //方法2：在布局文件中添加一个view 然后，给他动态设置高度和visible
//            View viewById = findViewById(R.id.statusBarView);
//            if (viewById != null) {
//                viewById.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, getStatusBarHeight(this)));
//                viewById.setBackgroundColor(color);
//                viewById.setVisibility(View.VISIBLE);
//            }

        }

    }

    //设置透明
    private void steepStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 透明状态栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    //获取导航栏的高度,得到的值是像素
    public float getNavigationBarHeight() {
        float result = 0;
        int resourceId = getResources().getIdentifier("navigation_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = getResources().getDimension(resourceId);
        }
        return result;
    }

    //获取状态栏的高度,得到的值是像素
    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        LogUtils.e("status bar height" + result);
        return result;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityStack.getScreenManager().popActivity(this);
    }
}
