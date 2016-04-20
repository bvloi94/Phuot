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
public class EntityFactory {

    private EntityFactory() {

    }

    private static EntityFactory instance;

    public static EntityFactory getInstance() {
        if (instance == null) {
            instance = new EntityFactory();
        }
        return instance;
    }

    public <T> T getObject(Class<T> c) {

        if (c == Account.class) {
            return (T) new Account();
        } else if (c == City.class) {
            return (T) new City();
        } else if (c == District.class) {
            return (T) new District();
        } else if (c == Fund.class) {
            return (T) new Fund();
        } else if (c == FundTransaction.class) {
            return (T) new FundTransaction();
        } else if (c == Notification.class) {
            return (T) new Notification();
        } else if (c == Place.class) {
            return (T) new Place();
        } else if (c == Preparation.class) {
            return (T) new Preparation();
        } else if (c == Transaction.class) {
            return (T) new Transaction();
        } else if (c == Trip.class) {
            return (T) new Trip();
        } else if (c == TripPlace.class) {
            return (T) new TripPlace();
        } else if (c == TripPreparation.class) {
            return (T) new TripPreparation();
        }
        return null;
    }
}
