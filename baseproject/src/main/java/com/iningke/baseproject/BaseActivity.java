package com.iningke.baseproject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.iningke.baseproject.utils.LoadingDialog;

import butterknife.ButterKnife;

/**
 * Created by iningke on 2016/6/1.
 */
public abstract class BaseActivity extends FragmentActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        ScreenUtils.setTranslucent(this);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (setLayoutView()==null){
            setContentView(setLayoutId());
        }else {
            setContentView(setLayoutView());
        }
        ButterKnife.bind(this);
        initView();
        addListener();
        initData();
    }

    /**
     * 添加监听器
     */
    protected void addListener(){}

    /**
     * 加载数据
     * @return
     */
    public abstract void initData();
    /**
     * 初始化师徒
     * @return
     */
    public abstract void initView();

    /**
     * 设置布局id
     * @return
     */
    public abstract int setLayoutId();

    /**
     * 设置布局view
     */
    public abstract View setLayoutView();
    /**
     * 跳转页面
     */
    public <T extends FragmentActivity> void gotoActivity(Class<T> clz, Bundle data){
        Intent intent = new Intent(this,clz);
        intent.putExtra("data",data);
        startActivity(intent);
    }
    public <T extends FragmentActivity> void gotoActivityForResult(Class<T> clz, Bundle data,int requestcode){
        Intent intent = new Intent(this,clz);
        intent.putExtra("data",data);
        startActivityForResult(intent,requestcode);
    }
    /**
     * 跳转数据获取
     */
    public Bundle getActivityData(){
        Intent intent = getIntent();
        if (intent==null){
            return null;
        }
        return intent.getBundleExtra("data");
    }
    /**
     * 加载dialog
     */
    LoadingDialog dialog;
    public void showDialog(DialogInterface.OnDismissListener listener){
        if (dialog==null){
            dialog = new LoadingDialog(this, R.style.selectorDialog);
        }
        dialog.showDialog(listener);
    }
    public void dismissDialog(){
        if (dialog!=null&&dialog.isShowing()){
            dialog.dismiss();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissDialog();
        ButterKnife.unbind(this);
    }
}
