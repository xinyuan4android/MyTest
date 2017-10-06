package com.example.hxy_baseproject.base;

import rx.Subscription;

/**
 * 描述：presenter的基础实现类
 * 作者：hxy on  2017/9/28 11:12.
 *
 * @param <V> 视图接口对象(view) 具体业务各自继承自IBaseView
 * @param <T> 业务请求返回的类
 */

public abstract class BasePresenterImpl<V extends IBaseView, T> implements IBasePresenter, IBaseRequestCallBack<T> {
    protected V view;
    protected Subscription mSubscription;

    public BasePresenterImpl(V view) {
        this.view = view;

    }

    @Override
    public void unSubscribe() {
        if (mSubscription != null && !mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
//        view = null;
    }


    @Override
    public void beforeRequest() {
        view.showProgress();
    }

    @Override
    public void requestError(String errorMsg) {
        view.dismissProgress();
    }

    @Override
    public void requestComplete() {
        view.dismissProgress();
    }

}
