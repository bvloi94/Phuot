package com.loibv.t1p.dao;

import com.loibv.t1p.model.Account;
import com.loibv.t1p.model.City;
import com.loibv.t1p.model.District;
import com.loibv.t1p.model.Fund;
import com.loibv.t1p.model.FundTransaction;
import com.loibv.t1p.model.Notification;
import com.loibv.t1p.model.Place;
import com.loibv.t1p.model.Preparation;
import com.loibv.t1p.model.Transaction;
import com.loibv.t1p.model.Trip;
import com.loibv.t1p.model.TripPlace;
import com.loibv.t1p.model.TripPreparation;

/**
 * Created by vanloibui on 4/4/16.
 */
public class SqliteTableFactory {
    private SqliteTableFactory() {

    }

    private static SqliteTableFactory instance;

    public static SqliteTableFactory getInstance() {
        if (instance == null) {
            instance = new SqliteTableFactory();
        }
        return instance;
    }

    public <TEntity extends ISqliteTable> TEntity getSqliteTableObject(Class<TEntity> c) {

        if (c == Account.class) {
            return (TEntity) new Account();
        } else if (c == City.class) {
            return (TEntity) new City();
        } else if (c == District.class) {
            return (TEntity) new District();
        } else if (c == Fund.class) {
            return (TEntity) new Fund();
        } else if (c == FundTransaction.class) {
            return (TEntity) new FundTransaction();
        } else if (c == Notification.class) {
            return (TEntity) new Notification();
        } else if (c == Place.class) {
            return (TEntity) new Place();
        } else if (c == Preparation.class) {
            return (TEntity) new Preparation();
        } else if (c == Transaction.class) {
            return (TEntity) new Transaction();
        } else if (c == Trip.class) {
            return (TEntity) new Trip();
        } else if (c == TripPlace.class) {
            return (TEntity) new TripPlace();
        } else if (c == TripPreparation.class) {
            return (TEntity) new TripPreparation();
        }
        return null;
    }
}
