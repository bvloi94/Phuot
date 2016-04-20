package com.loibv.t1p.iinterface;

import java.util.List;

/**
 * Created by vanloibui on 4/18/16.
 */
public interface OnRequestArray<T> {
    public void onTaskCompleted(List<T> list, boolean error, String message);
}
