package com.iningke.baseproject.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by iningke on 2016/6/16.
 */
public class FileUtils {
    public static final String JKJL = "JKJL";

    public static final String SDCardDir    = Environment.getExternalStorageDirectory ()
            .getAbsolutePath ()
            + File.separator
            + JKJL
            + File.separator;
//    public static final String DOWNLOAD_DIR = SDCardDir
//            + "TMP"
//            +File.separator;
//    public static final String STORAGE_PATH = JKJL
//            + File.separator
//            + "tmp"+ File.separator;
//
    public static final String DATA_DIR = SDCardDir
            + "Data";

    public static String getHashPath(String url){
        return DATA_DIR+File.separator+url.hashCode()+".mp4";
    }

    public static boolean isFileExist(String url){
        String hashPath = getHashPath(url);
        File file = new File(hashPath);
        if (file.exists()){
            return true;
        }
        return false;
    }

    public static void renameFile(String oldPath,String newPath){
        File file = new File(oldPath);
        file.renameTo(new File(newPath));
    }
    /**
     * 删除指定文件
     * @param file
     */
    public static void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
            }
            file.delete();
        } else {
            //UIUtils.showToastSafe("文件不存在！");
            // Constants.Logdada("文件不存在！"+"\n");
        }
    }

    //文件拷贝
    public static int copySdcardFile(String fromFile, String toFile)
    {
        File file = new File(toFile);
        if (file.exists())
            file.delete();
        File dir = new File(toFile.substring(0,toFile.lastIndexOf("/")));
        if (!dir.exists())
            dir.mkdirs();

        try
        {
            file.createNewFile();
            InputStream fosfrom = new FileInputStream(fromFile);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = fosfrom.read(bt)) > 0)
            {
                fosto.write(bt, 0, c);
            }
            fosfrom.close();
            fosto.close();
            return 0;

        } catch (Exception ex)
        {
            return -1;
        }
    }
    //文件拷贝
    public static int copySdcardFile(InputStream inputStream, String toFile)
    {
        File file = new File(toFile);
        if (file.exists())
            file.delete();
        File dir = new File(toFile.substring(0,toFile.lastIndexOf("/")));
        if (!dir.exists())
            dir.mkdirs();

        try
        {
            file.createNewFile();
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024];
            int c;
            while ((c = inputStream.read(bt)) != -1)
            {
                fosto.write(bt, 0, c);
            }
            fosto.flush();
            fosto.close();
            inputStream.close();
            return 0;

        } catch (Exception ex)
        {
            return -1;
        }
    }

    public static String getExerciseRootPath(Context context){
        return context.getDir("exercise",Context.MODE_PRIVATE).getAbsolutePath() + "/";
    }

    public static String getUrlName(String url){
        return url.substring(url.lastIndexOf('/') + 1);
    }

    public static String getExerciseExamplePath(Context context,long unitid){
        return getExerciseRootPath(context) + "unit" + unitid + "/example/";
    }

    public static void deleteExerciseExample(Context context,long unitid){
        String path = getExerciseExamplePath(context,unitid);
        if (TextUtils.isEmpty(path)){
            File file = new File(path);
            if (file.exists()){
                deleteFile(file);
            }
        }
    }
    public static String getExerciseBestPath(Context context,long unitid){
        return getExerciseRootPath(context) + unitid + "/best/";
    }
    public static void deleteExerciseBest(Context context,long unitid){
        String path = getExerciseBestPath(context,unitid);
        if (TextUtils.isEmpty(path)){
            File file = new File(path);
            if (file.exists()){
                deleteFile(file);
            }
        }
    }
    public static String getExerciseCurrentPath(Context context,long unitid){
        return getExerciseRootPath(context) + unitid +  "/current/";
    }
    public static void deleteExerciseCurrent(Context context,long unitid){
        String path = getExerciseCurrentPath(context,unitid);
        if (TextUtils.isEmpty(path)){
            File file = new File(path);
            if (file.exists()){
                deleteFile(file);
            }
        }
    }
    public static void deleteExercise(Context context,long unitid){
        deleteExerciseBest(context,unitid);
        deleteExerciseCurrent(context,unitid);
        deleteExerciseExample(context,unitid);
    }

    public static String getDefaultMp3(Context context,String name) {
        return "file:///android_asset/defaultexercise/" + name;
        /*InputStream abpath = context.getClass().getResourceAsStream("/assets/defaultexercise/" + name);
        try {
            byte[] b = inputStreamToByte(abpath);
            return new String(b);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";*/
        //return "android:resource://" + context.getPackageName() + "/" + resourceId;
        //return "defaultexercise/" + name;
    }

    public static byte[] inputStreamToByte(InputStream is) throws IOException {
        ByteArrayOutputStream bytestream = new ByteArrayOutputStream();
        int ch;
        while ((ch = is.read()) != -1) {
            bytestream.write(ch);
        }
        byte imgdata[] = bytestream.toByteArray();
        bytestream.close();
        return imgdata;
    }
}
