package com.example.iningke.myapplication.practice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.iningke.myapplication.R;
import com.example.iningke.myapplication.adapter.HxyActivityAdapter;
import com.example.iningke.myapplication.bean.HxyActivityBean;
import com.example.iningke.myapplication.practice.paint.PaintShaderActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 自定义View之 Paint-详解 测试
 *
 * @author hxy
 * @since 2018/9/2 23:13
 */

public class PaintTestActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    @Bind(R.id.hxy_listView)
    ListView hxyListView;
    private HxyActivityAdapter adapter;
    private List<HxyActivityBean> dataSource = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paint_test);
        ButterKnife.bind(this);
        initView();
        setData();
    }

    private void setData() {
        dataSource.add(new HxyActivityBean("PaintShaderActivity", "测试Paint 的Shader"));

        adapter.notifyDataSetChanged();
    }

    private void initView() {
        adapter = new HxyActivityAdapter(dataSource);
        hxyListView.setAdapter(adapter);
        hxyListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, PaintShaderActivity.class));
                break;
            default:
                break;
        }
    }
}
