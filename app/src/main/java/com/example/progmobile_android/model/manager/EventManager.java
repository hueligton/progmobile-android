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
import java.util.stream.Collectors;

public class EventManager {
    private Gson gson;
    private Context context;
    private String url = Constants.URL;

    private List<Event> listEvent;

    public EventManager(Context context) {
        this.context = context;
        this.gson = new Gson();
        listEvent = new ArrayList<>();
    }

    /**
     * Retorna via callback lista de todos os eventos disponíveis no webservice
     *
     * @param serverCallback callback para retorno
     */
    public void getListEvents(final ServerCallback serverCallback) {
        if (listEvent.size() > 0) {
            listEvent.clear();
        }

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

    /**
     * Retorna via callback lista de todos os eventos disponíveis no webservice filtrados pelo nome fornecido
     *
     * @param name           nome do evento
     * @param serverCallback callback para retorno
     */
    public void searchEventByName(String name, final ServerCallback serverCallback) {
        //Se vazio, preencher lista
        if (listEvent.size() == 0) {
            getListEvents(new ServerCallback() {
                @Override
                public void onSuccess(Object object) {
                    searchEventByName(name, serverCallback);
                }

                @Override
                public void onError(Object object) {

                }

            });
        }
        //Se lista já preenchida, filtra lista e retorna via callback para quem requisitou
        else {
            List<Event> eventList = listEvent.stream()
                    .filter(e ->
                            e.getName()
                                    .toLowerCase()
                                    .contains(name.toLowerCase()))
                    .collect(Collectors.<Event>toList());

            serverCallback.onSuccess(eventList);
        }
    }

    /**
     * Retorna evento selecionado pelo seu ID via callback
     * @param eventId id do evento
     * @param serverCallback callback de retorno
     */
    public void getEvent(int eventId, final ServerCallback serverCallback) {
        final String endPoint = url + "events/" + eventId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, endPoint, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Event event = gson.fromJson(response.toString(), Event.class);
                        serverCallback.onSuccess(event);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("getEvent", error.toString());
                    }
                });

        Repository.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}
