package com.example.iningke.myapplication.gif;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import com.iningke.baseproject.utils.LogUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * 可缩放 可移动 imageview
 * Created by hxy on  2016/12/5.
 */
public class MyZoomImageView extends ImageView implements View.OnTouchListener,
        ScaleGestureDetector.OnScaleGestureListener, ViewTreeObserver.OnGlobalLayoutListener {
    private static final String TAG = MyZoomImageView.class.getSimpleName();

    public static float SCALE_MAX = 3.0f; //缩放最大比例
    public static float SCALE_MIN = 0.25f;//缩放最小比例
    public static float SCALE_MID = 1.5f;//缩放中间比例
    /**
     * 初始化时的缩放比例，如果图片宽或高大于屏幕，此值将小于0
     */
    private float initScale = 1.0f;

    /**
     * 用于存放矩阵的9个值
     */
    private final float[] matrixValues = new float[9];

    private boolean once = true;
    /**
     * 缩放的手势检测
     */
    private ScaleGestureDetector mScaleGestureDetector = null;

    private GestureDetector mGestureDetector;
    private final Matrix mScaleMatrix = new Matrix();

    public MyZoomImageView(Context context) {
        super(context);

    }

    private boolean isAutoScale;

    public MyZoomImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setScaleType(ScaleType.MATRIX);
        mScaleGestureDetector = new ScaleGestureDetector(context, this);
        mGestureDetector = new GestureDetector(context,
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public boolean onSingleTapConfirmed(MotionEvent e) {
                        //单击手势
                        EventBus.getDefault().post(new ZoomEvent());
                        return super.onSingleTapConfirmed(e);
                    }

                    @Override
                    public boolean onDoubleTap(MotionEvent e) {
                        //双击手势
                        if (isAutoScale)
                            return true;

                        float x = e.getX();
                        float y = e.getY();
                        Log.e("DoubleTap", getScale() + " , " + initScale);
                        if (getScale() < initScale && getScale() > SCALE_MIN) {
                            MyZoomImageView.this.postDelayed(
                                    new AutoScaleRunnable(initScale, x, y), 16);
                            isAutoScale = true;
                        } else if (getScale() >= initScale
                                && getScale() < SCALE_MAX) {
                            MyZoomImageView.this.postDelayed(
                                    new AutoScaleRunnable(SCALE_MAX, x, y), 16);
                            isAutoScale = true;
                        } else {
                            MyZoomImageView.this.postDelayed(
                                    new AutoScaleRunnable(initScale, x, y), 16);
                            isAutoScale = true;
                        }

                        return true;
                    }
                });
        //是一个距离，表示滑动的时候，手的移动要大于这个距离才开始移动控件。如果小于这个距离就不触发移动控件，如viewpager就是用这个距离来判断用户是否翻页
        mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        super.setScaleType(ScaleType.MATRIX);
        this.setOnTouchListener(this);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {

        super.setImageBitmap(bm);


    }

    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        //scale 当前 图片的缩放比例
        float scale = getScale();
        //手指滑动 的缩放比例
        float scaleFactor = detector.getScaleFactor();

        if (getDrawable() == null)
            return true;
        /**
         * 缩放的范围控制
         */
        if ((scale < SCALE_MAX && scaleFactor > 1.0f)
                || (scale > SCALE_MIN && scaleFactor < 1.0f)) {
            /**
             * 最大值最小值判断
             */
            if (scaleFactor * scale < SCALE_MIN) {
                scaleFactor = SCALE_MIN / scale;
            }
            if (scaleFactor * scale > SCALE_MAX) {
                scaleFactor = SCALE_MAX / scale;
            }
            /**
             * 设置缩放比例
             */
            mScaleMatrix.postScale(scaleFactor, scaleFactor, detector.getFocusX(),
                    detector.getFocusY());
            checkBorderAndCenterWhenScale();
            setImageMatrix(mScaleMatrix);
        }

        return true;

    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
    }

    private int lastPointerCount;
    private boolean isCanDrag;
    private float mLastX;
    private float mLastY;
    private boolean isCheckLeftAndRight;
    private boolean isCheckTopAndBottom;


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (mGestureDetector.onTouchEvent(event))
            return true;


        mScaleGestureDetector.onTouchEvent(event);

        float x = 0, y = 0;
        // 拿到触摸点的个数
        final int pointerCount = event.getPointerCount();
        // 得到多个触摸点的x与y均值
        for (int i = 0; i < pointerCount; i++) {
            x += event.getX(i);
            y += event.getY(i);
        }
        x = x / pointerCount;
        y = y / pointerCount;

        /**
         * 每当触摸点发生变化时，重置mLasX , mLastY
         */
        if (pointerCount != lastPointerCount) {
            isCanDrag = false;
            mLastX = x;
            mLastY = y;
        }


        lastPointerCount = pointerCount;

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                RectF rectF2 = getMatrixRectF();
                if (rectF2.width() > getWidth() || rectF2.height() > getHeight()) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e(TAG, "ACTION_MOVE");
                float dx = x - mLastX;
                float dy = y - mLastY;

                if (!isCanDrag) {
                    isCanDrag = isCanDrag(dx, dy);
                }
                if (isCanDrag) {
                    RectF rectF = getMatrixRectF();
                    if (getDrawable() != null) {
                        if (rectF.width() > getWidth() || rectF.height() > getHeight()) {
                            getParent().requestDisallowInterceptTouchEvent(true);
                        }
                        isCheckLeftAndRight = isCheckTopAndBottom = true;
                        // 如果宽度小于屏幕宽度，则禁止左右移动
                        if (rectF.width() < getWidth()) {
                            dx = 0;
                            isCheckLeftAndRight = false;
                        }
                        // 如果高度小雨屏幕高度，则禁止上下移动
                        if (rectF.height() < getHeight()) {
                            dy = 0;
                            isCheckTopAndBottom = false;
                        }
                        mScaleMatrix.postTranslate(dx, dy);
                        checkMatrixBounds();
                        setImageMatrix(mScaleMatrix);

                    }
                }
                mLastX = x;
                mLastY = y;
                break;

            case MotionEvent.ACTION_UP:
                float scale = getScale();
                if (scale < initScale) {
                    /**
                     * 如果手指抬起的时候 ， 当前图片的缩放比例 比初始化比例小，就设置当前图片为初始化比例。
                     * 设置缩放比例
                     */
//                    mScaleMatrix.postScale(initScale / scale, initScale / scale, x,
//                            y);
//                    checkBorderAndCenterWhenScale();
//                    setImageMatrix(mScaleMatrix);
                    MyZoomImageView.this.postDelayed(
                            new AutoScaleRunnable(initScale, x, y), 16);
                    isAutoScale = true;
                }
                Log.e(TAG, "ACTION_UP");
                lastPointerCount = 0;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.e(TAG, "ACTION_UP");
                lastPointerCount = 0;
                break;
        }

        return true;

    }

    /**
     * 移动时，进行边界判断，主要判断宽或高大于屏幕的
     */
    private void checkMatrixBounds() {
        RectF rect = getMatrixRectF();

        float deltaX = 0, deltaY = 0;
        final float viewWidth = getWidth();
        final float viewHeight = getHeight();
        // 判断移动或缩放后，图片显示是否超出屏幕边界
        if (rect.top > 0 && isCheckTopAndBottom) {
            deltaY = -rect.top;
        }
        if (rect.bottom < viewHeight && isCheckTopAndBottom) {
            deltaY = viewHeight - rect.bottom;
        }
        if (rect.left > 0 && isCheckLeftAndRight) {
            deltaX = -rect.left;
        }
        if (rect.right < viewWidth && isCheckLeftAndRight) {
            deltaX = viewWidth - rect.right;
        }
        mScaleMatrix.postTranslate(deltaX, deltaY);
    }

    private float mTouchSlop;

    /**
     * 是否是推动行为
     *
     * @param dx
     * @param dy
     * @return
     */
    private boolean isCanDrag(float dx, float dy) {
        return Math.sqrt((dx * dx) + (dy * dy)) >= mTouchSlop;
    }

    /**
     * 在缩放时，进行图片显示范围的控制
     */
    private void checkBorderAndCenterWhenScale() {

        RectF rect = getMatrixRectF();
        float deltaX = 0;
        float deltaY = 0;

        int width = getWidth();
        int height = getHeight();
        LogUtils.e("rect.width() ==" + rect.width() + "rect.height() == " + rect.height());

        // 如果宽或高大于屏幕，则控制范围
        if (rect.width() >= width) {
            if (rect.left > 0) {
                deltaX = -rect.left;
            }
            if (rect.right < width) {
                deltaX = width - rect.right;
            }
        }
        if (rect.height() >= height) {
            if (rect.top > 0) {
                deltaY = -rect.top;
            }
            if (rect.bottom < height) {
                deltaY = height - rect.bottom;
            }
        }
        // 如果宽或高小于屏幕，则让其居中
        if (rect.width() < width) {
            deltaX = width * 0.5f - rect.right + 0.5f * rect.width();
        }
        if (rect.height() < height) {
            deltaY = height * 0.5f - rect.bottom + 0.5f * rect.height();
        }
        Log.e(TAG, "deltaX = " + deltaX + " , deltaY = " + deltaY);

        mScaleMatrix.postTranslate(deltaX, deltaY);

    }

    /**
     * 根据当前图片的Matrix获得图片的范围
     *
     * @return
     */
    private RectF getMatrixRectF() {
        Matrix matrix = mScaleMatrix;
        RectF rect = new RectF();
        Drawable d = getDrawable();
        if (null != d) {
            rect.set(0, 0, d.getIntrinsicWidth(), d.getIntrinsicHeight());
            matrix.mapRect(rect);
        }
        return rect;
    }

    /**
     * 获得当前的缩放比例
     *
     * @return
     */
    public final float getScale() {
        mScaleMatrix.getValues(matrixValues);
        return matrixValues[Matrix.MSCALE_X];
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        SCALE_MAX = 3.0f; //缩放最大比例
        SCALE_MIN = 0.25f;//缩放最小比例
        SCALE_MID = 1.5f;//缩放中间比例
        getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        if (once) {
            Drawable d = getDrawable();
            if (d == null)
                return;
            Log.e(TAG, d.getIntrinsicWidth() + " , " + d.getIntrinsicHeight());
            int width = getWidth();
            int height = getHeight();
            Log.e(TAG, "width == " + width + " , height==" + height);
            // 拿到图片的宽和高
            int dw = d.getIntrinsicWidth();
            int dh = d.getIntrinsicHeight();
            float scale = 1.0f;
            // 如果图片的宽或者高大于屏幕，则缩放至屏幕的宽或者高
            if (dw > width && dh <= height) {
                scale = width * 1.0f / dw;
            }
            if (dh > height && dw <= width) {
                scale = height * 1.0f / dh;
            }
            // 如果宽和高都大于屏幕，则让其按按比例适应屏幕大小
            if (dw > width && dh > height) {
                scale = Math.min(width * 1.0f / dw, height * 1.0f / dh);
            }
            //如果图片的 宽和高都小于屏幕 ，则让 其按比例 放大。适应屏幕
            if (dw < width && dh < height) {
                scale = Math.min(width * 1.0f / dw, height * 1.0f / dh);
            }
            initScale = scale;
            LogUtils.e("initScale == " + initScale);
            SCALE_MIN *= initScale;
            SCALE_MID *= initScale;
            SCALE_MAX *= initScale;
            // 图片移动至屏幕中心
            mScaleMatrix.postTranslate((width - dw) / 2, (height - dh) / 2);
            mScaleMatrix
                    .postScale(scale, scale, getWidth() / 2, getHeight() / 2);
            setImageMatrix(mScaleMatrix);
            once = false;
        }
    }

    /**
     * 自动缩放的任务
     * 让缩放 不那么生硬， 缓慢的缩放。
     *
     * @author zhy
     */
    private class AutoScaleRunnable implements Runnable {
        static final float BIGGER = 1.07f;
        static final float SMALLER = 0.93f;
        private float mTargetScale;
        private float tmpScale;

        /**
         * 缩放的中心
         */
        private float x;
        private float y;

        /**
         * 传入目标缩放值，根据目标值与当前值，判断应该放大还是缩小
         *
         * @param targetScale
         */
        public AutoScaleRunnable(float targetScale, float x, float y) {
            this.mTargetScale = targetScale;
            this.x = x;
            this.y = y;
            if (getScale() < mTargetScale) {
                tmpScale = BIGGER;
            } else {
                tmpScale = SMALLER;
            }

        }

        @Override
        public void run() {
            // 进行缩放
            mScaleMatrix.postScale(tmpScale, tmpScale, x, y);
            LogUtils.e("tmpscale = " + tmpScale + "mTargetScale ==" + mTargetScale);
            checkBorderAndCenterWhenScale();
            setImageMatrix(mScaleMatrix);

            final float currentScale = getScale();
            //如果值在合法范围内，继续缩放
            if (((tmpScale > 1f) && (currentScale < mTargetScale))
                    || ((tmpScale < 1f) && (mTargetScale < currentScale))) {
                MyZoomImageView.this.postDelayed(this, 16);
            } else//设置为目标的缩放比例
            {
                final float deltaScale = mTargetScale / currentScale;
                mScaleMatrix.postScale(deltaScale, deltaScale, x, y);
                checkBorderAndCenterWhenScale();
                setImageMatrix(mScaleMatrix);
                isAutoScale = false;
            }

        }
    }

    public class ZoomEvent {

    }
}
