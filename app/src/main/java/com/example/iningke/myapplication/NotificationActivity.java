package com.example.iningke.myapplication;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RemoteViews;

import cn.qqtheme.framework.util.LogUtils;

public class NotificationActivity extends AppCompatActivity {
    public static final int FLAG_HOMEKEY_DISPATCHED = 0x80000000; //需要自己定义标志

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(FLAG_HOMEKEY_DISPATCHED, FLAG_HOMEKEY_DISPATCHED);//关键代码
        setContentView(R.layout.activity_notification);
    }

    public void send(View view) {
        handler.sendEmptyMessageDelayed(100, 5000);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            showNotification();
            return false;
        }
    });
    private int i = 0;

    public void showNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
//        builder.setContentTitle("c测试");//设置通知栏标题
//        builder.setStyle()
        RemoteViews remoteView = new RemoteViews(this.getPackageName(), R.layout.header_listview);
        builder.setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(this, 0, new Intent(), 0));
        builder.setContent(remoteView);
//        builder.setContentText("测试");//设置通知栏显示内容
//        builder.setWhen(System.currentTimeMillis());//通知产生的时间，会在通知信息里显示，一般是系统获取到的时间
//        builder.setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND | Notification.DEFAULT_LIGHTS);//向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            builder.setSmallIcon(R.mipmap.ic_launcher);//置通知小ICON（5.0必须采用白色透明图片）
        } else {
            builder.setSmallIcon(R.mipmap.ic_launcher);//设置通知小ICON
        }

        Notification notification = builder.build();
//        notification.flags |= Notification.FLAG_ONLY_ALERT_ONCE;//响铃一次
//        notification.flags |= Notification.FLAG_AUTO_CANCEL;//通知点击后 消失

        notification.bigContentView = remoteView;
//        notification.bigContentView.setOnClickFillInIntent(R.id.iv_header, new Intent());
        manager.notify(222222 + ++i, notification);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_HOME) {
            //dosomething
            LogUtils.error("home home");
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
