package com.example.iningke.myapplication.testActivityStackStatusBar;


import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.example.iningke.myapplication.R;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends BaseFragment {


    @Bind(R.id.textView1)
    TextView textView1;
    @Bind(R.id.textView2)
    TextView textView2;
    @Bind(R.id.textView3)
    TextView textView3;
    @Bind(R.id.textView4)
    TextView textView4;
    @Bind(R.id.textView5)
    TextView textView5;

    public Fragment1() {
        // Required empty public constructor
    }


    @Override
    public int bindingLayout() {
        return R.layout.fragment_fragment1;
    }

    @Override
    public void initView(View view) {

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}
