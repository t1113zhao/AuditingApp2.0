package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.ImageGenerals;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBase;
import com.example.administrator.financialauditingapppro.net.Beans.BeanPDUploadListItem;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfoFromSharedPref;
import com.example.administrator.financialauditingapppro.net.Beans.NetUtils;
import com.example.administrator.financialauditingapppro.net.GetImagePath;
import com.example.administrator.financialauditingapppro.net.MultipartRequest;
import com.example.administrator.financialauditingapppro.net.ProjectURLS;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.example.administrator.financialauditingapppro.net.StaticConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.L;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

//import static com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.ImageGenerals.ImageRequest.PROTOCOL_CHARSET;
import static com.example.administrator.financialauditingapppro.net.StaticConstants.TIMEOUTMS;


/**
 * Created by Administrator on 6/23/2017.
 */

public class ImageActivity extends AppCompatActivity implements BeanUserInfoFromSharedPref{

    protected BeanUserInfo beanUserInfo;
    protected Uri picURI;
    protected String filePath;
    protected BeanPDUploadListItem pdBean;
    protected final String CANCEL = "Cancel";

    protected ImageAdapter adapter= new ImageAdapter();
    protected int localArrayPosition;

    protected ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent information) {
        super.onActivityResult(requestCode, resultCode, information);

        String picturePath =CANCEL;
        if (resultCode != RESULT_OK){
            return;
        }
        if (requestCode == StaticConstants.REQUEST_IMAGE_FILE){
            Uri selectedImage = information.getData();
            if(selectedImage != null){
                picturePath = GetImagePath.getPath(this, selectedImage);

//					Cursor cursor = getContentResolver().query(selectedImage,
//							filePathColumn, null, null, null);
//					if(cursor != null){
//						cursor.moveToFirst();
//						int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//						String picturePath = cursor.getString(columnIndex);
//						if(mCallPictureGalley != null && !TextUtils.isEmpty(picturePath)){
//							mCallPictureGalley.callPictureGalley(picturePath);
//						}
//						L.i("picturePath == "+picturePath);
//						cursor.close();
//					}else{
//						ToastUtil.show("上传失败");
//						L.i("游标为空");
//						return;
//					}
            }else{
                Toast.makeText(this, "IMAGE ERROR", Toast.LENGTH_SHORT).show();
            }
        }
        else if (requestCode == StaticConstants.REQUEST_IMAGE_CAPTURE){
            if(!TextUtils.isEmpty(filePath)){
                picturePath = filePath;
            }
        }
        else{
            Toast.makeText(this, "This should never happen", Toast.LENGTH_SHORT).show();
        }
        preUploadPicture(picturePath);
//        String[] proj = { MediaStore.Video.Media.DATA };
//        String result = null;
//
//        CursorLoader cursorLoader = new CursorLoader(
//                this,
//                picURI, proj, null, null, null);
//        Cursor cursor = cursorLoader.loadInBackground();
//
//        if(cursor != null){
//            int column_index =
//                    cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
//            cursor.moveToFirst();
//            result = cursor.getString(column_index);
//            Log.d("file path", result);
//        }
//        imageLoader.loadImage(picURI.toString(),new SimpleImageLoadingListener(){
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                super.onLoadingComplete(imageUri, view, loadedImage);
//                ursomething = imageUri;
//
//
//                    postImageResult(loadedImage);
//            }
//
//            @Override
//            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
//                super.onLoadingFailed(imageUri, view, failReason);
//                Toast.makeText(ImageActivity.this, "Image failed to load", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    protected String getImagePath(final Context context, final Uri uri){
        return null;
    }
    protected void getPDListItem() {
    }

    protected void preUploadPicture(String selectedPath){

        if (selectedPath.equals(CANCEL)){
            Toast.makeText(this, "Error occured", Toast.LENGTH_SHORT).show();
        }

        ArrayList<File> files = new ArrayList<File>();
        File file = new File(selectedPath);
        files.add(file);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("type", "progress");


        final String[] urlParams = new String[]{
                ProjectURLS.APP + ProjectURLS.ASSISTANT_EXTENSION,
                ProjectURLS.VERSION_NUMBER,
                ProjectURLS.UPLOADPICTURE_EXTENSION
        };

        BeanUserInfo beanUserInfo = getBeanUserInfo();
        pdBean = new Gson().fromJson(SharedPreferencesUtil.getJsonString(this, SharedPreferencesUtil.IMAGEINFORMATION),BeanPDUploadListItem.class);

        final String[] paramsTwos = new String[]{
                beanUserInfo.customerid,
                ProjectURLS.VERSION_NUMBER,
                pdBean.id+"",
                pdBean.stageid+"",
                pdBean.stagetitle
        };

        ProjectURLS u = new ProjectURLS();
//        http://test9.525j.com.cn/app/com.uploadfiles
//        http://test9.525j.com.cn/app%2Fassistantapi/v1.0/assistant.uploadpicture
        String initialURL = u.BASEURL + u.APP + u.PREUPLOAD_EXTENSION;
        String uploadURL =  u.BASEURL +  u.APP_API_EXTENSION + u.VERSION_NUMBER + u.UPLOADPICTURE_EXTENSION;

//        final Response.ErrorListener errorListener = new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        };

//        final Response.Listener<String> stringListener = new Response.Listener<String>() {

//            @Override
//            public void onResponse(String response) {
//                postImageResponse(response);
//            }
//        };


        MultipartRequest multipartRequest = new MultipartRequest(
                initialURL,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                },
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        L.i("response == " + response);
                        BeanBase<ArrayList<PicInfo>> baseBean = new BeanBase<ArrayList<PicInfo>>();
                        Type tp = new TypeToken<BeanBase<ArrayList<PicInfo>>>() {
                        }.getType();
                        baseBean = NetUtils.getResponseBean(tp, response);
                        if(baseBean != null){
                            if ("200".equals(baseBean.code)) {
                                upLoadPicture(baseBean.data,urlParams,paramsTwos);
                            } else {
                                Toast.makeText(ImageActivity.this, baseBean.msg+"", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ImageActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                "imageupload",
                files,
                map
        );

        multipartRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        TIMEOUTMS,
                       1,1));
        RequestQueue queue = Volley.newRequestQueue(ImageActivity.this);
        queue.add(multipartRequest);
    }

    private void upLoadPicture(ArrayList<PicInfo> picInfos, String[] urlParams, String[] paramsTwos) {
        if (picInfos != null && !picInfos.isEmpty()){
            ArrayList<String> pictureNames = new ArrayList<String>();
            for (PicInfo picInfo : picInfos) {
                pictureNames.add(picInfo.picname);
            }
            BeanUpLoadPicture bPicture = new BeanUpLoadPicture();
            bPicture.customerid = paramsTwos[0];
            bPicture.version = paramsTwos[1];
//             bPicture.progressid = mItemData.progressid+"";
            bPicture.progressid = paramsTwos[2];
            bPicture.stageid = paramsTwos[3];
            bPicture.stagename = paramsTwos[4];
            bPicture.pictures = pictureNames;

            Gson gson = new Gson();
            String jsonString = gson.toJson(bPicture);
            Response.Listener<String> stringListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String a = response;
                    postImageResponse(response);
                }
            };
            Response.ErrorListener errorListener = new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            };
//            ProjectURLS u = new ProjectURLS();
//            StringBuffer urlBuffer =new StringBuffer (u.BASEURL);
//            urlBuffer.deleteCharAt(urlBuffer.lastIndexOf("/"));
//            for (String param: urlParams){
//
//                urlBuffer.append("/");
//                try {
//                    urlBuffer.append(URLEncoder.encode(param, Default_Request.PROTOCOL_CHARSET));
//                } catch (UnsupportedEncodingException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            String uploadURL = urlBuffer.toString();
//            Default_Request request = new Default_Request(Request.Method.POST, uploadURL, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    int u =0;
//                    postImageResponse(response);
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//                }
//            });

//            request.setRetryPolicy(
//                    new DefaultRetryPolicy(
//                            TIMEOUTMS,
//                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            ImageRequest request = ImageRequest.imageRequestPost(jsonString,null,stringListener,errorListener,urlParams);
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);

        }
    }



    protected void postImageResponse(String response) {
        int i =0;
    }

    static class BeanUpLoadPicture {
        public String version;
        public String customerid;
        public String progressid;
        public String stageid;
        public String stagename;
        public ArrayList<String> pictures;

        public String checkid;
        public String title;
    }

    static class PicInfo {
            public String picname;
            public String picurl;
        }
    @Override
    public BeanUserInfo getBeanUserInfo() {
        BeanUserInfo bean=new Gson().fromJson(SharedPreferencesUtil.getJsonString(this, SharedPreferencesUtil.USER_INFO),BeanUserInfo.class);
        return bean;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
