package com.example.iningke.myapplication.exlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.iningke.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * 类似QQ分组的 可展开 收缩的控件
 *
 * @author hxy
 * @date 2017/2/18
 */
public class MyExpandableListViewActivity extends AppCompatActivity implements MyExListViewAdapter.MyDeleteOnClickListener {
    private ExpandableListView exListView;
    private List<String> dataSource_parent = new ArrayList<>();
    private List<List<String>> dataSource_child = new ArrayList<>();
    private MyExListViewAdapter adapter;
    private int expandPosition = -1;//上一个被展开的位置；用来实现，同一时间只有一条展开。

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_expandable_list_view);
        initView();
        initData();
    }

    private void initData() {

        for (int i = 0; i < 4; i++) {
            dataSource_parent.add(i + "");
            dataSource_child.add(new ArrayList<String>());
            for (int j = 1; j < 20; j++) {
                dataSource_child.get(i).add("xxx" + j);
            }
            //设置某一个组 展开
            exListView.expandGroup(i);
        }
        adapter.notifyDataSetChanged();

    }

    private void initView() {
        exListView = (ExpandableListView) findViewById(R.id.expandListView);
        adapter = new MyExListViewAdapter(dataSource_parent, dataSource_child, this);
        exListView.setAdapter(adapter);

        exListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyExpandableListViewActivity.this, "Item点击了===》" + position, Toast.LENGTH_SHORT).show();
            }
        });
        exListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MyExpandableListViewActivity.this,
                        "子item点击了---->" + dataSource_child.get(groupPosition).get(childPosition), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        //        设置这个点击事件会屏蔽自带的展开和缩回去的效果
        exListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(MyExpandableListViewActivity.this, "Group被点击了====>" + groupPosition, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        /*用来实现同一时间 只有一条分组展开*/
//        exListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
//            //某一条缩回去监听事件
//            @Override
//            public void onGroupCollapse(int groupPosition) {
//                expandPosition = -1;
//                LogUtils.e("第" + groupPosition + "个缩回去了");
//            }
//        });
//        exListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
//            //某一条展开的监听事件
//            @Override
//            public void onGroupExpand(int groupPosition) {
//                if (expandPosition >= 0) {
//                    //设置某一条缩回去
//                    exListView.collapseGroup(expandPosition);
//                }
//                expandPosition = groupPosition;
//                LogUtils.e("第" + groupPosition + "个被展开了");
//            }
//        });
    }

    @Override
    public void onMyDeleteClick(View view, int groupPosition, int childPosition) {
        dataSource_child.get(groupPosition).remove(childPosition);
        adapter.notifyDataSetChanged();
    }
}
