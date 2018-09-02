package com.example.hxy_baseproject.base;


import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment<T extends IBasePresenter> extends Fragment implements IBaseView {
    private View mFragmentRootView;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this layouts.fragment
        mFragmentRootView = inflater.inflate(bindingLayout(), container, false);
        ButterKnife.bind(mFragmentRootView);
        initView();
        return mFragmentRootView;
    }

    //初始化控件
    protected abstract void initView();


    /**
     * @return 设置Fragment的布局id
     */
    public abstract
    @LayoutRes
    int bindingLayout();

    @Override
    public void showProgress() {
        //TODO 实现网络加载的动画效果
    }

    @Override
    public void dismissProgress() {
        //TODO 实现网络加载的动画效果
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewGroup parent = (ViewGroup) mFragmentRootView.getParent();
        if (null != parent) {
            parent.removeView(mFragmentRootView);
            ButterKnife.unbind(this);
        }
    }

}
