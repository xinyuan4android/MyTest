package com.iningke.baseproject.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.iningke.baseproject.R;

/**
 * Created by iningke on 2016/6/4.
 */

public class LoadingDialog extends Dialog implements DialogInterface.OnDismissListener {
    private LayoutInflater inflater;
    private OnDismissListener listener;
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.sendEmptyMessage(0);
        }
    };
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (current_page==3){
                current_page=0;
            }else {
                current_page++;
            }
            imageView.setImageResource(pageID[current_page]);
            handler.removeCallbacks(runnable);
            handler.postDelayed(runnable,500);
            return false;
        }
    });
    public LoadingDialog(Context context) {
        super(context);
        inflater = LayoutInflater.from(context);
    }

    public LoadingDialog(Context context, int themeResId) {
        super(context, themeResId);
        inflater = LayoutInflater.from(context);
    }
    ImageView imageView ;
    private int current_page = 0;
    private int[] pageID = {R.drawable.loding0,R.drawable.loding1,R.drawable.loding2,R.drawable.loding3};
    public void showDialog(OnDismissListener listener){
        if (this!=null&&this.isShowing()){
            dismiss();
        }
        current_page = 0;
        View contentview = inflater.inflate(R.layout.dialog,null);
        setContentView(contentview);
        setCancelable(false);
        imageView = (ImageView) contentview.findViewById(R.id.loading_imag);
        setOnDismissListener(this);
        this.listener = listener;
        show();
        handler.sendEmptyMessage(0);
    }


    @Override
    public void onDismiss(DialogInterface dialog) {
        handler.removeCallbacks(runnable);
        if (listener!=null){
            listener.onDismiss(dialog);
        }
    }
}
