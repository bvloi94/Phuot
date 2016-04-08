package com.loibv.t1p.data;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

/**
 * Created by vanloibui on 4/4/16.
 */
public interface IJsonConvertable<T> {
    JSONObject toJson() throws JSONException;

    <T> T fromJson(JSONObject object) throws JSONException, ParseException;
}