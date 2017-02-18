package com.iningke.baseproject.view;

/**
 * Created by iningke on 2016/6/8.
 */

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.iningke.baseproject.R;
import com.iningke.baseproject.utils.UIUtils;
import com.iningke.baseproject.view.uienum.LoadResult;


/** * 创建者：gaonan
 * * 时间：2015/11/13
 * * 类描述：可刷新页面的布局
 * * 修改人：
 * * 修改时间：
 * * 修改备注：
 * */
public abstract class LoadingView extends FrameLayout {
    /* 未知情况页面 */
    public static final int STATE_UNKNOW = 0;
    /* 加载中的页面 */
    public static final int STATE_LOADING = 1;
    /* 错误的页面 */
    public static final int STATE_ERROR = 2;
    /* 空的页面 */
    public static final int STATE_EMPTY = 3;
    /* 成功的页面 */
    public static final int STATE_SUCCESS = 4;
    /* 其他页面 */
    public static final int STATE_OTHER = 5;

    /* 当前的页面 */
    public int curState = STATE_UNKNOW;

    /* 加载中的界面 */
    private View loadingView;
    /* 错误界面 */
    private View errorView;
    /* 空界面 */
    private View emptyView;
    /* 加载成功的界面 */
    private View successView;
    /* 加载其他的界面 */
    private View otherView;

    public LoadingView(Context context) {
        super(context);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /* 初始化 */
    private void init() {
        loadingView = createLoadingView();
        if (loadingView != null) {
            this.addView(loadingView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        errorView = createErrorView();
        if (errorView != null) {
            this.addView(errorView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        emptyView = createEmptyView();
        if (emptyView != null) {
            this.addView(emptyView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }
        otherView = createOtherView();
        if (otherView != null) {
            this.addView(otherView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        }

        showPage();
    }


    /* 根据不同的状态显示不同的界面 */
    private void showPage() {
        if (loadingView != null)
            //当前状态 是 未知 和 加载中 时候, 都显示 加载界面.
            loadingView.setVisibility(curState == STATE_UNKNOW  || curState == STATE_LOADING ? View.VISIBLE : View.INVISIBLE);
        if (errorView != null) {
            errorView.setVisibility(curState == STATE_ERROR ? View.VISIBLE : View.INVISIBLE);
        }
        if (emptyView != null) {
            emptyView.setVisibility(curState == STATE_EMPTY ? View.VISIBLE : View.INVISIBLE);
        }
        if(otherView != null){
            otherView.setVisibility(curState == STATE_OTHER ?  View.VISIBLE : View.INVISIBLE);
        }
        if (curState == STATE_SUCCESS) {
            if (successView == null) {
                successView =View.inflate(UIUtils.getContext(), createSuccessView(), null);
                this.addView(successView, new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            }
            successView.setVisibility(View.VISIBLE);
        } else {
            if (successView != null) {
                successView.setVisibility(View.INVISIBLE);
            }
        }
    }

    /**
     * 创建成功页面
     * @return 成功页面的view
     */
    protected abstract int createSuccessView();

    protected View createEmptyView() {
        return View.inflate(UIUtils.getContext(), R.layout.loadpage_empty, null);
    }

    protected View createErrorView() {
        View errorView = View.inflate(UIUtils.getContext(), R.layout.error_wifi, null);
        errorView.findViewById(R.id.rlRoot).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                show();
            }
        });
        return errorView;
    }

    protected View createOtherView() {
        return null;
    }

    /* 创建加载中的页面 */
    protected View createLoadingView() {
        return View.inflate(UIUtils.getContext(), R.layout.error_loading, null);
    }

    /**
     * 根据服务器的数据 切换状态
     */
    public void show(){
        if(curState == STATE_ERROR || curState == STATE_EMPTY || curState == STATE_OTHER){
            curState = STATE_LOADING;
        }
        //使用类去管理线程吧....我不管了...
        load();
        //单线程执行会不会太慢= =!
      /*  new Thread(){
            @Override
            public void run() {

            }
        }.start();*/
        //多线程处理当前 任务. 保证load方法执行的速度.
      /*  UIUtils.startTaskInThreadPool(new Runnable() {
            @Override
            public void run() {
                final LoadResult loadResult = load();
                UIUtils.runInMainThread(new Runnable() {
                    @Override
                    public void run() {
                        //更新状态
                        curState = loadResult.getValue();
                        showPage();
                    }
                });
            }
        });*/
        //改变当前状态后,刷新下界面.
        showPage();

    }

    /**
     * 注意:该方法运行在子线程中.
     * 加载数据时候运用此方法.
     */
    protected abstract void load();

    public void notifyDataChanged(final LoadResult load){
        UIUtils.runInMainThread(new Runnable() {
            @Override
            public void run() {
                //更新状态
                curState = load.getValue();
                showPage();
            }
        });
    }
}

