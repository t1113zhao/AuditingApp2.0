package com.example.administrator.financialauditingapppro.MainDesk;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 6/9/2017.
 */

public class Bench_Project_Request extends StringRequest{

    protected static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private String params = "A";

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public Bench_Project_Request(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, listener, errorListener);

//        Log.d("HTTP REQUEST URL", url);
    }

//    public Bench_Project_Request(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
//        super(url, listener, errorListener);
//    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> maps = new HashMap<>();
        maps.put("Authorization", "bb5a74bad72b4507a83b45df17758be8");
        return maps;
    }

    public byte[] getBody() throws AuthFailureError {
        try {
            return params.getBytes(getParamsEncoding());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }
}
