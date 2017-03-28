package com.example.iningke.myapplication.permission;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.iningke.myapplication.R;

public class TextPermission2Activity extends CheckPermissionActivity {
    private Button call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_permission2);
        initView();
    }

    private void initView() {
        call = (Button) findViewById(R.id.call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission(Manifest.permission.CALL_PHONE, 111);
            }
        });
    }


    @Override
    protected void permissionDenied(int requestCode) {
        Toast.makeText(TextPermission2Activity.this, "权限拒绝~", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void permissionGrant(int requestCode) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri parse = Uri.parse("tel:" + "18363852860");
        intent.setData(parse);
        //TODO 拨打电话
        startActivity(intent);
    }
}
