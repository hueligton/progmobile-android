package com.example.progmobile_android.model.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.progmobile_android.model.entities.Pair;
import com.example.progmobile_android.model.entities.Purchase;
import com.example.progmobile_android.model.entities.Ticket;
import com.example.progmobile_android.model.repository.Repository;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PurchaseManager {
    private Gson gson;
    private Context context;
    private String url = Constants.URL;

    public PurchaseManager(Context context){
        this.context = context;
        this.gson = new Gson();
    }

    public void getListPurchases(String userId, String token, final ServerCallback serverCallback) {
        final String endPoint = url + "purchases";

        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, endPoint, null, new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            List<Purchase> listPurchase = new ArrayList<>();
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject object = (JSONObject) response.get(i);
                                listPurchase.add(gson.fromJson(object.toString(), Purchase.class));
                            }
                            serverCallback.onSuccess(listPurchase);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("getListPurchases", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("userId", userId);
                headers.put("token", token);
                return headers;
            }
        };

        Repository.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void setPurchase(String userId, String token, int eventId, List<Pair> list, final ServerCallback serverCallback) {
        final String endPoint = url + "purchases/";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, endPoint, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        final Purchase purchase = gson.fromJson(response.toString(), Purchase.class);
                        serverCallback.onSuccess(purchase);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("getPurchase", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("userId", userId);
                headers.put("token", token);
                return headers;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("eventId", String.valueOf(eventId));
                params.put("Pair", gson.toJson(list));
                return params;
            }
        };

        Repository.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getPurchase(String userId, String token, int purchaseId, final ServerCallback serverCallback) {
        final String endPoint = url + "purchases/" + purchaseId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endPoint, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        final Purchase purchase = gson.fromJson(response.toString(), Purchase.class);
                        serverCallback.onSuccess(purchase);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("getPurchase", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("userId", userId);
                headers.put("token", token);
                return headers;
            }
        };

        Repository.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getTicket(String userId, String token, int ticketId, final ServerCallback serverCallback) {
        final String endPoint = url + "ticket/" + ticketId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endPoint, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        final Ticket ticket = gson.fromJson(response.toString(), Ticket.class);
                        serverCallback.onSuccess(ticket);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("getPurchase", error.toString());
                    }
                }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("userId", userId);
                headers.put("token", token);
                return headers;
            }
        };

        Repository.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
