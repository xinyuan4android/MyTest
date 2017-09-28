package com.example.iningke.myapplication;

import android.graphics.Paint;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.iningke.myapplication.utils.HttpDownloader;

import java.io.IOException;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;
    private TextView textView2;
//    private WebView webView;
//    private VideoView videoView;

    private TextView btnplay, btnstop, btnpause;
    private SurfaceView surfaceView;
    private MediaPlayer mediaPlayer;
    private int position;
    private SeekBar seekBar;
    private SurfaceHolder holder;
    private TextView btndownload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initView();
        aboutVedio();

        //定义DisplayMetrics 对象

        //text();
//        aboutRongCloud();
//        TextView test = ((TextView) findViewById(R.id.textTest));
//        test.setText(Html.fromHtml("苹果apple 青翠苹果园烟台苹果80mm红富士苹果苹果apple 青翠苹果园烟台苹果80mm红富士苹果" + "<img src=\" " + R.mipmap.text_img + "\">", new Html.ImageGetter() {
//            @Override
//            public Drawable getDrawable(String source) {
//                Drawable drawable = getResources().getDrawable(R.mipmap.text_img);
//                drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
//                return drawable;
//            }
//        }, null));
    }

    private void download() {

//        int result = httpDownLoader.downfile("http://v6.pstatp.com/video/c/5128754298294b61849d548819b87bca/?Signature=TySzYwP3YLwVoQtIY8CTcR4Z0UE%3D&Expires=1470736385&KSSAccessKeyId=qh0h9TdcEMrm1VlR2ad/",
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpDownloader httpDownLoader = new HttpDownloader();
                int result = httpDownLoader.downfile("http://182.92.201.213/222.mp4",
                        "", "不知道什么东西.mp4");
//                int result = httpDownLoader.downfile("http://pic3.nipic.com/20090622/2605630_113023052_2.jpg","", "测试2.png");
                Log.e("iningke","result== " +result);
            }
        }).start();
    }


    private void aboutRongCloud() {
        String Token = "d6bCQsXiupB/4OyGkh+TOrI6ZiT8q7s0UEaMPWY0lMxmHdi1v/AAJxOma4aYXyaivfPIJjNHdE+FMH9kV/Jrxg==";//test
        /**
         * IMKit SDK调用第二步
         *
         * 建立与服务器的连接
         *
         */
        RongIM.connect(Token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {
                //Connect Token 失效的状态处理，需要重新获取 Token
            }

            @Override
            public void onSuccess(String userId) {
                Log.e("MainActivity", "——onSuccess— -" + userId);
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
                Log.e("MainActivity", "——onError— -" + errorCode);
            }
        });
    }

    private void text() {
        TextView test = ((TextView) findViewById(R.id.textTest));
        DisplayMetrics dm = new DisplayMetrics();
        //取得窗口属性
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        //窗口的宽度
        int screenWidth = dm.widthPixels;

        //窗口高度
        int screenHeight = dm.heightPixels;

        Log.e("android", "屏幕宽度 =" + screenWidth + "屏幕高度 =" + screenHeight);
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        int screenWidthDp = (int) (screenWidth / density);//屏幕宽度(dp)
        int screenHeightDp = (int) (screenHeight / density);//屏幕高度(dp)
        Log.e("android", "屏幕宽度Dp =" + screenWidthDp + "屏幕高度Dp =" + screenHeightDp);

        test.setWidth(screenWidth);
        test.setHeight(screenHeight);

        Point point = new Point();
        getWindowManager().getDefaultDisplay().getRealSize(point);
        DisplayMetrics dm2 = getResources().getDisplayMetrics();
        double x = Math.pow(point.x / dm2.xdpi, 2);
        double y = Math.pow(point.y / dm2.ydpi, 2);
        double screenInches = Math.sqrt(x + y);
        Log.e("android", "Screen inches : " + screenInches);
    }

    private void aboutVedio() {

        btnplay = (TextView) this.findViewById(R.id.btnplay);
        btnstop = (TextView) this.findViewById(R.id.btnstop);
        btnpause = (TextView) this.findViewById(R.id.btnpause);
        btndownload = (TextView) findViewById(R.id.btndownload);
        seekBar = ((SeekBar)
                findViewById(R.id.seekBar));
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b && mediaPlayer != null) {
                    mediaPlayer.seekTo(i);//更新播放进度
                    position = i;//拖动进度条后 将位置设置为拖动后位置
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btnstop.setOnClickListener(this);
        btnplay.setOnClickListener(this);
        btnpause.setOnClickListener(this);
        btndownload.setOnClickListener(this);
        mediaPlayer = new MediaPlayer();
        surfaceView = (SurfaceView) this.findViewById(R.id.surfaceView);

        //设置SurfaceView自己不管理的缓冲区
//        surfaceView.getHolder().setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder = surfaceView.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }

            @Override
            public void surfaceCreated(SurfaceHolder holder) {
//                if (position > 0) {
                Log.e("surfaceView", " ===============surfaceCreated=============== ");
                try {
                    play();
                    handler.sendEmptyMessageDelayed(1, 500);
                } catch (Exception e) {
                }
//                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                       int height) {

            }
        });
    }

    private void initView() {

        textView = ((TextView) findViewById(R.id.text));
        textView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG);
        textView2 = ((TextView) findViewById(R.id.text2));
        textView2.getPaint().setAntiAlias(true);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnplay:
                mediaPlayer.start();
                position = mediaPlayer.getCurrentPosition();
                handler.sendEmptyMessageDelayed(1, 500);
                break;

            case R.id.btnpause:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
//                    position = mediaPlayer.getCurrentPosition();
                } else {
                    mediaPlayer.start();
                    handler.sendEmptyMessageDelayed(1, 500);
                }
                break;

            case R.id.btnstop:
                seekBar.setProgress(0);
                mediaPlayer.seekTo(0);
                position = 0;
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btndownload:
                download();
                break;
            default:
                break;
        }

    }

//    @Override
//    protected void onPause() {
//        //先判断是否正在播放
//        if (mediaPlayer.isPlaying()) {
//            //如果正在播放我们就先保存这个播放位置
//            position = mediaPlayer.getCurrentPosition();
//            mediaPlayer.stop();
//        }
//        super.onPause();
//    }

    private void play() {
//            mediaPlayer.reset();
//            mediaPlayer
//                    .setAudioStreamType(AudioManager.STREAM_MUSIC);
        //把视频画面输出到SurfaceView
        mediaPlayer.setDisplay(holder);
        //设置需要播放的视频
            /* 设置缓冲进度更新监听器 */
        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer arg0, int percent) {
                    /* 打印缓冲的百分比, 如果缓冲 */
                Log.e("视频播放s", "缓冲了的百分比 : " + percent + " %");
            }
        });
//            mediaPlayer.setDataSource(Environment.getExternalStorageDirectory() + "/xiatian.mp4");
//
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Uri uri = Uri.parse("http://baobab.wdjcdn.com/1456117847747a_x264.mp4");
                    mediaPlayer.setDataSource(MainActivity.this, uri);
                    mediaPlayer.prepare();
                    int duration = mediaPlayer.getDuration();
                    Log.e("视频播放s", "duration == " + duration);
                    seekBar.setMax(duration);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //播放
//            mediaPlayer.start();

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (mediaPlayer.isPlaying()) {
                position += 500;
                seekBar.setProgress(position);
                sendEmptyMessageDelayed(1, 500);
            }
        }
    };
}
