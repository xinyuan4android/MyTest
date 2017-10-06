package com.example.iningke.myapplication.testActivityStackStatusBar;


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
public abstract class BaseFragment extends Fragment {
    private View mFragmentRootView;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mFragmentRootView = inflater.inflate(bindingLayout(), container, false);
        ButterKnife.bind(mFragmentRootView);
        return mFragmentRootView;
    }

    public abstract
    @LayoutRes
    int bindingLayout();

    public abstract void initView(View view);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ViewGroup parent = (ViewGroup) mFragmentRootView.getParent();
        if (null != parent) {
            ButterKnife.unbind(mFragmentRootView);
            parent.removeView(mFragmentRootView);
        }
    }
}
