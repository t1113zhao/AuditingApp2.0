package com.example.administrator.financialauditingapppro.Login;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 6/7/2017.
 */

public class LoginRequest extends StringRequest{


    protected static final String PROTOCOL_CHARSET = "utf-8";

    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    private String params;

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public LoginRequest(LoginPostBody postBody, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
        params = new Gson().toJson(postBody);

        Log.d("HTTP REQUEST URL", url);
        Log.d("HTTP REQUEST INFO", params);
    }

    public LoginRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(url, listener, errorListener);
    }

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
