package com.iningke.baseproject.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.iningke.baseproject.BaseApp;

/**
 * 创建者：gaonan
 * 时间：2015/11/13 16:33
 * 类描述：
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class UIUtils {
    /**
     * 全局上下文对象
     *
     * @return 上下文
     */
    public static Context getContext() {
        return BaseApp.getApplication();
    }

    public static Thread getMainThread() {
        return BaseApp.getMainThread();
    }

    public static long getMainThreadId() {
        return BaseApp.getMainThreadId();
    }

    /**
     * dip转换px
     */
    public static int dip2px(int dip) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dip * scale + 0.5f);
    }

    /**
     * pxz转换dip
     */
    public static int px2dip(int px) {
        final float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (px / scale + 0.5f);
    }

    /**
     * 获取主线程的handler
     */
    public static Handler getHandler() {
        // 获得主线程的looper
        Looper mainLooper = BaseApp.getMainThreadLooper();
        // 获取主线程的handler
        Handler handler = new Handler(mainLooper);
        return handler;
    }

    /**
     * 延时在主线程执行runnable
     */
    public static boolean postDelayed(Runnable runnable, long delayMillis) {
        return getHandler().postDelayed(runnable, delayMillis);
    }

    /**
     * 在主线程执行runnable
     */
    public static boolean post(Runnable runnable) {
        return getHandler().post(runnable);
    }

    /**
     * 从主线程looper里面移除runnable
     */
    public static void removeCallbacks(Runnable runnable) {
        getHandler().removeCallbacks(runnable);
    }

    public static View inflate(int resId) {
        return LayoutInflater.from(getContext()).inflate(resId, null);
    }

    /**
     * 获取资源
     */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /**
     * 获取文字
     */
    public static String getString(int resId) {
        try {
            return getResources().getString(resId);
        }catch (Exception e){
            return resId+"";
        }

    }

    /**
     * 获取文字数组
     */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取dimen
     */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取drawable
     */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /**
     * 获取颜色
     */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

    /**
     * 获取颜色选择器
     */
    public static ColorStateList getColorStateList(int resId) {
        return getResources().getColorStateList(resId);
    }

    public static boolean isRunInMainThread() {
        return android.os.Process.myTid() == getMainThreadId();
    }

    public static void runInMainThread(Runnable runnable) {
        if (isRunInMainThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final int resId) {
        showToastSafe(getString(resId));
    }

    /**
     * 对toast的简易封装。线程安全，可以在非UI线程调用。
     */
    public static void showToastSafe(final Object str) {
        if (isRunInMainThread()) {
            showToast(str.toString());
        } else {
            post(new Runnable() {
                @Override
                public void run() {
                    showToast(str.toString());
                }
            });
        }
    }

    /**
     * 通知栏高度
     *
     * @param context
     * @return
     */
    // public static int getNotifacationBarHeight(Context context) {
    // DisplayMetrics outMetrics = new DisplayMetrics();
    // ((Activity)
    // context).getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
    // int totalHeight = outMetrics.heightPixels;
    // Rect frame = new Rect();
    // ((BaseActivity)
    // GlobalParams.BASE_CONTEXT).getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
    // int noNotiBarHeight = frame.height();
    // return totalHeight - noNotiBarHeight;
    // }

    /**
     * 屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getDisplayWidth(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    public static int getDisplayHeight(Context context) {
        DisplayMetrics outMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    private static void showToast(String str) {
        Toast.makeText(getContext(), str, Toast.LENGTH_SHORT).show();
    }

    public static void showToastSonThread(final Activity activity,final String text) {
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
            }
        });
    }

	/*
	 * public static String toDBC(String input) { char[] c =
	 * input.toCharArray(); for (int i = 0; i < c.length; i++) { if (c[i] ==
	 * 12288) { c[i] = (char) 32; continue; } if (c[i] > 65280 && c[i] < 65375)
	 * c[i] = (char) (c[i] - 65248); } return new String(c); }
	 */


    /**
     * 隐藏小键盘的方法
     */
    public static void goneKeyboard(Context activity,View editText) {
        InputMethodManager imm = (InputMethodManager) activity
                .getSystemService(Context.INPUT_METHOD_SERVICE);// 控制小键盘的那个类
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0); // 强制隐藏键盘
    }

//    /**
//     * 多线程执行任务
//     * @param runTask 执行的任务
//     */
//    public static void startTaskInThreadPool(Runnable runTask){
//        ThreadManager.getInstance().createHelper().execute(runTask);
//    }
}