package com.iningke.baseproject;

import android.content.Context;

import com.iningke.baseproject.utils.LogUtils;


/**
 * Created by gaonan on 15/9/22.
 */
public class BaseGlobalParams {
    //application上下文
    public static Context applicationContext;

    //主线程pid
    public static int mainPID = -1;
    //TODO Log输出级别  初始默认不输出log
    public static int Debuggable = LogUtils.LEVEL_NONE;
    //是否输入 toast
    public static boolean showToast = false;
}
