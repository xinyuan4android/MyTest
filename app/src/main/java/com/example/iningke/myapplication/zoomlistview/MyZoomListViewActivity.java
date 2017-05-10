package com.example.iningke.myapplication.zoomlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.iningke.myapplication.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 头部可以缩放的ListView
 *
 * @author hxy
 * @date 2017/4/12
 */
public class MyZoomListViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_zoom_list_view);
        initView();
    }

    private ImageView iv_header;
    private MyZoomListView listView;

    private void initView() {

        listView = (MyZoomListView) findViewById(R.id.myZoomListView);
        View headView = LayoutInflater.from(this).inflate(R.layout.header_listview, null);
        iv_header = (ImageView) headView.findViewById(R.id.iv_header);
        listView.setZoomView(iv_header);
        listView.addHeaderView(headView);

        List<String> dataSource = new ArrayList<>();
        Collections.addAll(dataSource,
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1",
                "item1");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, dataSource);
        listView.setAdapter(adapter);
    }
}
