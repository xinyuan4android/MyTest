package com.example.iningke.myapplication.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.iningke.myapplication.R;
import com.iningke.baseproject.utils.LogUtils;
import com.iningke.baseproject.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BlueToothActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private BluetoothAdapter mBluetoothAdapter;
    private ArrayAdapter<String> arrayAdapter;
    private List<String> dataSource = new ArrayList<>();
    private ListView listView;
    private List<BluetoothDevice> dataSource_device = new ArrayList<>();
    private ParingReceived received;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);

        listView = (ListView) findViewById(R.id.listView);
        arrayAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, dataSource);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        // Register for broadcasts when a device is discovered
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);

        // Register for broadcasts when discovery has finished
        filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        this.registerReceiver(mReceiver, filter);

        received = new ParingReceived();
        registerReceiver(received, new IntentFilter(BluetoothDevice.ACTION_PAIRING_REQUEST));
    }

    public void getBluetooth(View view) {
        //获取已经配对的蓝牙设备
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice device : pairedDevices) {
                dataSource_device.add(device);
                dataSource.add(device.getName() + ":" + device.getAddress());
            }
        }
        arrayAdapter.notifyDataSetChanged();
        //该进程是异步的，该方法将立即返回一个布尔值，指示是否已成功启动
        //发现过程通常涉及一个约12秒的查询扫描，然后通过一个页面扫描每个发现的设备来检索其蓝牙名称。
        boolean b = mBluetoothAdapter.startDiscovery();
    }

    public void openBluetooth(View view) {
        if (mBluetoothAdapter == null) {
            // 设备不支持蓝牙
            UIUtils.showToastSafe("设备不支持蓝牙");
        } else {
            if (mBluetoothAdapter.isEnabled()) {
                UIUtils.showToastSafe("已经开启蓝牙");
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }

    }

    public void closeBluetooth(View view) {
        mBluetoothAdapter.disable();
    }

    // The BroadcastReceiver that listens for discovered devices and
    // changes the title when discovery is finished
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                UIUtils.showToastSafe("发现新设备");
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // If it's already paired, skip it, because it's been listed already
                if (device.getBondState() != BluetoothDevice.BOND_BONDED) {
                    dataSource_device.add(device);
                    dataSource.add(device.getName() + "\n" + device.getAddress());
                    arrayAdapter.notifyDataSetChanged();
                }
                // When discovery is finished, change the Activity title
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                UIUtils.showToastSafe("扫描结束");
                setProgressBarIndeterminateVisibility(false);
                mBluetoothAdapter.cancelDiscovery();
                unregisterReceiver(this);
                mReceiver = null;
            }
        }
    };

    private class ParingReceived extends BroadcastReceiver {
        String pin = "1234";  //此处为你要连接的蓝牙设备的初始密钥，一般为1234或0000

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction(); //得到action
            Log.e("action1=", action);
            BluetoothDevice btDevice = null;  //创建一个蓝牙device对象
            // 从Intent中获取设备对象
            btDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            if (action.equals("android.bluetooth.device.action.PAIRING_REQUEST")) //再次得到的action，会等于PAIRING_REQUEST{
                try {
                    //1.确认配对
                    ClsUtils.setPairingConfirmation(btDevice.getClass(), btDevice, true);
                    //2.终止有序广播
                    Log.i("order...", "isOrderedBroadcast:" + isOrderedBroadcast() + ",isInitialStickyBroadcast:" + isInitialStickyBroadcast());
                    abortBroadcast();//如果没有将广播终止，则会出现一个一闪而过的配对框。
                    //3.调用setPin方法进行配对...
                    boolean ret = ClsUtils.setPin(btDevice.getClass(), btDevice, pin);
                    LogUtils.e("setPin 配对" + ret);
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReceiver != null) {
            unregisterReceiver(mReceiver);
        }
        if (received != null) {
            unregisterReceiver(received);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            boolean bond = ClsUtils.createBond(dataSource_device.get(position).getClass(), dataSource_device.get(position));
            UIUtils.showToastSafe(dataSource_device.get(position).getName() + "配对" + bond);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
