package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteTable;
import com.loibv.t1p.dbHelper.DatabaseHelper;

public class TripPlace implements ISqliteTable {
    private int id;
    private int tripId;
    private int placeId;

    public void setId(int id) {
        this.id = id;
    }

    public long getId() {
        return this.id;
    }

    public int getTripId() {
        return this.tripId;
    }

    public void setTripId(int TripId) {
        this.tripId = TripId;
    }

    public int getPlaceId() {
        return this.placeId;
    }

    public void setPlaceId(int PlaceId) {
        this.placeId = PlaceId;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setTripId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_TRIPID)));
        this.setPlaceId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_PLACEID)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();

        values.put(DatabaseHelper.KEY_TRIPID, this.getTripId());
        values.put(DatabaseHelper.KEY_PLACEID, this.getPlaceId());

        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
