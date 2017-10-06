package com.example.iningke.myapplication.testActivityStackStatusBar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import com.example.iningke.myapplication.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {

    public View view = null;
    @Bind(R.id.text1)
    TextView text1;
    @Bind(R.id.text2)
    TextView text2;
    @Bind(R.id.text3)
    TextView text3;
    @Bind(R.id.text4)
    TextView text4;
    @Bind(R.id.text5)
    TextView text5;

    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (view != null) {
            ViewGroup parent = null;
            ViewParent v = view.getParent();
            if (v instanceof ViewGroup) {
                parent = (ViewGroup) v;
            }
            if (null != parent) {
                parent.removeView(view);
            }
        } else {
            view = inflater.inflate(setLayoutId(), container, false);
            ButterKnife.bind(this, view);
            initView(view);
            addListener();
            initData(view);
        }
        ButterKnife.bind(this, view);
        return view;
    }

    /**
     * 添加监听器
     *
     * @return
     */
    public void addListener() {
    }

    /**
     * 设置布局id
     *
     * @return
     */
    public int setLayoutId() {
        return R.layout.fragment_fragment2;
    }


    /**
     * 初始化师徒
     *
     * @return
     */
    public void initView(View view) {

    }

    /**
     * 加载数据
     *
     * @return
     */
    public void initData(View view) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
