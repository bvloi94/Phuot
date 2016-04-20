package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteData;
import com.loibv.t1p.dbHelper.DatabaseHelper;

/**
 * Created by vanloibui on 4/5/16.
 */
public class Notification implements ISqliteData {
    private int id;
    private int receiverId;
    private int senderId;
    private int type;
    private String message;
    private int isReaded;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReceiverId() {
        return this.receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public int getSenderId() {
        return this.senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIsReaded() {
        return this.isReaded;
    }

    public void setIsReaded(int isReaded) {
        this.isReaded = isReaded;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setReceiverId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_RECEIVERID)));
        this.setSenderId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_SENDERID)));
        this.setType(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_TYPE)));
        this.setMessage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_MESSAGE)));
        this.setIsReaded(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ISREADED)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_RECEIVERID, this.getReceiverId());
        values.put(DatabaseHelper.KEY_SENDERID, this.getSenderId());
        values.put(DatabaseHelper.KEY_TYPE, this.getType());
        values.put(DatabaseHelper.KEY_MESSAGE, this.getMessage());
        values.put(DatabaseHelper.KEY_ISREADED, this.getIsReaded());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
