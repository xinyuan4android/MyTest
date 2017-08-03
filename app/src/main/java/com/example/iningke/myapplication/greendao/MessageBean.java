package com.example.iningke.myapplication.greendao;

import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by hxy on  2017/6/9.
 */
public class MessageBean {
    @Id
    private Long messageId;
    @Property(nameInDb = "TITLE")
    private String title;
    @Property(nameInDb = "CONTENT")
    private String content;
    @Property(nameInDb = "DATE")
    private String date;
    @Property(nameInDb = "LINK")
    private String link;
    @Property(nameInDb = "TYPE")
    private int type;
}
