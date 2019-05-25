package com.example.progmobile_android.model.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.progmobile_android.model.entities.Event;
import com.example.progmobile_android.model.repository.Repository;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EventManager {
    private Context context;

    public EventManager(Context context){
        this.context=context;
    }

    public List<Event> getListEvents(){
        String url = "http://jsonplaceholder.typicode.com/todos/1";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

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

        return new ArrayList<Event>();
    }

    public List<Event> searchEventByName(String name) {
        return new ArrayList<Event>();
    }

    public Event getEvent(int eventId) {
        return new Event();
    }
}
