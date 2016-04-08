package com.loibv.t1p.dbHelper;

import android.app.Application;
import android.content.Context;

/**
 * Created by vanloibui on 4/4/16.
 */
public class ContextHelper extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        ContextHelper.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ContextHelper.context;
    }
}