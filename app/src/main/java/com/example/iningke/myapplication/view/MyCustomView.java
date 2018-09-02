package com.example.iningke.myapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.hxy_baseproject.utils.LogUtils;


/**
 * @author :  hxy
 * @since :  2018/5/24 15:39.
 */

public class MyCustomView extends View implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener, View.OnTouchListener, View.OnClickListener {
    private GestureDetector mGestureDetector;

    public MyCustomView(Context context) {
        super(context);
        mGestureDetector = new GestureDetector(context, this);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        setOnTouchListener(this);
        setOnClickListener(this);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyCustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Paint paint;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(getLeft(), getTop(), getRight(), getBottom(), paint);
    }

    private int mLastX;
    private int mLastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event)) {
            return true;
        }
        int x = (int) event.getRawX();
        int y = (int) event.getRawY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                int deltaX = x - mLastX;
                int deltaY = y - mLastY;
                LogUtils.e("move" + deltaX + "--" + deltaY);
                setTranslationX(getTranslationX() + deltaX);
                setTranslationY(getTranslationY() + deltaY);
                break;
            case MotionEvent.ACTION_DOWN:
                LogUtils.e("MotionEvent.ACTION_DOWN");
                break;
            case MotionEvent.ACTION_UP:
                if (isClickable() || isLongClickable()) {
                    performClick();
                }
                break;
            default:
                break;
        }

        mLastX = x;
        mLastY = y;
        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        LogUtils.e("onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        LogUtils.e("onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        LogUtils.e("onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        LogUtils.e("onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        LogUtils.e("onLongPress");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        LogUtils.e("onFling");
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        LogUtils.e("onSingleTapConfirmed");
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        LogUtils.e("onDoubleTap");
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        LogUtils.e("onDoubleTapEvent");
        return true;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        LogUtils.e("onTouch" + event.getAction());
        return false;
    }

    @Override
    public void onClick(View v) {
        LogUtils.e("onClick");
    }
}
