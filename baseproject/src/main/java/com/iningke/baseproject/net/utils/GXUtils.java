package com.iningke.baseproject.net.utils;

import com.alibaba.fastjson.JSON;
import com.iningke.baseproject.net.callback.GNetCallback;
import com.iningke.baseproject.utils.LogUtils;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

/**
 * Created by iningke on 2016/6/15.
 */
public class GXUtils  {
    static Callback.Cancelable cancelable;

    public static void postX(final RequestParams params, final GNetCallback callback, final int requestCode, final Class clz){

        cancelable = x.http().post(params, new Callback.ProgressCallback<String>() {
            @Override
            public void onWaiting() {

            }

            @Override
            public void onStarted() {
                callback.onStart();
            }

            @Override
            public void onLoading(long total, long current, boolean isDownloading) {

            }

            @Override
            public void onSuccess(String result) {
                callback.success(JSON.parseObject(result,clz),requestCode);
                LogUtils.e("*******************************************************************************");
                try {
                    LogUtils.e("请求参数："+params.toJSONString());
                    LogUtils.e("url："+params.getUri());
                }catch (Exception e){
                    e.printStackTrace();
                }
                LogUtils.e("成功结果："+result);
                LogUtils.e("*******************************************************************************");

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                callback.onFaild(requestCode,isOnCallback,ex);
                try {
                    LogUtils.e("请求参数："+params.toJSONString());
                    LogUtils.e("url："+params.getUri());
                }catch (Exception e){
                    e.printStackTrace();
                }
                LogUtils.e("失败信息："+ex.getMessage()+" isOnCallback->"+isOnCallback);
            }

            @Override
            public void onCancelled(CancelledException cex) {
                callback.onFaild(requestCode,true,cex);
            }

            @Override
            public void onFinished() {
                callback.onFinish();
            }
        });
    }
}
