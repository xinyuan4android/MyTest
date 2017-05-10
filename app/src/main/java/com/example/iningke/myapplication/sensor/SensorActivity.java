package com.example.iningke.myapplication.sensor;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.example.iningke.myapplication.R;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    private TextView mTvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        initView();
    }

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;

    private void initView() {
        mTvInfo = (TextView) findViewById(R.id.tv_info);
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
    }

    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }


    public static final float STANDARD_GRAVITY = 9.80665F;

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Log.d("linc", "value size: " + sensorEvent.values.length);

        //x轴方向的加速度。从左往右
        float xValue = sensorEvent.values[0];// Acceleration minus Gx on the x-axis

        //y轴方向的加速度。 从下往上
        float yValue = sensorEvent.values[1];//Acceleration minus Gy on the y-axis

        //z轴方向的加速度。 从内到外
        float zValue = sensorEvent.values[2];//Acceleration minus Gz on the z-axis
        mTvInfo.setText("x轴： " + xValue + "  y轴： " + yValue + "  z轴： " + zValue);
        if (xValue > STANDARD_GRAVITY) {
            mTvInfo.append("\n重力指向设备左边");
        } else if (xValue < -STANDARD_GRAVITY) {
            mTvInfo.append("\n重力指向设备右边");
        } else if (yValue > STANDARD_GRAVITY) {
            mTvInfo.append("\n重力指向设备下边");
        } else if (yValue < -STANDARD_GRAVITY) {
            mTvInfo.append("\n重力指向设备上边");
        } else if (zValue > STANDARD_GRAVITY) {
            mTvInfo.append("\n屏幕朝上");
        } else if (zValue < -STANDARD_GRAVITY) {
            mTvInfo.append("\n屏幕朝下");
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
