package com.example.administrator.financialauditingapppro.net;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.utils.L;
//
///**
// * Created by xiaowei on 2015/8/11.
// */
//
//import com.android.core.util.FilesCommon;
//import com.android.core.util.ImageUtils;
//import com.android.volley.AuthFailureError;
//import com.android.volley.NetworkResponse;
//import com.android.volley.Request;
//import com.android.volley.Response;
//import com.android.volley.VolleyLog;
//import com.android.volley.toolbox.HttpHeaderParser;
//import com.google.gson.Gson;
//import com.lovehome.android.aijiazhusou.common.staticfield.StaticStringField;
//import com.nostra13.universalimageloader.utils.L;
//
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import org.apache.http.entity.mime.content.FileBody;
//import org.apache.http.entity.mime.content.StringBody;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//import java.net.URLEncoder;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
public class MultipartRequest extends Request<String> {
//
    private MultipartEntity entity = new MultipartEntity();

    private Response.Listener<String> mListener;

    private List<File> mFileParts;
    private String mFilePartName;
    private Map<String, String> mParams;
    protected static final String PROTOCOL_CHARSET = "utf-8";

    public MultipartRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    public MultipartRequest(String url, Response.ErrorListener errorListener, Response.Listener<String> listener, String filePartName, List<File> files, Map<String, String> params) {
        super(Method.POST, url, errorListener);
        mFilePartName = filePartName;
        mListener = listener;
        mFileParts = files;
        mParams = params;
    }

    private void buildMultipartEntity() {
        if (mFileParts != null && mFileParts.size() > 0) {
            for (File file : mFileParts) {
                FileBody body = new FileBody(file);
//                        compressFile(file.getPath()));
                entity.addPart(mFilePartName, body);
                L.d("file路径：" + file.getPath() + "，长度：" + (body.getFile().length()/1024));
            }
            long l = entity.getContentLength();
            L.d(mFileParts.size() + "个，长度：" + l);
        }
        L.d("其他参数：" + new Gson().toJson(mParams));
        try {
            if (mParams != null && mParams.size() > 0) {
                for (Map.Entry<String, String> entry : mParams.entrySet()) {
                    entity.addPart(
                            entry.getKey(),

                            new StringBody(entry.getValue(), Charset
                                    .forName("UTF-8")));
                }
            }
        } catch (UnsupportedEncodingException e) {
            VolleyLog.e("UnsupportedEncodingException");
        }
    }
    @Override
    public String getBodyContentType() {
        return entity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        buildMultipartEntity();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            entity.writeTo(bos);
        } catch (IOException e) {
            e.printStackTrace();
            VolleyLog.e("IOException writing to ByteArrayOutputStream");
        }
        return bos.toByteArray();
    }
    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        L.d("parseNetworkResponse");
        if (VolleyLog.DEBUG) {
            if (response.headers != null) {
                for (Map.Entry<String, String> entry : response.headers
                        .entrySet()) {
                    VolleyLog.d(entry.getKey() + "=" + entry.getValue());
                }
            }
        }

        String parsed;
        try {
            parsed = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            parsed = new String(response.data);
        }
        return Response.success(parsed,
                HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        VolleyLog.d("getHeaders");
        Map<String, String> headers = super.getHeaders();

        if (headers == null || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }
        headers.put("Authorization", "bb5a74bad72b4507a83b45df17758be8");
        return headers;
    }
    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }
}
//
//    private final Response.Listener<String> mListener;
//
//    private List<File> mFileParts;
//    private String mFilePartName;
//    private Map<String, String> mParams;
//
//    protected static final String PROTOCOL_CHARSET = "utf-8";
//
//
//    public static MultipartRequest  requestPostFies(String[] urlParams, Response.ErrorListener errorListener,
//                                                    Response.Listener<String> listener, String filePartName,
//                                                    List<File> files, Map<String, String> params){
//    	StringBuffer url = new StringBuffer(ProjectURLS.BASEURL);
//		for (String urlParam : urlParams) {
//			url.append("/");
//			try {
//				url.append(URLEncoder.encode(urlParam, PROTOCOL_CHARSET));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}
//		}
//		String finalUrl = url.toString();
//    	return new MultipartRequest(finalUrl, errorListener, listener, filePartName, files, params);
//
//    }
//
//    /**
//     * 单个文件
//     *
//     * @param url
//     * @param errorListener
//     * @param listener
//     * @param filePartName
//     * @param file
//     * @param params
//     */
//    public MultipartRequest(String url, Response.ErrorListener errorListener,
//                            Response.Listener<String> listener, String filePartName, File file,
//                            Map<String, String> params) {
//        super(Method.POST, url, errorListener);
//
//        mFileParts = new ArrayList<File>();
//        if (file != null) {
//            mFileParts.add(file);
//        }
//        mFilePartName = filePartName;
//        mListener = listener;
//        mParams = params;
//    }
//
//    /**
//     * 多个文件，对应一个key
//     *
//     * @param url
//     * @param errorListener
//     * @param listener
//     * @param filePartName
//     * @param files
//     * @param params
//     */
//    public MultipartRequest(String url, Response.ErrorListener errorListener, Response.Listener<String> listener, String filePartName, List<File> files, Map<String, String> params) {
//        super(Method.POST, url, errorListener);
//        mFilePartName = filePartName;
//        mListener = listener;
//        mFileParts = files;
//        mParams = params;
//    }
//
//
//    private File compressFile(String path) {
//        return ImageUtils.compress(path, 120, FilesCommon.getImageDir(),
//                640, 960);
//    }
//
//    private void buildMultipartEntity() {
//        if (mFileParts != null && mFileParts.size() > 0) {
//            for (File file : mFileParts) {
//                FileBody body = new FileBody(compressFile(file.getPath()));
//                entity.addPart(mFilePartName, body);
//                L.d("file路径：" + file.getPath() + "，长度：" + (body.getFile().length()/1024));
//            }
//            long l = entity.getContentLength();
//            L.d(mFileParts.size() + "个，长度：" + l);
//        }
//        L.d("其他参数：" + new Gson().toJson(mParams));
//        try {
//            if (mParams != null && mParams.size() > 0) {
//                for (Map.Entry<String, String> entry : mParams.entrySet()) {
//                    entity.addPart(
//                            entry.getKey(),
//                            new StringBody(entry.getValue(), Charset
//                                    .forName("UTF-8")));
//                }
//            }
//        } catch (UnsupportedEncodingException e) {
//            VolleyLog.e("UnsupportedEncodingException");
//        }
//    }
//
//    @Override
//    public String getBodyContentType() {
//        return entity.getContentType().getValue();
//    }
//
//    @Override
//    public byte[] getBody() throws AuthFailureError {
//        buildMultipartEntity();
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        try {
//            entity.writeTo(bos);
//        } catch (IOException e) {
//            VolleyLog.e("IOException writing to ByteArrayOutputStream");
//        }
//        return bos.toByteArray();
//    }
//
//    @Override
//    protected Response<String> parseNetworkResponse(NetworkResponse response) {
//        L.d("parseNetworkResponse");
//        if (VolleyLog.DEBUG) {
//            if (response.headers != null) {
//                for (Map.Entry<String, String> entry : response.headers
//                        .entrySet()) {
//                    VolleyLog.d(entry.getKey() + "=" + entry.getValue());
//                }
//            }
//        }
//
//        String parsed;
//        try {
//            parsed = new String(response.data,
//                    HttpHeaderParser.parseCharset(response.headers));
//        } catch (UnsupportedEncodingException e) {
//            parsed = new String(response.data);
//        }
//        return Response.success(parsed,
//                HttpHeaderParser.parseCacheHeaders(response));
//    }
//
//
//    /*
//     * (non-Javadoc)
//     *
//     * @see com.android.volley.Request#getHeaders()
//     */
//    @Override
//    public Map<String, String> getHeaders() throws AuthFailureError {
//        VolleyLog.d("getHeaders");
//        Map<String, String> headers = super.getHeaders();
//
//        if (headers == null || headers.equals(Collections.emptyMap())) {
//            headers = new HashMap<String, String>();
//        }
//        headers.put("Authorization", "bb5a74bad72b4507a83b45df17758be8");
//        return headers;
//    }
//
//    @Override
//    protected void deliverResponse(String response) {
//        mListener.onResponse(response);
//    }
//}