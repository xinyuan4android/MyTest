package com.example.iningke.myapplication.annotion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by hxy on  2017/4/7.
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BaseEvent {
    String setListenerMethodName();//设置监听的方法名

    Class listenerType();//监听的类型

    String listenerCallBackMethod();//监听的回调方法名
}
