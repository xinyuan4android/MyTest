package com.example.iningke.myapplication.exlistview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.example.iningke.myapplication.R;

import java.util.List;

/**
 * Created by hxy on  2017/1/9.
 */
public class MyExListViewAdapter extends BaseExpandableListAdapter implements View.OnClickListener {
    private List<String> dataSource_parent;
    private List<List<String>> dataSource_child;
    private MyDeleteOnClickListener listener;

    public MyExListViewAdapter(List<String> dataSource_parent, List<List<String>> dataSource_child) {
        this.dataSource_parent = dataSource_parent;
        this.dataSource_child = dataSource_child;
    }

    public MyExListViewAdapter(List<String> dataSource_parent, List<List<String>> dataSource_child, MyDeleteOnClickListener listener) {
        this(dataSource_parent, dataSource_child);
        this.listener = listener;
    }

    /**
     * 获得父项的数量
     *
     * @return
     */
    @Override

    public int getGroupCount() {
        if (dataSource_parent != null) {
            return dataSource_parent.size();
        }
        return 0;
    }

    /**
     * 获得某个父项的子项数目
     *
     * @param groupPosition
     * @return
     */
    @Override
    public int getChildrenCount(int groupPosition) {
        if (dataSource_child != null && dataSource_child.get(groupPosition) != null) {

            return dataSource_child.get(groupPosition).size();
        }
        return 0;
    }

    /**
     * 获得某个父项
     *
     * @param groupPosition
     * @return
     */
    @Override
    public Object getGroup(int groupPosition) {
        return dataSource_parent.get(groupPosition);
    }

    /**
     * 获得某个父项的某个子项
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return dataSource_child.get(groupPosition).get(childPosition);
    }

    /**
     * //  获得某个父项的id
     *
     * @param groupPosition
     * @return
     */
    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    /**
     * 获得某个父项的某个子项的id
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    /**
     * 按函数的名字来理解应该是是否具有稳定的id，这个方法目前一直都是返回false，没有去改动过
     *
     * @return
     */
    @Override
    public boolean hasStableIds() {
        return false;
    }

    /**
     * 获得父项显示的view
     *
     * @param groupPosition
     * @param isExpanded
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exlistview_parent, null);
        }
        convertView.setTag(R.layout.item_exlistview_parent, groupPosition);
        convertView.setTag(R.layout.item_exlistview_child, -1);
        TextView textView = (TextView) convertView.findViewById(R.id.item_exListView_parentTxt);
        textView.setText(dataSource_parent.get(groupPosition));
        return convertView;
    }

    /**
     * 获得子项显示的view
     *
     * @param groupPosition
     * @param childPosition
     * @param isLastChild
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exlistview_child, null);
        }
        convertView.setTag(R.layout.item_exlistview_parent, groupPosition);
        convertView.setTag(R.layout.item_exlistview_child, childPosition);
        TextView textView = (TextView) convertView.findViewById(R.id.item_exListView_childTxt);
        textView.setText(dataSource_child.get(groupPosition).get(childPosition));
        TextView delete = (TextView) convertView.findViewById(R.id.item_exListView_childDelete);
        delete.setTag(R.layout.item_exlistview_parent, groupPosition);
        delete.setTag(R.layout.item_exlistview_child, childPosition);
        delete.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag(R.layout.item_exlistview_child) != null && v.getTag(R.layout.item_exlistview_child) instanceof Integer
                && v.getTag(R.layout.item_exlistview_parent) != null && v.getTag(R.layout.item_exlistview_parent) instanceof Integer) {
            int childPosition = (int) v.getTag(R.layout.item_exlistview_child);
            int groupPosition = (int) v.getTag(R.layout.item_exlistview_parent);
            if (listener != null) {
                listener.onMyDeleteClick(v, groupPosition, childPosition);
            }
        }
    }

    public interface MyDeleteOnClickListener {
        void onMyDeleteClick(View view, int groupPosition, int childPosition);
    }

    /**
     * 子项是否可选中，如果需要设置子项的点击事件，需要返回true
     *
     * @param groupPosition
     * @param childPosition
     * @return
     */
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
