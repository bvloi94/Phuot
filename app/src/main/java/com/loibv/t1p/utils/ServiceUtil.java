package com.loibv.t1p.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loibv.t1p.ApplicationController;
import com.loibv.t1p.dao.EntityFactory;
import com.loibv.t1p.iinterface.OnRequestArray;
import com.loibv.t1p.iinterface.OnRequestObject;
import com.loibv.t1p.iinterface.OnSendObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vanloibui on 4/10/16.
 */
public class ServiceUtil<T> {

    private Context ctx;

    // Use in request JSONObject
    private HashMap<String, String> hashMap;

    public ServiceUtil(Context ctx) {
        this.ctx = ctx;
        hashMap = new HashMap<>();
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = ((ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public void retrieveObjectData(String requestUrl, final OnRequestObject<T> taskCompleted, final Class<T> klass) {
        retrieveObjectData(requestUrl, new JSONObject(hashMap), taskCompleted, klass);
    }

    public void retrieveObjectData(String requestUrl, JSONObject jsonPostObj, final OnRequestObject<T> taskCompleted, final Class<T> klass) {
        if (isNetworkAvailable()) {
            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST,
                    requestUrl, jsonPostObj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getBoolean("success") == true) {
                                    ObjectMapper mapper = new ObjectMapper();
                                    T object = (T) mapper.readValue(String.valueOf(response.getJSONObject("data")), klass);
                                    taskCompleted.onTaskCompleted(object, false, null);
                                } else {
                                    taskCompleted.onTaskCompleted(null, false, null);
                                }
                            } catch (JSONException e) {
                                taskCompleted.onTaskCompleted(null, true, e.getMessage());
                            } catch (JsonParseException e) {
                                taskCompleted.onTaskCompleted(null, true, e.getMessage());
                            } catch (JsonMappingException e) {
                                taskCompleted.onTaskCompleted(null, true, e.getMessage());
                            } catch (IOException e) {
                                taskCompleted.onTaskCompleted(null, true, e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    taskCompleted.onTaskCompleted(null, true, volleyError.getMessage());
                }
            }) {
                /* Passing some request headers*/
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=utf-8");
                    return headers;
                }
            };
            // Adding request to request queue
            ApplicationController.getInstance().addToRequestQueue(jsonObjRequest, Const.TAG_JSONOBJ_REQUEST);
            //Canceling request
//		ApplicationController.getInstance().getRequestQueue().cancelAll(TAG_JSONOBJ_REQUEST);
        } else {
            Toast.makeText(ctx, "Network not available.", Toast.LENGTH_SHORT).show();
        }
    }

    public void retrieveArrayData(String requestUrl, final OnRequestArray<T> taskCompleted, final Class<T> klass) {
        retrieveArrayData(requestUrl, new JSONObject(hashMap), taskCompleted, klass);
    }

    public void retrieveArrayData(String requestUrl, JSONObject jsonPostObj, final OnRequestArray<T> taskCompleted, final Class<T> klass) {
        if (isNetworkAvailable()) {
            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST,
                    requestUrl, jsonPostObj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getBoolean("success") == true) {
                                    List<T> objList = new ArrayList<>();
                                    ObjectMapper mapper = new ObjectMapper();
                                    JSONArray jsonArray = response.getJSONArray("data");
                                    T obj;
                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        obj = EntityFactory.getInstance().getObject(klass);
                                        obj = mapper.readValue(String.valueOf(jsonArray.getJSONObject(i)), klass);
                                        objList.add(obj);
                                    }
                                    taskCompleted.onTaskCompleted(objList, false, null);
                                } else {
                                    taskCompleted.onTaskCompleted(null, false, null);
                                }
                            } catch (JSONException e) {
                                taskCompleted.onTaskCompleted(null, true, e.getMessage());
                            } catch (JsonParseException e) {
                                taskCompleted.onTaskCompleted(null, true, e.getMessage());
                            } catch (JsonMappingException e) {
                                taskCompleted.onTaskCompleted(null, true, e.getMessage());
                            } catch (IOException e) {
                                taskCompleted.onTaskCompleted(null, true, e.getMessage());
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    taskCompleted.onTaskCompleted(null, true, volleyError.getMessage());
                }
            }) {
                /* Passing some request headers*/
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    return headers;
                }
            };
            // Adding request to request queue
            ApplicationController.getInstance().addToRequestQueue(jsonObjRequest, Const.TAG_JSONOBJ_REQUEST);
        } else {
            Toast.makeText(ctx, "Network not available.", Toast.LENGTH_SHORT).show();
        }
    }

    public void sendObjectData(String requestUrl, final OnSendObject taskCompleted) {
        sendObjectData(requestUrl, new JSONObject(hashMap), taskCompleted);
    }

    public void sendObjectData(String requestUrl, JSONObject jsonPostObj, final OnSendObject taskCompleted) {
        if (isNetworkAvailable()) {
            JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST,
                    requestUrl, jsonPostObj,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getBoolean("success") == true) {
                                    taskCompleted.onTaskCompleted(false, null);
                                } else {
                                    taskCompleted.onTaskCompleted(true, null);
                                }
                            } catch (JSONException e) {
                                System.out.println(e.getMessage());
                                taskCompleted.onTaskCompleted(true, null);
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    taskCompleted.onTaskCompleted(true, volleyError.getMessage());
                }
            }) {
                /* Passing some request headers*/
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    HashMap<String, String> headers = new HashMap<String, String>();
                    headers.put("Content-Type", "application/json; charset=UTF-8");
                    return headers;
                }
            };
            // Adding request to request queue
            ApplicationController.getInstance().addToRequestQueue(jsonObjRequest, Const.TAG_JSONOBJ_REQUEST);
        } else {
            Toast.makeText(ctx, "Network not available.", Toast.LENGTH_SHORT).show();
        }
    }

    public HashMap<String, String> getHashMap() {
        return hashMap;
    }
}
