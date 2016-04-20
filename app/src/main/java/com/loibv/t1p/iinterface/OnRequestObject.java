package com.loibv.t1p.iinterface;

/**
 * Created by vanloibui on 4/18/16.
 */
public interface OnRequestObject<T> {
    public void onTaskCompleted(T obj, boolean error, String message);
}
