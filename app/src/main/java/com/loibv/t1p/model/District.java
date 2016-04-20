package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteData;
import com.loibv.t1p.dbHelper.DatabaseHelper;

public class District implements ISqliteData {

    private int id;
    private String name;
    private int cityId;
    private String type;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCityId() {
        return this.cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME)));
        this.setCityId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_CITYID)));
        this.setType(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TYPE)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NAME, this.getName());
        values.put(DatabaseHelper.KEY_CITYID, this.getCityId());
        values.put(DatabaseHelper.KEY_TYPE, this.getType());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
