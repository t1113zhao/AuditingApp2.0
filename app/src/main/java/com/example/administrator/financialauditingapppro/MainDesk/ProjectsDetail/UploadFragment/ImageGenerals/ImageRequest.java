package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.ImageGenerals;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.financialauditingapppro.net.ProjectURLS;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 7/6/2017.
 */

public class ImageRequest extends StringRequest {
    public static final String PROTOCOL_CHARSET = "utf-8";
    private HashMap<String, String> postParamsHashMap = new HashMap<String, String>();

    public Map<String, String> headers = new HashMap<String, String>();
    private String postParamsString;
    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    public ImageRequest(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, listener, errorListener);
    }

    public ImageRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(method,url,listener,errorListener);
    }

    public static ImageRequest imageRequestPost(String jsonString, HashMap<String, String> params, Response.Listener<String> listener,
                        Response.ErrorListener errorListener, String... urlParams){

        StringBuffer urlBuffer =new StringBuffer (ProjectURLS.BASEURL);
        urlBuffer.deleteCharAt(urlBuffer.lastIndexOf("/"));
        for (String param: urlParams){

            urlBuffer.append("/");
            try {
                urlBuffer.append(URLEncoder.encode(param, PROTOCOL_CHARSET));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String uploadURL = urlBuffer.toString();
        ImageRequest request = new ImageRequest(Request.Method.POST,
                uploadURL, listener, errorListener);
        if (!jsonString.isEmpty()){
            request.addPostRequestParams(jsonString);
        }
        if(params != null){
            request.addPostRequestParams(params);
            return request;
        }
        return request;
    }

    protected void addPostRequestParams(String jsonString) {
        this.postParamsString = jsonString;
    }
    protected void addPostRequestParams(HashMap<String, String> params) {
        if(params == null){
            return ;
        }
        postParamsHashMap.putAll(params);
    }


//    public Default_Request(String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
//        super(url, listener, errorListener);
//    }

    public Map<String, String> getHeaders() throws AuthFailureError {
        if(headers.isEmpty()){
            //默认加Encrypted_Information_2
            headers.put("Authorization", "1b75c03cacd5418f9759f1e431c91a28");
        }
        return headers;
    }

    public byte[] getBody() throws AuthFailureError {
        try {
            return postParamsString==null ? super.getBody() : postParamsString.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return super.getBody();
        }
    }
    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return postParamsHashMap.isEmpty() ? super.getParams() : postParamsHashMap;
    }
}
