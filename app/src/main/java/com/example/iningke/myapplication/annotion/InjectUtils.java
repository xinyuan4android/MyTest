package com.example.iningke.myapplication.annotion;

import android.content.Context;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hxy on  2017/4/7.
 */
public class InjectUtils {


    public static void injectAll(Context context) {
        injectContentView(context);
        injectView(context);
        injectClick(context);
    }

    private static void injectClick(Context context) {
        //获取context 对应的Class
        Class<? extends Context> clazz = context.getClass();

        //获取Class 所有的方法
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {

            Annotation[] annotations = method.getAnnotations();

            for (Annotation annotation : annotations) {

                Class<? extends Annotation> annotationType = annotation.annotationType();

                if (annotationType != null) {
                    BaseEvent baseEvent = annotationType.getAnnotation(BaseEvent.class);

                    if (baseEvent != null) {
                        String setListenerMethod = baseEvent.setListenerMethodName();
                        Class listenerType = baseEvent.listenerType();
                        String callBackMethod = baseEvent.listenerCallBackMethod();

                        try {
                            Method value = annotationType.getDeclaredMethod("value");

                            int[] ids = (int[]) value.invoke(annotation);

                            InjectInvocationHandler invocationHandler = new InjectInvocationHandler(context);

                            invocationHandler.add(callBackMethod, method);

                            Proxy proxy = (Proxy) Proxy.newProxyInstance(listenerType.getClassLoader(), new Class[]{listenerType}, invocationHandler);

                            for (int id : ids) {
                                //用反射获取 clazz类中的 findViewById 方法
                                Method method2 = clazz.getMethod("findViewById", int.class);
                                View view = (View) method2.invoke(context, id);
                                //通过反射获取方法
                                Method listener = view.getClass().getMethod(setListenerMethod, listenerType);
                                //执行方法
                                listener.invoke(view, proxy);

                            }
                        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

    private static void injectContentView(Context context) {
        //通过反射获取 获取context对象 对应的Clazz
        Class<? extends Context> clazz = context.getClass();

        //获取 context对应 的InjectContentView注解
        InjectContentView annotation = clazz.getAnnotation(InjectContentView.class);

        if (annotation != null) {
            //获取注解传入的值 也就是（R.layout.xxx）
            int value = annotation.value();

            try {
                //通过反射获取 context对应Clazz里 的setContentView（int layoutId）方法
                Method method = clazz.getMethod("setContentView", int.class);

                //直接调用方法
                method.invoke(context, value);
            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }

    }


    public static void injectView(Context context) {
        //通过反射获取  使用注解的类
        Class<? extends Context> clazz = context.getClass();

        //获取 类中所有的成员变量
        Field[] fields = clazz.getDeclaredFields();

        //遍历所有的成员变量
        for (Field field : fields) {
            //拿到成员变量 对应的 InjectView注解
            injectView annotation = field.getAnnotation(injectView.class);

            //如果注解不为null，说明，field成员变量使用了InjectView注解
            if (annotation != null) {
                //拿到往 注解里传的值，也就是 最后调用方法时要传的值。
                int value = annotation.value();
                try {
                    //用反射获取 clazz类中的 findViewById 方法
                    Method method = clazz.getMethod("findViewById", int.class);

                    //调用method方法 返回method对应方法该返回的值，也就是调用findViewById（R.id.xx）返回View
                    Object view = method.invoke(context, value);//第一个参数为调用方法的对象，第二个参数为 往方法里穿的参数。

                    //这里要注意，类中的成员变量为private,故必须进行此操作，否则无法给控件赋值（即初始化的捆绑）
                    //私有成员变量 无法拿到也就无法赋值，必须使用下面的方法，才可以拿到私有成员变量
                    field.setAccessible(true);

                    //给context的成员变量赋值.也就是调用完了findViewById方法后，给控件赋值
                    field.set(context, view);

                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
