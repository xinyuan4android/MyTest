package com.example.iningke.myapplication.bean;

import java.io.Serializable;

/**
 * Created by hxy on  2017/3/4.
 */
public class HxyActivityBean implements Serializable {
    private String title;
    private String content;

    public HxyActivityBean(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public HxyActivityBean() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
