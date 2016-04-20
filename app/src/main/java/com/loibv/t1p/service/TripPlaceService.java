package com.loibv.t1p.service;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loibv.t1p.model.TripPlace;
import com.loibv.t1p.utils.Const;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vanloibui on 4/12/16.
 */
public class TripPlaceService {

    public List<TripPlace> getAllTripPlace(int tripId) {

        final List<TripPlace> tripPlaceList = new ArrayList<>();

        HashMap<String, String> postObj = new HashMap<>();
        postObj.put("tripId", String.valueOf(tripId));

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, Const.URL_GET_ALL_TRIP_PLACE,
                new JSONObject(postObj), new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.getBoolean("success") == true) {
                        ObjectMapper mapper = new ObjectMapper();
                        JSONArray jsonArray = response.getJSONArray("data");
                        TripPlace tripPlace;
                        for (int i = 0; i < jsonArray.length(); i++) {
                            tripPlace = new TripPlace();
                            tripPlace = mapper.readValue(String.valueOf(jsonArray.getJSONObject(i)), TripPlace.class);
                            tripPlaceList.add(tripPlace);
                        }
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
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        return tripPlaceList;
    }
}
