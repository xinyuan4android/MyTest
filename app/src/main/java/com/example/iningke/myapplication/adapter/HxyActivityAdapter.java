package com.example.iningke.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.iningke.myapplication.R;
import com.example.iningke.myapplication.bean.HxyActivityBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by hxy on  2017/3/4.
 */
public class HxyActivityAdapter extends BaseAdapter {
    private List<HxyActivityBean> dataSource;

    public HxyActivityAdapter(List<HxyActivityBean> dataSource) {
        this.dataSource = dataSource;
    }

    private int count = 0;

    @Override
    public int getCount() {
        if (dataSource != null) {
            return dataSource.size();
        }
        return 0;
//        return count;
    }

    public void setCount(int count) {
        this.count = count;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        ViewHolder holder = null;
        if (convertView == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hxy_activity, null);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        holder.itemHxyContent.setText(dataSource.get(position).getContent());
        holder.itemHxyTitle.setText(dataSource.get(position).getTitle());
        return view;
    }

    static class ViewHolder {
        @Bind(R.id.item_hxy_title)
        TextView itemHxyTitle;
        @Bind(R.id.item_hxy_content)
        TextView itemHxyContent;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
