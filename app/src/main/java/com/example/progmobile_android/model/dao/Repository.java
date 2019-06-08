package com.example.progmobile_android.model.dao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class Repository {
    @SuppressLint("StaticFieldLeak")
    private static Repository instance;
    private RequestQueue requestQueue;
    @SuppressLint("StaticFieldLeak")
    private static Context ctx;

    private Repository(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    public static synchronized Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository(context);
        }
        return instance;
    }

    private RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    protected <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
