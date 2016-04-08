package com.loibv.t1p.dao;

import com.loibv.t1p.dbHelper.DatabaseHelper;
import com.loibv.t1p.model.City;

/**
 * Created by vanloibui on 4/4/16.
 */
public class CityDAO extends CommonDAO<City> {

    public CityDAO(DatabaseHelper databaseHelper) {
        super(databaseHelper, DatabaseHelper.TABLE_CITY, DatabaseHelper.KEY_ID, City.class);
    }


}
