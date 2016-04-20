package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.loibv.t1p.dao.ISqliteData;
import com.loibv.t1p.dbHelper.DatabaseHelper;

import java.io.Serializable;

public class TripPlace implements ISqliteData, Parcelable, Serializable {
    private int id;
    private int tripId;
    private int placeId;
    private Integer nextTripPlaceId;
    private String vehicle;

    public TripPlace() {
    }

    protected TripPlace(Parcel in) {
        id = in.readInt();
        tripId = in.readInt();
        placeId = in.readInt();
        nextTripPlaceId = in.readInt();
        vehicle = in.readString();
    }

    public static final Creator<TripPlace> CREATOR = new Creator<TripPlace>() {
        @Override
        public TripPlace createFromParcel(Parcel in) {
            return new TripPlace(in);
        }

        @Override
        public TripPlace[] newArray(int size) {
            return new TripPlace[size];
        }
    };

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

    public Integer getNextTripPlaceId() {
        return nextTripPlaceId;
    }

    public void setNextTripPlaceId(Integer nextTripPlaceId) {
        this.nextTripPlaceId = nextTripPlaceId;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setTripId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_TRIPID)));
        this.setPlaceId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_PLACEID)));
        this.setNextTripPlaceId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_NEXTTRIPID)));
        this.setVehicle(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_VEHICLE)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_TRIPID, this.getTripId());
        values.put(DatabaseHelper.KEY_PLACEID, this.getPlaceId());
        values.put(DatabaseHelper.KEY_NEXTTRIPID, this.getNextTripPlaceId());
        values.put(DatabaseHelper.KEY_VEHICLE, this.getVehicle());

        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(tripId);
        dest.writeInt(placeId);
        dest.writeInt(nextTripPlaceId);
        dest.writeString(vehicle);
    }

}
