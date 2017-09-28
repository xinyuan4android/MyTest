package com.example.iningke.myapplication.utils;

import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by iningke on 2016/8/10.
 */
public class HttpDownloader {
    private URL url = null;
    FileUtils fileUtils = new FileUtils();

    public int downfile(String urlStr, String path, String fileName) {
        if (fileUtils.isFileExist(path + fileName)) {
            return 1;
        } else {

            try {
                InputStream input = null;
                input = getInputStream(urlStr);
                File resultFile = fileUtils.write2SDFromInput(path, fileName, input);
                if (resultFile == null) {
                    return -1;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return 0;
    }

    //由于得到一个InputStream对象是所有文件处理前必须的操作，所以将这个操作封装成了一个方法
    public InputStream getInputStream(String urlStr) throws IOException {
        InputStream is = null;
        try {
            url = new URL(urlStr);
            HttpURLConnection urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setConnectTimeout(5 * 1000);
            if(urlConn.getResponseCode() == HttpURLConnection.HTTP_OK){
                Log.e("iningke","OK");
                return urlConn.getInputStream();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return is;
    }
    /**
     * Get data from stream
     * @param inStream
     * @return byte[]
     * @throws Exception
     */
    public static byte[] readStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while( (len=inStream.read(buffer)) != -1){
            outStream.write(buffer, 0, len);
        }
        outStream.close();
        inStream.close();
        return outStream.toByteArray();
    }
}