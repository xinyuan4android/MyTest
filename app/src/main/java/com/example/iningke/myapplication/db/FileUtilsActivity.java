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
    private SqliteUtils sqliteUtils;

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
        sqliteUtils = new SqliteUtils(this, SqliteUtils.path + "黄新元/tb_message", null, 1);


    }

    public void add(View view) {
        sqliteUtils.addDataToDB(new MessageBean("1", "2", "3", "4", "5", "6"));
    }

    public void update(View view) {
        sqliteUtils.upDataFromDB(new MessageBean("1", "1", "1", "1", "2", "1"));
    }

    public void delete(View view) {
        sqliteUtils.deleteDbData("1");
    }

    public boolean isHaveSdCard() {
        return Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
    }
}
