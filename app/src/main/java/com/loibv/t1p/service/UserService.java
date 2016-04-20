package com.loibv.t1p.service;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loibv.t1p.ApplicationController;
import com.loibv.t1p.model.Account;
import com.loibv.t1p.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by vanloibui on 4/12/16.
 */
public class UserService implements IUserService {

    private final String TAG = "GET ALL RELATED USER";

    @Override
    public List<Account> getAllRelatedUser(String email) {

        final List<Account> accountList = new ArrayList<Account>();
        final HashMap<String, String> postObj = new HashMap<>();
        postObj.put("email", email);
        JsonObjectRequest jsonObjRequest = new JsonObjectRequest(Request.Method.POST,
                Const.URL_GET_ALL_RELATED_USER, new JSONObject(postObj),
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                        try {
                            if (response.getBoolean("success") == true) {
                                JSONArray jsonArray = response.getJSONArray("data");
                                ObjectMapper mapper = new ObjectMapper();
                                Account acc = null;
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    acc = mapper.readValue(String.valueOf(jsonArray.getJSONObject(i)), Account.class);
                                    accountList.add(acc);
                                }
                            } else {

                            }
                        } catch (JSONException e) {
                            System.out.println(e.getMessage());
                        } catch (JsonParseException e) {
                            System.out.println(e.getMessage());
                        } catch (JsonMappingException e) {
                            System.out.println(e.getMessage());
                        } catch (IOException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error:" + error.getMessage());
            }
        }) {
            /* Passing some request headers*/
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Adding request to request queue
        ApplicationController.getInstance().addToRequestQueue(jsonObjRequest, Const.TAG_JSONOBJ_REQUEST);
        return accountList;
    }
}
