package com.example.iningke.myapplication.annotion;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hxy on  2017/4/7.
 */
@Target(ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
@BaseEvent(setListenerMethodName = "setOnClickListener",
        listenerType = View.OnClickListener.class,
        listenerCallBackMethod = "onClick")
public @interface HxyOnClick {
    int[] value();//因为一个方法可能与多个控件绑定
}
