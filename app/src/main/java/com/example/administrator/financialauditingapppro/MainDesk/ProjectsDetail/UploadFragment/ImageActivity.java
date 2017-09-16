package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.financialauditingapppro.net.Beans.BeanPDUploadListItem;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfoFromSharedPref;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.example.administrator.financialauditingapppro.net.StaticConstants;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;


/**
 * Created by Administrator on 6/23/2017.
 */

public class ImageActivity extends AppCompatActivity implements BeanUserInfoFromSharedPref{

    BeanUserInfo beanUserInfo;
    Uri picURI;
    String filePath;
    protected BeanPDUploadListItem pdBean;

    protected ImageAdapter adapter= new ImageAdapter();
    protected int localArrayPosition;

    ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent information) {
        super.onActivityResult(requestCode, resultCode, information);
        if (resultCode != RESULT_OK || information == null ){
            return;
        }
        if (requestCode == StaticConstants.REQUEST_IMAGE_FILE){
//            Uri selectedImage = information.getData();
//
//            if (selectedImage!= null){
//                String selectedPath = getImagePath(this, selectedImage);
//                uploadPicture(selectedPath);
//            }

        }
        else if (requestCode == StaticConstants.REQUEST_IMAGE_CAPTURE){
        }
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

    protected void uploadPicture(String selectedPath){

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
