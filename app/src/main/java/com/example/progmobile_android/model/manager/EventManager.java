package com.example.progmobile_android.model.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.repository.Repository;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private Gson gson;
    private Context context;
    private String url = "http://jsonplaceholder.typicode.com/";

    private List<Event> listEvent;

    public EventManager(Context context) {
        this.context = context;
        this.gson = new Gson();
        listEvent = new ArrayList<>();
    }

    public void getListEvents(final ServerCallback serverCallback) {
        final String endPoint = url + "events";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, endPoint, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject event = (JSONObject) response.get(i);
                                listEvent.add(gson.fromJson(event.toString(), Event.class));
                            }
                            serverCallback.onSuccess(listEvent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("getListEvents", error.toString());
                    }
                });

        Repository.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void searchEventByName(String name, final ServerCallback serverCallback) {
    }

    public void getEvent(int eventId, final ServerCallback serverCallback) {
        String endPoint = url;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endPoint, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("teste", response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

        Repository.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
