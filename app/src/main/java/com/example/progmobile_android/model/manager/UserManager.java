package com.example.progmobile_android.model.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.progmobile_android.model.entities.User;
import com.example.progmobile_android.model.entities.UserToken;
import com.example.progmobile_android.model.repository.Repository;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Gson gson;
    private Context context;
    private String url = Constants.URL;

    private UserToken userToken;

    public UserManager(Context context) {
        this.context = context;
        this.gson = new Gson();
    }

    public void login(String login, String password, final ServerCallback callback) {

        final String endPoint = url + "authentication";

        StringRequest stringRequest = new StringRequest(Request.Method.POST, endPoint, response -> {
            Log.d("login", response);
            try {
                JSONObject jsonFromResponse = new JSONObject(response);
                JSONObject jsonUser = jsonFromResponse.getJSONObject("user");
                String token = jsonFromResponse.getString("token");

                User user = gson.fromJson(jsonUser.toString(), User.class);
                userToken = new UserToken(user, token);

                callback.onSuccess(userToken);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Log.d("login", error.toString());
            callback.onError(null);
        }) {

            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("login", login);
                params.put("password", password);
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);

    }

    public void logout(String userId, String token, final ServerCallback serverCallback) {

        //Usar o mesmo endpoint para Logout
        //Alterar somente o método para DELETE
        final String endPoint = url + "authentication";

        StringRequest stringRequest = new StringRequest
                (Request.Method.DELETE, endPoint, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        serverCallback.onSuccess(true);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("login", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("userId", userId);
                params.put("token", token);
                return params;
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void createUser(String login, String name, String password, String email, final ServerCallback serverCallback) {
        final String endPoint = url + "user";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, endPoint, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        User user = gson.fromJson(response.toString(), User.class);
                        serverCallback.onSuccess(user);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("login", error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("login", login);
                params.put("password", password);
                params.put("name", name);
                params.put("email", email);
                return params;
            }
        };

        Repository.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }

    public void getUser(ServerCallback serverCallback) {
        if (userToken != null)
            serverCallback.onSuccess(userToken);
        else
            serverCallback.onError(null);
    }
}
