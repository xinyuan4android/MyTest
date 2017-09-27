package com.example.iningke.myapplication.retrofit;

import java.io.Serializable;

/**
 * Created by hxy on  2017/9/27.
 */

public class JKModel implements Serializable {

    /**
     * msg : http://app.jiakaojingling.com:80/jkjl/static/upload/member/headImg/20170927/15064799786371506479423612.jpg
     * status : 200
     */

    private String msg;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
