package com.example.iningke.myapplication.testActivityStackStatusBar;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.iningke.myapplication.R;
import com.iningke.baseproject.utils.LogUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Test3Activity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    @Bind(R.id.linearContainer)
    LinearLayout linearContainer;
    @Bind(R.id.radioBtn1)
    RadioButton radioBtn1;
    @Bind(R.id.radioBtn2)
    RadioButton radioBtn2;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_test3;
    }

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;

    private void initView() {
        radioGroup.setOnCheckedChangeListener(this);
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        switchFragment(fragment1);
    }

    public void exit(View view) {
        ActivityStack.getScreenManager().clearAllActivity();
//        android.os.Process.killProcess(android.os.Process.myPid());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //fragment 切换
    private void switchFragment1(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            if (currentFragment != null) {
                transaction.hide(currentFragment);
            }
            transaction
                    .add(R.id.linearContainer, targetFragment)
                    .commit();
            LogUtils.e("还没添加呢" + targetFragment.getClass().getSimpleName());
        } else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
            LogUtils.e("添加了( ⊙o⊙ )哇" + targetFragment.getClass().getSimpleName());
        }
        currentFragment = targetFragment;
    }

    //fragment 切换
    private void switchFragment(Fragment targetFragment) {
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        transaction.replace(R.id.linearContainer, targetFragment).commit();
    }

    private Fragment currentFragment;

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId) {
            case R.id.radioBtn1:
                switchFragment(fragment1);
                break;
            case R.id.radioBtn2:
                switchFragment(fragment2);
                break;
            case R.id.radioBtn3:
                switchFragment(fragment3);
                break;
        }
    }
}
