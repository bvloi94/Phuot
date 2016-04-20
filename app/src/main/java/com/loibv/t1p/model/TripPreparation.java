package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteData;
import com.loibv.t1p.dbHelper.DatabaseHelper;

public class TripPreparation implements ISqliteData {
    private int id;
    private int tripId;
    private int preparationId;
    private String description;

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public int getTripId() {
        return this.tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public int getPreparationId() {
        return this.preparationId;
    }

    public void setPreparationId(int preparationId) {
        this.preparationId = preparationId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setTripId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_TRIPID)));
        this.setPreparationId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_PREPARATIONID)));
        this.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DESCRIPTION)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TRIPID, this.getTripId());
        values.put(DatabaseHelper.KEY_PREPARATIONID, this.getPreparationId());
        values.put(DatabaseHelper.KEY_DESCRIPTION, this.getDescription());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
