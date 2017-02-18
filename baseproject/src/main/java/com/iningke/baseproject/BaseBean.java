package com.iningke.baseproject;

import java.io.Serializable;

/**
 * Created by iningke on 2016/6/13.
 */

public class BaseBean implements Serializable{
    /**
     * status : 100
     * errstring : 用户名不能为空
     */
    private int status;
    private String errstring;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getErrstring() {
        return errstring;
    }

    public void setErrstring(String errstring) {
        this.errstring = errstring;
    }
}
