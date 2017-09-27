package com.example.iningke.myapplication.retrofit;

import java.io.Serializable;

/**
 * Created by hxy on  2017/9/27.
 */

public class BaseModel implements Serializable {

    /**
     * msg : 修改成功
     * success : true
     */

    private String msg;
    private boolean success;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }


}
