package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loibv.t1p.dao.ISqliteData;
import com.loibv.t1p.dbHelper.DatabaseHelper;

import java.io.Serializable;

public class Trip implements ISqliteData, Parcelable, Serializable {

    private int id;
    private String name;
    private int leaderId;
    private String createTime;
    private String startTime;
    private String endTime;
    private String gatheringPlace;
    private int status;
    private String description;

    public Trip() {
    }

    protected Trip(Parcel in) {
        id = in.readInt();
        name = in.readString();
        leaderId = in.readInt();
        createTime = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        gatheringPlace = in.readString();
        status = in.readInt();
        description = in.readString();
    }

    public static final Creator<Trip> CREATOR = new Creator<Trip>() {
        @Override
        public Trip createFromParcel(Parcel in) {
            return new Trip(in);
        }

        @Override
        public Trip[] newArray(int size) {
            return new Trip[size];
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public static Creator<Trip> getCREATOR() {
        return CREATOR;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getGatheringPlace() {
        return this.gatheringPlace;
    }

    public void setGatheringPlace(String gatheringPlace) {
        this.gatheringPlace = gatheringPlace;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLeaderId() {
        return this.leaderId;
    }

    public void setLeaderId(int leaderId) {
        this.leaderId = leaderId;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME)));
        this.setLeaderId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_LEADERID)));
        this.setCreateTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_CREATETIME)));
        this.setStartTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_STARTTIME)));
        this.setEndTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_ENDTIME)));
        this.setGatheringPlace(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_GATHERINGPLACE)));
        this.setStatus(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_STATUS)));
        this.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DESCRIPTION)));
    }


    @Override
    @JsonIgnore
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NAME, this.getName());
        values.put(DatabaseHelper.KEY_LEADERID, this.getLeaderId());
        values.put(DatabaseHelper.KEY_CREATETIME, this.getDescription());
        values.put(DatabaseHelper.KEY_STARTTIME, this.getStartTime());
        values.put(DatabaseHelper.KEY_ENDTIME, this.getEndTime());
        values.put(DatabaseHelper.KEY_GATHERINGPLACE, this.getGatheringPlace());
        values.put(DatabaseHelper.KEY_STATUS, this.getStatus());
        values.put(DatabaseHelper.KEY_DESCRIPTION, this.getDescription());
        return values;
    }

    @Override
    @JsonIgnore
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }

    @Override
    @JsonIgnore
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeInt(leaderId);
        dest.writeString(createTime);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(gatheringPlace);
        dest.writeInt(status);
        dest.writeString(description);
    }

}
