package com.example.progmobile_android.model.manager;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.example.progmobile_android.model.entities.User;
import com.example.progmobile_android.model.entities.UserToken;
import com.example.progmobile_android.model.repository.Repository;
import com.example.progmobile_android.model.repository.ServerCallback;
import com.google.gson.Gson;

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

    public void login(String login, String password, final ServerCallback serverCallback) {

        final String endPoint = url + "authentication";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                endPoint,
                response -> {
                    Log.d("login", response);
                    try {
                        JSONObject jsonFromResponse = new JSONObject(response);
                        JSONObject jsonUser = jsonFromResponse.getJSONObject("user");
                        String token = jsonFromResponse.getString("token");

                        User user = gson.fromJson(jsonUser.toString(), User.class);
                        this.userToken = new UserToken(user, token);

                        serverCallback.onSuccess(userToken);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        serverCallback.onError(null);
                    }

                },
                error -> {
                    Log.d("login", error.toString());
                    serverCallback.onError(null);
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

    public void logout(final ServerCallback serverCallback) {

        final String endPoint = url + "authentication";

        StringRequest stringRequest = new StringRequest(Request.Method.DELETE,
                endPoint,
                response -> {
                    userToken = null;
                    serverCallback.onSuccess(true);
                },
                error -> {
                    Log.d("logout", error.toString());
                    serverCallback.onError(false);
                }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("userId", String.valueOf(userToken.getUser().getId()));
                headers.put("token", userToken.getToken());
                return headers;
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void createUser(String login, String name, String password, String email, final ServerCallback serverCallback) {
        final String endPoint = url + "user";

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                endPoint,
                response -> {
                    Log.d("createUser", response);
                    User user = gson.fromJson(response, User.class);
                    serverCallback.onSuccess(user);
                },
                error -> {
                    Log.d("createUser", error.toString());
                    serverCallback.onError(null);
                }) {

            @Override
            public byte[] getBody() {
                HashMap<String, String> params = new HashMap<>();
                params.put("login", login);
                params.put("password", password);
                params.put("name", name);
                params.put("email", email);
                return new JSONObject(params).toString().getBytes();
            }

            @Override
            public String getBodyContentType() {
                return "application/json";
            }
        };

        Repository.getInstance(context).addToRequestQueue(stringRequest);
    }

    public void getUser(ServerCallback serverCallback) {
        if (userToken != null)
            serverCallback.onSuccess(userToken);
        else
            serverCallback.onError(null);
    }
}
