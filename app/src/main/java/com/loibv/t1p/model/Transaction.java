package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteData;
import com.loibv.t1p.dbHelper.DatabaseHelper;

/**
 * Created by vanloibui on 4/5/16.
 */
public class Transaction implements ISqliteData {
    private int id;
    private int amount;
    private int type;
    private String description;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String Description) {
        this.description = Description;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setAmount(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_AMOUNT)));
        this.setType(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_TYPE)));
        this.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DESCRIPTION)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_AMOUNT, this.getAmount());
        values.put(DatabaseHelper.KEY_TYPE, this.getType());
        values.put(DatabaseHelper.KEY_DESCRIPTION, this.getDescription());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
