package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteTable;
import com.loibv.t1p.dbHelper.DatabaseHelper;

public class Trip implements ISqliteTable {
    private int id;
    private String name;
    private String description;
    private String startTime;
    private String endTime;
    private String gatheringPlace;
    private int isFinished;
    private int adminId;
    
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
    
    public int getIsFinished() {
        return this.isFinished;
    }
    
    public void setIsFinished(int isFinished) {
        this.isFinished = isFinished;
    }
    
    public int getAdminId() {
        return this.adminId;
    }
    
    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setName(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_NAME)));
        this.setDescription(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_DESCRIPTION)));
        this.setStartTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_STARTTIME)));
        this.setEndTime(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_ENDTIME)));
        this.setGatheringPlace(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_GATHERINGPLACE)));
        this.setIsFinished(cursor.getInt(cursor.getColumnIndex("IsFinished")));
        this.setAdminId(cursor.getInt(cursor.getColumnIndex("AdminId")));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_NAME, this.getName());
        values.put(DatabaseHelper.KEY_DESCRIPTION, this.getDescription());
        values.put(DatabaseHelper.KEY_STARTTIME, this.getStartTime());
        values.put(DatabaseHelper.KEY_ENDTIME, this.getEndTime());
        values.put(DatabaseHelper.KEY_GATHERINGPLACE, this.getGatheringPlace());
        values.put(DatabaseHelper.KEY_ISFINISHED, this.getIsFinished());
        values.put(DatabaseHelper.KEY_ADMINID, this.getAdminId());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
