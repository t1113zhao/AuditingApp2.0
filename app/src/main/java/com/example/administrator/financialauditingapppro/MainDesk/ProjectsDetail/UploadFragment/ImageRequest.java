package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadPostBody;
import com.google.gson.Gson;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 6/27/2017.
 */

public class ImageRequest extends StringRequest {


    protected static final String PROTOCOL_CHARSET = "utf-8";

//    private MultipartEntity entity = new MultipartEntity();
    private final Response.Listener<String> mListener;

    private File mFile;
    private String mFileName;
    private String mParams;
    /**
     * Content type for request.
     */
    private static final String PROTOCOL_CONTENT_TYPE =
            String.format("application/json; charset=%s", PROTOCOL_CHARSET);

    public ImageRequest(String filePartName, String url, Response.ErrorListener errorListener,Response.Listener<String> listener, File file) {
        super(Method.POST, url,listener, errorListener);
        mFileName = filePartName;
        mListener = listener;
        mFile = file;
//        mParams = new Gson().toJson(postBody);;
    }


    public ImageRequest(UploadPostBody uploadPostBody, String url, Response.ErrorListener errorListener, Response.Listener<String> listener) {
        super(Method.POST, url,listener, errorListener);
//        mFileName = filePartName;
        mListener = listener;
        mParams = new Gson().toJson(uploadPostBody);;
    }



    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> maps = new HashMap<>();
        maps.put("Authorization", "bb5a74bad72b4507a83b45df17758be8");
        return maps;
    }

    public byte[] getBody() throws AuthFailureError {
        try {
            return mParams.getBytes(getParamsEncoding());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
        }
        return null;
    }
//
//    @Override
//    protected Response<String> parseNetworkResponse(NetworkResponse response) {
//        return null;
//    }
//
//    @Override
//    protected void deliverResponse(String response) {
//
//    }
}
