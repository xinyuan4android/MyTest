package com.js2java.ndk;

/**
 * Created by hxy on  2017/4/5.
 */
public class NDKUtils {
    public static native String getStringFromNDK();

    static {
        System.loadLibrary("jniHello");//之前在build.gradle里面设置的so名字，必须一致
    }
}
