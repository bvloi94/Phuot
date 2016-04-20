package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.loibv.t1p.dao.ISqliteData;
import com.loibv.t1p.dbHelper.DatabaseHelper;
import com.loibv.t1p.utils.JsonGeneratorUtil;

import java.io.Serializable;

/**
 * Created by vanloibui on 4/5/16.
 */
public class Account implements ISqliteData, Serializable, Parcelable {
    private int id;
    private String email;
    private String password;
    private String phone;
    private String avatar;
    private String fullname;
    private int roleId;
    private int isActive;

    public Account() {
    }

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

    @JsonSerialize(using = JsonGeneratorUtil.NumericBooleanSerializer.class)
    public int getIsActive() {
        return this.isActive;
    }

    @JsonDeserialize(using = JsonGeneratorUtil.NumericBooleanDeserializer.class)
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(email);
        dest.writeString(password);
        dest.writeString(phone);
        dest.writeString(avatar);
        dest.writeString(fullname);
        dest.writeInt(roleId);
        dest.writeInt(isActive);
    }

    protected Account(Parcel in) {
        id = in.readInt();
        email = in.readString();
        password = in.readString();
        phone = in.readString();
        avatar = in.readString();
        fullname = in.readString();
        roleId = in.readInt();
        isActive = in.readInt();
    }

    public static final Creator<Account> CREATOR = new Creator<Account>() {
        @Override
        public Account createFromParcel(Parcel in) {
            return new Account(in);
        }

        @Override
        public Account[] newArray(int size) {
            return new Account[size];
        }
    };

//    @Override
//    public JSONObject toJson() throws JSONException {
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("id", this.id);
//        jsonObject.put("email", this.email);
//        jsonObject.put("password", this.password);
//        jsonObject.put("phone", this.phone);
//        jsonObject.put("avatar", this.avatar);
//        jsonObject.put("fullname", this.fullname);
//        jsonObject.put("roleId", this.roleId);
//        jsonObject.put("isActive", this.isActive == 1);
//        return jsonObject;
//    }
//
//    @Override
//    public Account fromJson(JSONObject jsonObject) throws JSONException, ParseException {
//        this.id = jsonObject.getInt("id");
//        this.email = jsonObject.getString("email");
//        this.password = jsonObject.getString("password");
//        this.phone = jsonObject.getString("phone");
//        this.avatar = jsonObject.getString("avatar");
//        this.fullname = jsonObject.getString("fullname");
//        this.roleId = jsonObject.getInt("roleId");
//        if (jsonObject.getBoolean("isActive")) {
//            this.isActive = 1;
//        } else {
//            this.isActive = 0;
//        }
//        return this;
//    }
}
