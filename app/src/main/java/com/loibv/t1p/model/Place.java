package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.loibv.t1p.dao.ISqliteData;
import com.loibv.t1p.dbHelper.DatabaseHelper;

public class Place implements ISqliteData, Parcelable {

    private int id;
    private String name;
    private String address;
    private String imagePath;
    private String description;
    private double latitude;
    private double longitude;

    protected Place(Parcel in) {
        id = in.readInt();
        name = in.readString();
        address = in.readString();
        imagePath = in.readString();
        description = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
    }

    public static final Creator<Place> CREATOR = new Creator<Place>() {
        @Override
        public Place createFromParcel(Parcel in) {
            return new Place(in);
        }

        @Override
        public Place[] newArray(int size) {
            return new Place[size];
        }
    };

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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME)));
        this.setAddress(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_ADDRESS)));
        this.setImagePath(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_IMAGE_PATH)));
        this.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DESCRIPTION)));
        this.setLatitude(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.KEY_LATITUDE)));
        this.setLongitude(cursor.getDouble(cursor.getColumnIndex(DatabaseHelper.KEY_LONGITUDE)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NAME, this.getName());
        values.put(DatabaseHelper.KEY_ADDRESS, this.getAddress());
        values.put(DatabaseHelper.KEY_IMAGE_PATH, this.getImagePath());
        values.put(DatabaseHelper.KEY_DESCRIPTION, this.getDescription());
        values.put(DatabaseHelper.KEY_LATITUDE, this.getLatitude());
        values.put(DatabaseHelper.KEY_LONGITUDE, this.getLongitude());
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
        dest.writeString(name);
        dest.writeString(address);
        dest.writeString(imagePath);
        dest.writeString(description);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
    }
}
