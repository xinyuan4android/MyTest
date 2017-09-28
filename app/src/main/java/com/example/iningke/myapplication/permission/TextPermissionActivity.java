package com.example.iningke.myapplication.permission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.iningke.myapplication.R;

public class TextPermissionActivity extends AppCompatActivity {

    private Button dialog;
    private Button dialog2;
    private int call_phone_request_code = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_permission);
        initView();
    }

    private void initView() {
        dialog = (Button) findViewById(R.id.btn_dialog);
        dialog2 = (Button) findViewById(R.id.btn_dialog2);
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });
        dialog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TextPermissionActivity.this, TextPermission2Activity.class));
            }
        });
    }

    /**
     * 检查拨打电话权限
     */
    public void checkPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //申请CALL_PHONE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},
                    call_phone_request_code);
            return;
        } else
            call("18363852860");

    }

    private void call(String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri parse = Uri.parse("tel:" + phoneNumber);
        intent.setData(parse);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == call_phone_request_code) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                call("18363852860");
            } else {
                // Permission Denied
                Toast.makeText(TextPermissionActivity.this, "没有权限~", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
