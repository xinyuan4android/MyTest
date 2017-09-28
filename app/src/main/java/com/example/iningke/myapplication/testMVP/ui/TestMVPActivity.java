package com.example.iningke.myapplication.testMVP.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.iningke.myapplication.R;
import com.example.iningke.myapplication.okhttptest.UserInfoModel;
import com.example.iningke.myapplication.testMVP.presenter.UserPresenterImpl;
import com.example.iningke.myapplication.testMVP.view.UserView;
import com.google.gson.Gson;

public class TestMVPActivity extends AppCompatActivity implements UserView {
    private UserPresenterImpl presenter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mvp);
        presenter = new UserPresenterImpl(this);
        textView = (TextView) findViewById(R.id.textView);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(true);

    }

    public void getData(View view) {
        presenter.getUserInfo("53e8d39a50c54c1395728dc77451d148");
    }

    @Override
    public void showUserInfo(UserInfoModel userInfo) {
        textView.setText(new Gson().toJson(userInfo));
    }

    @Override
    public void showErrorInfo(String errorMsg) {
        textView.setText(errorMsg);
    }

    private ProgressDialog dialog;

    @Override
    public void showProgress() {
        dialog.show();
    }

    @Override
    public void dismissProgress() {
        dialog.dismiss();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }
}
