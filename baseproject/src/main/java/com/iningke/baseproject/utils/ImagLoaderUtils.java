package com.iningke.baseproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.widget.ImageView;

import com.iningke.baseproject.R;
import com.nostra13.universalimageloader.cache.disc.impl.ext.LruDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.MemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LRULimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by iningke on 2016/6/16.
 */
public class ImagLoaderUtils {
    public static void initImageLoader(Context context) {
        MemoryCache memoryCache = getMemoryCache();
        ImageLoaderConfiguration config = initConfig(context, memoryCache);
        ImageLoader.getInstance().init(config);
    }

    private static ImageLoaderConfiguration initConfig(Context context, MemoryCache memoryCache) {
        ImageLoaderConfiguration config = null;
        // 创建存放图片的磁盘目录
        File cacheDir = StorageUtils.getOwnCacheDirectory(context,FileUtils.DATA_DIR);
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder().build();
        try {
            config = new ImageLoaderConfiguration.Builder(context).defaultDisplayImageOptions(defaultOptions).threadPriority(Thread.NORM_PRIORITY - 2)
                    // 线程的优先级
                    .memoryCache(memoryCache)
                    // 设置缓存的大小
                    // 文件高速缓存到本地，参数1：磁盘地址。 参数2：加密算法。 参数3：高速缓存的最大大小 TODO
                    .diskCache(new LruDiskCache(cacheDir, new Md5FileNameGenerator(), 10 * 1024 * 1024)).denyCacheImageMultipleSizesInMemory().threadPoolSize(5)// 线程池线程个数，避免线程过多找出oom
                    .tasksProcessingOrder(QueueProcessingType.LIFO)// 任务的处理顺序
                    // 后进先出法
//                    .writeDebugLogs()// 写调试日志
                    .build();
        }
        catch (Exception e) {
            config = new ImageLoaderConfiguration.Builder(context).defaultDisplayImageOptions(defaultOptions).threadPriority(Thread.NORM_PRIORITY - 2)// 线程的优先级
                    .memoryCache(memoryCache)// 设置缓存的大小
                    .diskCacheFileNameGenerator(new Md5FileNameGenerator())// TODO
                    // 不同的地方
                    .denyCacheImageMultipleSizesInMemory().threadPoolSize(4)// 线程池线程个数，避免线程过多找出oom
                    .tasksProcessingOrder(QueueProcessingType.LIFO)// 任务的处理顺序
                    // 后进先出法
//                    .writeDebugLogs()// 写调试日志
                    .build();
            e.printStackTrace();
        }
        return config;
    }
    /**
     * 获取内存，取得内存的十分之一当做ImageLoader的内存使用
     *
     * @return MemoryCache
     */
    private static MemoryCache getMemoryCache() {
        int memoryCacheSize = (int) (Runtime.getRuntime().maxMemory() / 10);// 获取内存的十分之一
        MemoryCache memoryCache ;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {// 在android
            // api
            // 9之前
            memoryCache = new LruMemoryCache(memoryCacheSize);
        } else {
            memoryCache = new LRULimitedMemoryCache(memoryCacheSize);
        }
        return memoryCache;
    }
    public static void clearBitmap(String url){
        Bitmap bitmap = ImageLoader.getInstance().getMemoryCache().get(url);
        ImageLoader.getInstance().getMemoryCache().remove(url);
        if (bitmap!=null)
            bitmap.recycle();
    }

    public static void clearCache(){
        ImageLoader.getInstance().clearDiskCache();
        ImageLoader.getInstance().clearMemoryCache();
    }

    public static Bitmap getBitmap(String url){
//        ImageLoader.getInstance().loadImageSync()
        return ImageLoader.getInstance().getMemoryCache().get(url);
    }

    public static void showImage(String url, ImageView imageView,int defautid){
        if (defautid==0){
            imageView.setImageResource(R.drawable.bg_defaut);
        }
        ImageLoader.getInstance().displayImage(url,imageView,getOption(defautid));
    }

    public static DisplayImageOptions getOption(int defautid){
        if (defautid==0||defautid==-1){
            DisplayImageOptions options = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                    .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
//                .displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
                    .bitmapConfig(Bitmap.Config.ARGB_8888)
                    .build();
            return options;
        }
        // 使用DisplayImageOptions.Builder()创建DisplayImageOptions
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                .showStubImage(defautid)          // 设置图片下载期间显示的图片
                .showImageForEmptyUri(defautid)  // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(defautid)       // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true)                        // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true)                          // 设置下载的图片是否缓存在SD卡中
//                .displayer(new RoundedBitmapDisplayer(20))  // 设置成圆角图片
                .bitmapConfig(Bitmap.Config.ARGB_8888)
                .build();
        return options;
    }
}
