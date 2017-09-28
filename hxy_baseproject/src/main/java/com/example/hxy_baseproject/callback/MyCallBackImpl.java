package com.example.hxy_baseproject.callback;

import okhttp3.Response;

/**
 * 描述：
 * 作者：hxy on  2017/9/28 15:24.
 */

public abstract class MyCallBackImpl<T> implements MyCallBack<T> {
    /**
     * if you parse reponse code in parseNetworkResponse, you should make this method return true.
     *
     * @param response
     * @return
     */
    public boolean validateReponse(Response response, int id) {
        return response.isSuccessful();
    }

//    /**
//     * Thread Pool Thread
//     *
//     * @param response
//     */
//    public T parseNetworkResponse(Response response, int id) throws Exception {
////        return new Gson().fromJson(response.body().string(), Class < T >);
//        return T;
//    }


    public static MyCallBackImpl Call_Back = new MyCallBackImpl() {

        @Override
        public void beforeRequest(int requestId) {

        }

        @Override
        public void requestComplete(int requestId) {

        }

        @Override
        public void requestError(String msg, int requestId) {

        }

        @Override
        public void inProgress(float progress, long total, int requestId) {

        }

        @Override
        public void requestSuccess(Object data, int requestId) {

        }

        @Override
        public Object parseNetworkResponse(Response response, int id) throws Exception {
            return null;
        }

    };
}
