package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.PictureAdapter;
import com.example.administrator.financialauditingapppro.net.Beans.BeanPDUploadListItem;
import com.example.administrator.financialauditingapppro.net.StaticConstants;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;


/**
 * Created by Administrator on 6/23/2017.
 */

public class ImageActivity extends AppCompatActivity {

    protected PictureAdapter adapter= new PictureAdapter();
    protected int localArrayPosition;

    ImageLoader imageLoader = ImageLoader.getInstance();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        int b =0;
//        localArrayPosition = adapter.getLocalArrayPosition();
        String u = data.getExtras().getString(MediaStore.EXTRA_OUTPUT);
        Toast.makeText(this, "SMAPLES" + requestCode + ":", Toast.LENGTH_SHORT).show();
        Log.d("REQUESTCODE" , requestCode+"");
        Log.d("RESULTCODE" , resultCode+"");
        if (requestCode == StaticConstants.OPEN_IMAGE_FROM_FILE){
            Uri uri = data.getData();
            imageLoader.loadImage(uri.toString(),new SimpleImageLoadingListener(){
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    postImageResult(imageUri,view,loadedImage);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    super.onLoadingFailed(imageUri, view, failReason);
                    Toast.makeText(ImageActivity.this, "Image failed to load", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if (requestCode == StaticConstants.REQUEST_IMAGE_CAPTURE){
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            Uri uri = data.getData();
            int l =0;
            postImageResult(thumbnail);
        }

    }
    protected void postImageResult(Bitmap loadedImage){
        Intent intene

    }

    protected void postImageResult(String imageUri, View view, Bitmap loadedImage){
    }
}
