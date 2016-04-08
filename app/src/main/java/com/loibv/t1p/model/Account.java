package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteTable;
import com.loibv.t1p.dbHelper.DatabaseHelper;

/**
 * Created by vanloibui on 4/5/16.
 */
public class Account implements ISqliteTable {
    private int id;
    private String email;
    private String password;
    private String phone;
    private String avatar;
    private String fullname;
    private int roleId;
    private int isActive;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getRoleId() {
        return this.roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public int getIsActive() {
        return this.isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setEmail(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_EMAIL)));
        this.setPassword(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PASSWORD)));
        this.setPhone(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_PHONE)));
        this.setAvatar(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_AVATAR)));
        this.setFullname(cursor.getString(cursor.getColumnIndex(DatabaseHelper.KEY_FULLNAME)));
        this.setRoleId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ROLEID)));
        this.setIsActive(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ISACTIVE)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_EMAIL, this.getEmail());
        values.put(DatabaseHelper.KEY_PASSWORD, this.getPassword());
        values.put(DatabaseHelper.KEY_PHONE, this.getPhone());
        values.put(DatabaseHelper.KEY_AVATAR, this.getAvatar());
        values.put(DatabaseHelper.KEY_FULLNAME, this.getFullname());
        values.put(DatabaseHelper.KEY_ROLEID, this.getRoleId());
        values.put(DatabaseHelper.KEY_ISACTIVE, this.getIsActive());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
