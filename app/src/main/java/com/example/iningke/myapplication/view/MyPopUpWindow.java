package com.example.iningke.myapplication.view;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.LayoutRes;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.example.iningke.myapplication.R;

/**
 * Created by hxy on  2017/3/4.
 */
public class MyPopUpWindow extends PopupWindow {
    private PopupWindow popupWindow;
    private View contentView;

    public PopupWindow initPopupWindow(Context context, @LayoutRes int parentId) {
        contentView = LayoutInflater.from(context).inflate(R.layout.activity_go_to_system_contact, null);
        popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        contentView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK)
                    popupWindow.dismiss();
                return false;
            }
        });
        View parent = LayoutInflater.from(context).inflate(parentId, null);
        popupWindow.showAtLocation(new View(context), Gravity.BOTTOM, 0, 0);
        return popupWindow;
    }
}
