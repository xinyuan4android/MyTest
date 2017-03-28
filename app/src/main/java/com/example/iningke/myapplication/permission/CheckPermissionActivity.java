package com.example.iningke.myapplication.permission;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by hxy on  2016/11/7.
 */
public abstract class CheckPermissionActivity extends AppCompatActivity {
//    private int request_code = 111;

    protected void checkPermission(String perission, int request_code) {
        if (ActivityCompat.checkSelfPermission(this, perission) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            //申请CALL_PHONE权限
            ActivityCompat.requestPermissions(this, new String[]{perission},
                    request_code);
            return;
        } else
            //已经允许权限，直接调用方法
            permissionGrant(request_code);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode, grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // Permission Granted
            permissionGrant(requestCode);
        } else {
            // Permission Denied
            permissionDenied(requestCode);
        }

    }

    /**
     * 权限申请拒绝后，执行的代码
     *
     * @param requestCode
     */
    protected abstract void permissionDenied(int requestCode);

    /**
     * 权限申请成功后，代码
     *
     * @param requestCode
     */
    protected abstract void permissionGrant(int requestCode);


}
