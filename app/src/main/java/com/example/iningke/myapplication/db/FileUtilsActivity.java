package com.example.iningke.myapplication.db;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.iningke.myapplication.R;
import com.iningke.baseproject.utils.LogUtils;

import java.io.File;

public class FileUtilsActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_utils);
        editText = (EditText) findViewById(R.id.et_fileName);
    }

    public void make(View view) {
        String fileName = editText.getText().toString();
        if (TextUtils.isEmpty(fileName)) {
            return;
        }
        if (isHaveSdCard()) {
            File file = getDir("黄新元", Context.MODE_PRIVATE);
            LogUtils.e("file" + file.getAbsolutePath());
        }
        File file = new File(Environment.getExternalStorageDirectory(), fileName);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public boolean isHaveSdCard() {
        return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
