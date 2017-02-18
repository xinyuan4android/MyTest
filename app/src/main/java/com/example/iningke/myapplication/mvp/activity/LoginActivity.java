package com.example.iningke.myapplication.mvp.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.iningke.myapplication.R;
import com.example.iningke.myapplication.mvp.bean.BeanUser;
import com.example.iningke.myapplication.mvp.presenter.Presenter;
import com.example.iningke.myapplication.mvp.view.IViewLogin;

public class LoginActivity extends AppCompatActivity implements IViewLogin {

    private EditText name;
    private EditText password;
    private Button login;
    private Button clear;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        presenter = new Presenter(this);
        name = (EditText) findViewById(R.id.name);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.btn_login);
        clear = (Button) findViewById(R.id.btn_clear);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.login();
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.clear();
            }
        });
    }

    @Override
    public String getUserName() {
        return name.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void clearUserName() {
        name.setText("");
    }

    @Override
    public void clearPassword() {
        password.setText("");
    }

    @Override
    public void loginSuccess(BeanUser beanUser) {
        Toast.makeText(LoginActivity.this, "登录成功~", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError() {
        Toast.makeText(LoginActivity.this, "网络异常", Toast.LENGTH_SHORT).show();
    }
}
