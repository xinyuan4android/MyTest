package com.example.iningke.myapplication.ad;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.iningke.myapplication.R;
import com.iningke.baseproject.utils.LogUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class AdvertisementActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertisement);

        initView();
    }

    private void initView() {
        ImageView img = (ImageView) findViewById(R.id.img);
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                        LogUtils.e("1111111");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }).start();
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute("https://ss0.baidu.com/6ONWsjip0QIZ8tyhnq/it/u=956057211,1582894120&fm=58");
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public class MyAsyncTask extends AsyncTask<String, Integer, Integer> {
        /**
         * 下载准备工作。在UI线程中调用。
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Integer doInBackground(String... params) {
            HttpURLConnection conn = null;
            BufferedInputStream bis = null;
            BufferedOutputStream bos = null;
            try {
                URL urlObj = new URL(params[0]);
                conn = (HttpURLConnection) urlObj.openConnection();
                bos = new BufferedOutputStream(new FileOutputStream(new File(Environment.getExternalStorageDirectory(), "zzz.jpg")));
                conn.setDoInput(true);
                conn.setConnectTimeout(5000);
                conn.connect();

                if (conn.getResponseCode() == 200) {
                    bis = new BufferedInputStream(conn.getInputStream());
                    int c = -1;
                    byte[] buffer = new byte[1 * 1024];
                    while ((c = bis.read(buffer)) != -1) {
                        bos.write(buffer, 0, c);
                        bos.flush();
                    }
                    return 1;
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {

                try {
                    if (bos != null) {
                        bos.close();
                    }
                    if (bis != null) {
                        bis.close();
                    }
                    if (conn != null) {
                        conn.disconnect();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            return 2;
        }

        /**
         * 下载完成后调用
         *
         * @param integer
         */
        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            LogUtils.e((int) integer + "");
        }
    }
}
