package com.example.progmobile_android.model.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
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

        final String endPoint = url + "/events";

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                endPoint,
                response -> {
                    try {
                        JSONArray jsonFromResponse = new JSONArray(response);
                        for (int i = 0; i < jsonFromResponse.length(); i++) {
                            JSONObject event = jsonFromResponse.getJSONObject(i);
                            listEvent.add(gson.fromJson(event.toString(), Event.class));
                        }
                        serverCallback.onSuccess(listEvent);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        serverCallback.onError(null);
                    }
                },
                error -> {
                    Log.d("getListEvents", error.toString());
                    serverCallback.onError(null);
                });

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    /**
     * Retorna via callback lista de todos os eventos disponíveis no webservice filtrados pelo nome fornecido
     *
     * @param name           nome do evento
     */
    public List<Event> searchEventByName(String name) {
        //Se vazio, preencher lista
        if (listEvent.size() == 0) {
            getListEvents(new ServerCallback() {
                @Override
                public void onSuccess(Object object) {
                    searchEventByName(name);
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

            return eventList;
        }
        return null;
    }

    /**
     * Retorna evento selecionado pelo seu ID via callback
     * @param eventId        id do evento
     * @param serverCallback callback de retorno
     */
    public void getEvent(int eventId, final ServerCallback serverCallback) {
        final String endPoint = url + "/events/" + eventId;

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                endPoint,
                response -> {
                    Log.d("getEvent", response);
                    Event event = gson.fromJson(response, Event.class);
                    serverCallback.onSuccess(event);
                },
                error -> {
                    Log.d("getEvent", error.toString());
                    serverCallback.onError(null);
                });

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }
}
