package com.example.iningke.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.iningke.myapplication.ad.AdvertisementActivity;
import com.example.iningke.myapplication.adapter.HxyActivityAdapter;
import com.example.iningke.myapplication.animation.TestAnimationActivity;
import com.example.iningke.myapplication.annotion.TextAnnotationActivity;
import com.example.iningke.myapplication.bean.HxyActivityBean;
import com.example.iningke.myapplication.bluetooth.BlueToothActivity;
import com.example.iningke.myapplication.contact.ContactsFromPhoneActivity;
import com.example.iningke.myapplication.contact.GoToSystemContactActivity;
import com.example.iningke.myapplication.db.FileUtilsActivity;
import com.example.iningke.myapplication.designpattern.StrategyPatternActivity;
import com.example.iningke.myapplication.dragItem.RecyclerViewActivity;
import com.example.iningke.myapplication.encrypt.AESTestActivity;
import com.example.iningke.myapplication.exlistview.MyExpandableListViewActivity;
import com.example.iningke.myapplication.gif.GifActivity;
import com.example.iningke.myapplication.iflytek.IFlyTekSpeechActivity;
import com.example.iningke.myapplication.mvp.activity.LoginActivity;
import com.example.iningke.myapplication.ndk.NDKHelloWorldActivity;
import com.example.iningke.myapplication.okhttptest.TestOkHttpActivity;
import com.example.iningke.myapplication.permission.TextPermission2Activity;
import com.example.iningke.myapplication.practice.PaintTestActivity;
import com.example.iningke.myapplication.retrofit.TestRetrofitActivity;
import com.example.iningke.myapplication.rxjava.TestRxJavaActivity;
import com.example.iningke.myapplication.sensor.SensorActivity;
import com.example.iningke.myapplication.testActivityStackStatusBar.Test1Activity;
import com.example.iningke.myapplication.testMVP.ui.TestMVPActivity;
import com.example.iningke.myapplication.toast.TestToastActivity;
import com.example.iningke.myapplication.zoomlistview.MyZoomListViewActivity;
import com.iningke.baseproject.utils.LogUtils;
import com.iningke.baseproject.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HxyActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.hxy_listView)
    ListView hxyListView;
    private HxyActivityAdapter adapter;
    private List<HxyActivityBean> dataSource = new ArrayList<>();
    private ListView listView_footView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hxy);
        ButterKnife.bind(this);
        initView();
        setData();
        int displayWidth = UIUtils.getDisplayWidth(this);
        int displayHeight = UIUtils.getDisplayHeight(this);
        LogUtils.e("displayWidth" + displayWidth + "--displayHeight--" + displayHeight);
//        setPullLvHeight(listView_footView);
    }

    private void setData() {

        dataSource.add(new HxyActivityBean("ScrollForeverTextView", "自定义无限循环的跑马灯"));
        dataSource.add(new HxyActivityBean("ContactsFromPhoneActivity", "自定义联系人列表界面"));
        dataSource.add(new HxyActivityBean("GoToSystemContactActivity", "直接跳系统的联系人界面"));
        dataSource.add(new HxyActivityBean("MyExpandableListViewActivity", "带分组的ListView"));
        dataSource.add(new HxyActivityBean("GifActivity", "用Glide加载GIF动态图，并且可缩放"));
        dataSource.add(new HxyActivityBean("IFlyTekSpeechActivity", "科大讯飞的语音朗读功能"));
        dataSource.add(new HxyActivityBean("TextToSpeechActivity", "android自带的语音朗读功能，仅支持英文,德语,意大利语,法语,西班牙语"));
        dataSource.add(new HxyActivityBean("LoginActivity", "MVP模式的一个简答的例子"));
        dataSource.add(new HxyActivityBean("TextPermission2Activity", "android6.0动态权限"));
        dataSource.add(new HxyActivityBean("FileUtilsActivity", "关于文件"));
        dataSource.add(new HxyActivityBean("NDKHelloWorldActivity", "初识NDK"));
        dataSource.add(new HxyActivityBean("TextAnnotationActivity", "初识注解"));
        dataSource.add(new HxyActivityBean("AdvertisementActivity", "广告页"));
        dataSource.add(new HxyActivityBean("SensorActivity", "三轴传感器"));
        dataSource.add(new HxyActivityBean("MyZoomListViewActivity", "头部缩放ListView"));
        dataSource.add(new HxyActivityBean("NotificationActivity", "测试通知"));
        dataSource.add(new HxyActivityBean("BlueToothActivity", "蓝牙功能"));
        dataSource.add(new HxyActivityBean("TestOkHttpActivity", "测试OkHttp"));
        dataSource.add(new HxyActivityBean("TestRetrofitActivity", "测试Retrofit"));
        dataSource.add(new HxyActivityBean("TestRxJavaActivity", "测试RxJava"));
        dataSource.add(new HxyActivityBean("TestMVPActivity", "测试MVP"));
        dataSource.add(new HxyActivityBean("Test1Activity", "测试ActivityStack"));
        dataSource.add(new HxyActivityBean("TestAnimationActivity", "测试布局动画"));
        dataSource.add(new HxyActivityBean("RecyclerViewActivity", "测试RecyclerView"));
        dataSource.add(new HxyActivityBean("TestToastActivity", "测试Toast"));
        dataSource.add(new HxyActivityBean("TestMeasureActivity", "测试Measure"));
        dataSource.add(new HxyActivityBean("StrategyPatternActivity", "测试Strategy"));
        dataSource.add(new HxyActivityBean("SwitchButtonActivity", "测试SwitchButton"));
        dataSource.add(new HxyActivityBean("AESTestActivity", "测试AES 加密"));
        dataSource.add(new HxyActivityBean("PaintTestActivity", "测试AES 加密"));


        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                startActivity(new Intent(this, MarqueeActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, ContactsFromPhoneActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, GoToSystemContactActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, MyExpandableListViewActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, GifActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, IFlyTekSpeechActivity.class));
                break;
            case 6:
                startActivity(new Intent(this, TextToSpeechActivity.class));
                break;
            case 7:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case 8:
                startActivity(new Intent(this, TextPermission2Activity.class));
                break;
            case 9:
                startActivity(new Intent(this, FileUtilsActivity.class));
                break;
            case 10:
                startActivity(new Intent(this, NDKHelloWorldActivity.class));
                break;
            case 11:
                startActivity(new Intent(this, TextAnnotationActivity.class));
                break;
            case 12:
                startActivity(new Intent(this, AdvertisementActivity.class));
                break;
            case 13:
                startActivity(new Intent(this, SensorActivity.class));
                break;
            case 14:
                startActivity(new Intent(this, MyZoomListViewActivity.class));
                break;
            case 15:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case 16:
                startActivity(new Intent(this, BlueToothActivity.class));
                break;
            case 17:
                startActivity(new Intent(this, TestOkHttpActivity.class));
                break;
            case 18:
                startActivity(new Intent(this, TestRetrofitActivity.class));
                break;
            case 19:
                startActivity(new Intent(this, TestRxJavaActivity.class));
                break;
            case 20:
                startActivity(new Intent(this, TestMVPActivity.class));
                break;
            case 21:
                startActivity(new Intent(this, Test1Activity.class));
                break;
            case 22:
                startActivity(new Intent(this, TestAnimationActivity.class));
                break;
            case 23:
                startActivity(new Intent(this, RecyclerViewActivity.class));
                break;
            case 24:
                startActivity(new Intent(this, TestToastActivity.class));
                break;
            case 25:
                startActivity(new Intent(this, TestMeasureActivity.class));
                break;
            case 26:
                startActivity(new Intent(this, StrategyPatternActivity.class));
            case 27:
                startActivity(new Intent(this, SwitchButtonActivity.class));
                break;
            case 28:
                startActivity(new Intent(this, AESTestActivity.class));
                break;
            case 29:
                startActivity(new Intent(this, PaintTestActivity.class));
                break;
            default:
                break;
        }
    }

    private void initView() {
        adapter = new HxyActivityAdapter(dataSource);
//        View headView = LayoutInflater.from(this).inflate(R.layout.activity_mpandroid_chart, null);
//        hxyListView.addHeaderView(headView);
//        View footView = LayoutInflater.from(this).inflate(R.layout.activity_hxy, null);
//        listView_footView = (ListView) footView.findViewById(R.id.hxy_listView);
//        listView_footView.setBackgroundColor(Color.RED);
//        listView_footView.setAdapter(adapter);
//        hxyListView.addFooterView(footView);
        hxyListView.setAdapter(adapter);
        hxyListView.setOnItemClickListener(this);

    }

    private void setPullLvHeight(ListView pull) {
        int totalHeight = 0;
        ListAdapter adapter = pull.getAdapter();
        for (int i = 0, len = adapter.getCount(); i < len; i++) { //listAdapter.getCount()返回数据项的数目
            View listItem = adapter.getView(i, null, pull);
            listItem.measure(0, 0); //计算子项View 的宽高
            totalHeight += listItem.getMeasuredHeight(); //统计所有子项的总高度
        }

        ViewGroup.LayoutParams params = pull.getLayoutParams();
        params.height = totalHeight + (pull.getDividerHeight() * (pull.getCount() - 1));
        pull.setLayoutParams(params);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
