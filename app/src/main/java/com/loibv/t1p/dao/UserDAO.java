package com.loibv.t1p.dao;

import com.loibv.t1p.dbHelper.DatabaseHelper;

/**
 * Created by vanloibui on 4/12/16.
 */
public class UserDAO extends CommonDAO{

    public UserDAO(DatabaseHelper databaseHelper, String tableName, String primaryField, Class entityClass) {
        super(databaseHelper, tableName, primaryField, entityClass);
    }

}
