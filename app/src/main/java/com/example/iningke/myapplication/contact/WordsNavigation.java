package com.example.iningke.myapplication.contact;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by hxy on  2017/2/14.
 */
public class WordsNavigation extends View {
    /*绘制的列表导航字母*/
//    private String words[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
//            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};
    private String words[] = new String[]{};
    /*字母画笔*/
    private Paint wordsPaint;
    /*字母背景画笔*/
    private Paint bgPaint;
    /*每一个字母的宽度*/
    private int itemWidth;
    /*每一个字母的高度*/
    private int itemHeight;
    /*手指按下的字母索引*/
    private int touchIndex = 0;
    /*手指按下的字母改变接口*/
    private onWordsChangeListener listener;
    /*数据源中间的数*/
    private int middle = 13;

    public WordsNavigation(Context context) {
        super(context);
    }

    public WordsNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        wordsPaint = new Paint();
        bgPaint = new Paint();
    }

    public WordsNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (words.length <= 27) {
//            if (words.length % 2 == 1) {
//                //奇数
//                int middle = words.length / 2;
//            } else {
//                //偶数
//                int middle = words.length / 2;
//            }
            middle = words.length / 2;
        }
        for (int i = 0; i < words.length; i++) {
            //判断是不是我们按下的当前字母
//            int j = 13 - middle + i;
            if (touchIndex == i) {
                //绘制文字圆形背景
                canvas.drawCircle(itemWidth / 2, itemHeight / 2 + (i + 13 - middle) * itemHeight, 23, bgPaint);
                wordsPaint.setColor(Color.WHITE);
            } else {
                wordsPaint.setColor(Color.GRAY);
            }
            //获取文字的宽高
            Rect rect = new Rect();
            wordsPaint.getTextBounds(words[i], 0, 1, rect);
            wordsPaint.setTextSize(com.example.iningke.myapplication.utils.UIUtils.dip2px(15));
            int wordWidth = rect.width();
            //绘制字母
            float wordX = itemWidth / 2 - wordWidth / 2;
            float wordY = itemWidth / 2 + (i + 13 - middle) * itemHeight;
            canvas.drawText(words[i], wordX, wordY, wordsPaint);
        }
    }

    //得到画布的宽度和每一个字母所占的高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        itemWidth = getMeasuredWidth();
        //使得边距好看一些
        int height = getMeasuredHeight() - 10;
        itemHeight = height / 27;
    }

    /**
     * 当手指触摸按下的时候改变字母背景颜色
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                //关键点===获得我们按下的是那个索引(字母)
                int index = (int) (y / itemHeight);
                index = index - 13 + middle;
                index = index < 0 ? 0 : index;
                index = index >= words.length ? words.length - 1 : index;
                if (index != touchIndex)
                    touchIndex = index;
                //防止数组越界
                if (listener != null && 0 <= touchIndex && touchIndex <= words.length - 1) {
                    //回调按下的字母
//                    LogUtils.e("touchIndex===" + touchIndex);
                    listener.wordsChange(words[touchIndex]);
                }
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起,不做任何操作
                break;
        }
        return true;
    }

    /*手指按下了哪个字母的回调接口*/
    public interface onWordsChangeListener {
        void wordsChange(String words);
    }

    /*设置手指按下字母改变监听*/
    public void setOnWordsChangeListener(onWordsChangeListener listener) {
        this.listener = listener;
    }

    /*设置导航字母的数据源*/
    public void setWords(String[] words) {
        this.words = words;
    }
}
