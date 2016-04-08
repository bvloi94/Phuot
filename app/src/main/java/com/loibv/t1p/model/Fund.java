package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteTable;
import com.loibv.t1p.dbHelper.DatabaseHelper;

/**
 * Created by vanloibui on 4/5/16.
 */
public class Fund implements ISqliteTable{
    private int id;
    private int total;
    private int managerId;
    private int tripId;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTotal() {
        return this.total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getManagerId() {
        return this.managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public int getTripId() {
        return this.tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setTotal(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_TOTAL)));
        this.setManagerId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_MANAGERID)));
        this.setTripId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_TRIPID)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TOTAL, this.getTotal());
        values.put(DatabaseHelper.KEY_MANAGERID, this.getManagerId());
        values.put(DatabaseHelper.KEY_TRIPID, this.getTripId());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}