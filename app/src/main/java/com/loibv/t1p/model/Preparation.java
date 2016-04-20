package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteData;
import com.loibv.t1p.dbHelper.DatabaseHelper;

public class Preparation implements ISqliteData {
    private int id;
    private int memberId;
    private String object;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMemberId() {
        return this.memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public String getObject() {
        return this.object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setMemberId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_MEMBERID)));
        this.setObject(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_OBJECT)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_MEMBERID, this.getMemberId());
        values.put(DatabaseHelper.KEY_OBJECT, this.getObject());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
