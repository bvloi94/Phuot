package com.loibv.t1p.utils;

import java.text.SimpleDateFormat;

/**
 * Created by vanloibui on 4/18/16.
 */
public class DateUtil {

    private static SimpleDateFormat dateFormat;

    public static SimpleDateFormat getDateFormat() {
        if (dateFormat != null)
            return dateFormat;
        else {
            return new SimpleDateFormat("HH:mm dd/MM/yyyy");
        }
    }
}
