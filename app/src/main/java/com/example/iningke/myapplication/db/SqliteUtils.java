package com.example.iningke.myapplication.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hxy on  2017/6/9.
 */
public class SqliteUtils extends SQLiteOpenHelper {
    public static String path = Environment.getExternalStorageDirectory() + "/";

    /**
     * @param context ：上下文对像
     * @param name    ：数据库名字
     * @param factory ：游标创建的工厂对象，
     * @param version ：数据库的版本号
     */
    public SqliteUtils(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_message (_id integer primary key autoincrement, " +
                "messageId  varchar(30)," +
                "title varchar(30)," +
                "date varchar(30)," +
                "content varchar(100)," +
                "link varchar(100)," +
                "type varchar(30))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addDataToDB(MessageBean bean) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("messageId", bean.getMessageId());
        values.put("title", bean.getTitle());
        values.put("date", bean.getDate());
        values.put("content", bean.getContent());
        values.put("link", bean.getLink());
        values.put("type", bean.getTitle());
        db.insert("tb_message", null, values);
    }

    public List<MessageBean> getDataFromDB() {
        List<MessageBean> bean = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor;
        cursor = db.rawQuery("select * from tb_message ", null);
        while (cursor.moveToNext()) {
            String messageId = cursor.getString(cursor.getColumnIndex("messageId"));
            String title = cursor.getString(cursor.getColumnIndex("title"));
            String date = cursor.getString(cursor.getColumnIndex("date"));
            String content = cursor.getString(cursor.getColumnIndex("content"));
            String link = cursor.getString(cursor.getColumnIndex("link"));
            String type = cursor.getString(cursor.getColumnIndex("type"));
            MessageBean entity = new MessageBean(messageId, title, content, date, type, link);
            bean.add(entity);
        }
        cursor.close();
        return bean;

    }

    public void upDataFromDB(MessageBean bean) {
        if (bean == null) {
            return;
        }
        SQLiteDatabase db = getReadableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("title", bean.getTitle());
        cv.put("date", bean.getDate());
        cv.put("content", bean.getContent());
        cv.put("link", bean.getLink());
        cv.put("type", bean.getType());
        db.update("tb_message", cv, "messageId = ?", new String[]{bean.getMessageId()});
    }

    public void deleteDbData(String messageId) {
        if (messageId != null) {
            SQLiteDatabase db = getWritableDatabase();
            db.delete("tb_message", "messageId = ?", new String[]{messageId});
        }
//        db.rawQuery("delete from tb_location where orderid = ?", new String[]{orderId});
    }
}
