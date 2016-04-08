package com.loibv.t1p.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.loibv.t1p.dao.ISqliteTable;
import com.loibv.t1p.dbHelper.DatabaseHelper;

/**
 * Created by vanloibui on 4/5/16.
 */
public class FundTransaction  implements ISqliteTable{
    private int id;
    private int fundId;
    private int transactionId;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFundId() {
        return this.fundId;
    }

    public void setFundId(int fundId) {
        this.fundId = fundId;
    }

    public int getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    @Override
    public void setValue(Cursor cursor) {
        this.setId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_ID)));
        this.setFundId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_FUNDID)));
        this.setTransactionId(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.KEY_TRANSACTIONID)));
    }

    @Override
    public ContentValues getContentValues() {
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.KEY_FUNDID, this.getFundId());
        values.put(DatabaseHelper.KEY_TRANSACTIONID, this.getTransactionId());
        return values;
    }

    @Override
    public String getPrimaryValue() {
        return String.valueOf(this.id);
    }
}
