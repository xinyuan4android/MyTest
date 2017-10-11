package com.example.iningke.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.iningke.baseproject.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RListViewActivity extends AppCompatActivity {
    private ListView listView;
    private List<String> dataSource = new ArrayList<>();

    private ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rlist_view);
        initData();
        initView();
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.listview);
        adapter = new ListAdapter();
        listView.setAdapter(adapter);
    }

    protected void initData() {
        dataSource = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            dataSource.add("" + (char) i);
        }
    }

    public class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (dataSource != null) {
                return dataSource.size();
            }
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view = null;
            ViewHolder holder = null;
            if (convertView == null) {
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, null);
                holder = new ViewHolder(view);
                view.setTag(holder);
//                holder.idNum.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        LogUtils.e("holder  . Num  delete position =" + position);
//                        dataSource.remove(position);
//                        notifyDataSetChanged();
//                    }
//                });
            } else {
                view = convertView;
                holder = (ViewHolder) view.getTag();
            }
            holder.idNum.setText(dataSource.get(position));
            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LogUtils.e("delete ==" + position);
                    dataSource.remove(position);
                    notifyDataSetChanged();
                }
            });
            return view;
        }

        public class ViewHolder {
            @Bind(R.id.id_num)
            TextView idNum;
            @Bind(R.id.delete)
            TextView delete;
            @Bind(R.id.item_edit)
            EditText itemEdit;

            ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
