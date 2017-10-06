package com.example.hxy_baseproject.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.hxy_baseproject.utils.ActivityStack;

import butterknife.ButterKnife;

/**
 * 描述：activity基类
 * 作者：hxy on  2017/9/30 9:17.
 */

public abstract class BaseActivity<T extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    protected T mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(setLayoutId());
        ActivityStack.getScreenManager().pushActivity(this);
        ButterKnife.bind(this);
        initView();
    }

    /**
     * 控件的初始化
     */
    protected abstract void initView();


    protected void beforeSetContentView() {

    }

    /**
     * @return activity布局的layoutId
     */
    public abstract
    @LayoutRes
    int setLayoutId();

    @Override
    public void showProgress() {
        //TODO 实现网络加载的动画效果
    }

    @Override
    public void dismissProgress() {
        //TODO 实现网络加载的动画效果
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        ActivityStack.getScreenManager().popActivity(this);
    }

}
