package com.example.iningke.myapplication.annotion;

import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hxy on  2017/4/8.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@BaseEvent(setListenerMethodName = "setOnLongClickListener",
        listenerType = View.OnLongClickListener.class,
        listenerCallBackMethod = "onLongClick")
public @interface HxyOnLongClick {
    int[] value();
}
