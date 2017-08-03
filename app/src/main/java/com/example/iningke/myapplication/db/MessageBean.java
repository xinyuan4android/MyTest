package com.example.iningke.myapplication.db;

import java.io.Serializable;

/**
 * Created by hxy on  2017/6/9.
 */
public class MessageBean implements Serializable {
    private String messageId;
    private String title;
    private String content;
    private String date;
    private String type;
    private String link;

    public MessageBean() {
    }

    public MessageBean(String messageId, String title, String content, String date, String type, String link) {
        this.messageId = messageId;
        this.title = title;
        this.content = content;
        this.date = date;
        this.type = type;
        this.link = link;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
