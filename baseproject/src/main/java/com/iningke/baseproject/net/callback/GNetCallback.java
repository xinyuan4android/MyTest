package com.iningke.baseproject.net.callback;


/**
 * Created by iningke on 2016/6/13.
 */

public interface GNetCallback{
    void success(Object obj, int requestCode);
    void onFaild(int requestCode, boolean isCancell, Throwable ex);
    void onResult(int resultCode,Object... obj);
    void onStart();
    void onFinish();
}
