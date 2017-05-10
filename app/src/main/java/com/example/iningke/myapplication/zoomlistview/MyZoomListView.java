package com.example.iningke.myapplication.zoomlistview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ListView;

import com.iningke.baseproject.utils.LogUtils;

/**
 * 头部可以缩放的ListView
 *
 * @author hxy
 * @date 2017/4/12
 */
public class MyZoomListView extends ListView {
    private ImageView headerView;

    public MyZoomListView(Context context) {
        super(context);
        init();
    }

    public MyZoomListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyZoomListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public ImageView getZoomView() {
        return headerView;
    }

    public void setZoomView(ImageView headerView) {
        this.headerView = headerView;
    }

    private void init() {
    }

    private int originWidth;
    private int originHeight;

    private boolean flag = true;
    private int currentHeight;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (flag) {
            flag = false;
            originHeight = headerView.getHeight();
            originWidth = headerView.getWidth();
            headerView.getLayoutParams().height = originHeight;
        }
    }

    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY,
                                   int scrollRangeX, int scrollRangeY, int maxOverScrollX,
                                   int maxOverScrollY, boolean isTouchEvent) {
        if (deltaY < 0) {
            LogUtils.e("delaY" + deltaY);
            currentHeight = originHeight - deltaY;
            LogUtils.e("currentHeight" + currentHeight);
            headerView.getLayoutParams().height = currentHeight;
//            ((View) (headerView.getParent())).layout(0, 0, originWidth, currentHeight);
            headerView.layout(0, 0, originHeight, currentHeight);
//            headerView.setLayoutParams(new LinearLayout.LayoutParams(originWidth, currentHeight));
            headerView.requestLayout();
        }
        return super.overScrollBy(deltaX, deltaY, scrollX, scrollY, scrollRangeX, scrollRangeY, maxOverScrollX, maxOverScrollY, isTouchEvent);
    }

//    @Override
//    public void onScrollStateChanged(AbsListView view, int scrollState) {
//
//    }
//
//    private int scrollHeight = 0;
//
//    @Override
//    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

//        View c = getChildAt(0);
//        if (c == null) {
//            scrollHeight = 0;
//            return;
//        }
//
//        int top = c.getTop();
//        scrollHeight = -top + firstVisibleItem * c.getHeight();
//        LogUtils.e("ScrollHeight==" + scrollHeight);
//        if (currentHeight - scrollHeight > originHeight) {
//            currentHeight -= scrollHeight;
//            headerView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
//                    currentHeight));
//            headerView.requestLayout();
//        }
//    }


}
