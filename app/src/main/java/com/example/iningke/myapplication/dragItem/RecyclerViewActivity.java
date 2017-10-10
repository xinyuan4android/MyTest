package com.example.iningke.myapplication.dragItem;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iningke.myapplication.DividerItemDecoration;
import com.example.iningke.myapplication.R;
import com.example.iningke.myapplication.RListViewActivity;
import com.iningke.baseproject.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private HomeAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
        mRecyclerView.addItemDecoration(new DividerItemDecoration(this,
                DividerItemDecoration.VERTICAL_LIST));
        mAdapter.setListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Log.e("position", "position == " + postion);
                Toast.makeText(RecyclerViewActivity.this, "position == " + postion, Toast.LENGTH_SHORT).show();
            }
        });

        ItemTouchHelperCallBack callBack = new ItemTouchHelperCallBack(mAdapter, mDatas);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
//        mAdapter.setLongListener(new MyItemLongClickListener() {
//            @Override
//            public void onItemLongClick(View view, int position) {
//                LogUtils.e("deleteposition-- " + position);
//                mDatas.remove(position);
//                mAdapter.notifyItemRemoved(position);
////                mAdapter.notifyItemRangeRemoved(position, 0);
//            }
//        });
    }

    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
    }

    public void goToList(View view) {
        startActivity(new Intent(this, RListViewActivity.class));
    }

    class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

        private MyItemClickListener listener;
        private MyItemLongClickListener longListener;

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                    RecyclerViewActivity.this).inflate(R.layout.item_home, parent,
                    false), listener, longListener);

            return holder;
        }

        public MyItemLongClickListener getLongListener() {
            return longListener;
        }

        public void setLongListener(MyItemLongClickListener longListener) {
            this.longListener = longListener;
        }

        public void setListener(MyItemClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            LogUtils.e("onBindView Holder------------>" + position);
            holder.tv.setText(mDatas.get(position));
            LogUtils.e("===============================holder.delete.setonClickListener===============================");
//            holder.delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    LogUtils.e("holder.  delete  position --" + position);
//                    if (position >= mDatas.size()) {
//                        return;
//                    }
//                    mDatas.remove(position);
//                    notifyItemRemoved(position);
//                    notifyItemRangeChanged(position, mDatas.size() - position);
//                }
//            });
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
            private MyItemClickListener listener;
            private MyItemLongClickListener longListener;
            private TextView tv;
            private EditText et;
            private TextView delete;

            public MyViewHolder(View view, MyItemClickListener listener, MyItemLongClickListener longListener) {
                super(view);
                this.longListener = longListener;
                this.listener = listener;
                tv = (TextView) view.findViewById(R.id.id_num);
                et = (EditText) view.findViewById(R.id.item_edit);
                delete = (TextView) view.findViewById(R.id.delete);
                LogUtils.e("-------------------------------et .addTextChangedListener-------------------------------");
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        LogUtils.e("editText changed ===============>" + getAdapterPosition()
                                + "\n" + "================================>" + s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                view.setOnClickListener(this);
                view.setOnLongClickListener(this);
            }

            @Override
            public void onClick(View v) {
                listener.onItemClick(v, getAdapterPosition());
            }

            @Override
            public boolean onLongClick(View v) {
                if (longListener != null) {
                    longListener.onItemLongClick(v, getAdapterPosition());
                }
                return false;
            }
        }
    }

    public interface MyItemClickListener {
        void onItemClick(View view, int postion);
    }

    public interface MyItemLongClickListener {
        void onItemLongClick(View view, int position);
    }
}
