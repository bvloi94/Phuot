package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteTable;
import com.loibv.t1p.dbHelper.DatabaseHelper;

public class Place implements ISqliteTable {
    private int id;
    private String name;
    private String address;
    private int districtId;
    private String image;
    private String description;
    private String location;

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

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDistrictId() {
        return this.districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME)));
        this.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_ADDRESS)));
        this.setDistrictId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_DISTRICTID)));
        this.setImage(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_IMAGE)));
        this.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DESCRIPTION)));
        this.setLocation(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_LOCATION)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NAME, this.getName());
        values.put(DatabaseHelper.KEY_ADDRESS, this.getAddress());
        values.put(DatabaseHelper.KEY_DISTRICTID, this.getDistrictId());
        values.put(DatabaseHelper.KEY_IMAGE, this.getImage());
        values.put(DatabaseHelper.KEY_DESCRIPTION, this.getDescription());
        values.put(DatabaseHelper.KEY_LOCATION, this.getLocation());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
