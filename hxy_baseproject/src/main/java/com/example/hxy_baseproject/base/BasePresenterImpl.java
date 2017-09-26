package com.example.hxy_baseproject.base;


/**
 * Created by hxy on  2017/9/25.
 */

public abstract class BasePresenterImpl<V extends BaseView, T> implements BasePresenter, BaseCallBack<T> {
    protected V view;

    public BasePresenterImpl(V view) {
        this.view = view;
    }

    @Override
    public void beforeRequest(int requestId) {
        view.showProgress();
    }

    @Override
    public void requestComplete(int requestId) {
        view.hideProgress();
    }

    @Override
    public void requestError(String msg, int requestId) {
        view.hideProgress();
    }
}
