package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteTable;
import com.loibv.t1p.dbHelper.DatabaseHelper;

public class City implements ISqliteTable {

    private int id;
    private String name;
    private String type;

    public int getId() {
        return this.id;
    }

    public void setId(int Id) {
        this.id = Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String Name) {
        this.name = Name;
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
        this.setType(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_TYPE)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NAME, this.getName());
        values.put(DatabaseHelper.KEY_TYPE, this.getType());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
