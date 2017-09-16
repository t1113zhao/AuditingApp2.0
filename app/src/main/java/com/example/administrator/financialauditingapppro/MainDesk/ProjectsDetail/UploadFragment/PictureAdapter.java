package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.example.administrator.financialauditingapppro.net.Beans.BeanPDUploadListItem;
import com.example.administrator.financialauditingapppro.net.StaticConstants;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by Administrator on 6/22/2017.
 */

public class PictureAdapter extends BaseAdapter {
    Context mContext;
    protected int localArrayPosition=-1;

    ArrayList<BeanPDUploadListItem> uploadListItems;

    BeanPDUploadListItem listItem;

    ArrayList<BeanPDUploadListItem.Faqentity> faqentities = new ArrayList<>();

    int uploadOrSample;

    HashMap<Integer,Integer> sampleMap = new HashMap<>();
    HashMap<Integer,Integer> uploadMap = new HashMap<>();
    String mCurrentPhotoPath;

    int listPosition;

    protected static LayoutInflater inflater = null;
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    protected void getPictures(int id){
        this.listPosition =id;
        final PictureUploadDialog dialog = new PictureUploadDialog(mContext);
        dialog.show();
        dialog.setPictureUploadDialogListener(new PictureUploadDialogListener() {
            @Override
            public void onDialogPress() {
                if (dialog.selected == 0){
                    takePicture();
                }
                else if (dialog.selected ==1){
                    choosePicture();
                }
                dialog.dismiss();
            }
        });
    }


    private void takePicture(){
        Intent takePic = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        updateLocalArrayPosition();
//        ((Activity) mContext).startActivityForResult(takePic, StaticConstants.REQUEST_IMAGE_CAPTURE);
        if (takePic.resolveActivity(mContext.getPackageManager())!= null){
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(mContext,
                        "com.example.android.fileprovider",
                        photoFile);
                takePic.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                ((Activity) mContext).startActivityForResult(takePic, StaticConstants.REQUEST_IMAGE_CAPTURE);
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void choosePicture() {
        Intent choosePic = new Intent();
        updateLocalArrayPosition();
        choosePic.setType("image/*");
        choosePic.setAction(Intent.ACTION_GET_CONTENT);

        choosePic.putExtra(MediaStore.EXTRA_OUTPUT, "LMAO");
        ((Activity) mContext).startActivityForResult(Intent.createChooser(choosePic,"Choose Picture"), StaticConstants.OPEN_IMAGE_FROM_FILE);
    }

    private void updateLocalArrayPosition(){
        if (uploadOrSample == StaticConstants.UPLOAD){
            localArrayPosition = uploadMap.get(listPosition);
        }
        else if (uploadOrSample == StaticConstants.SAMPLE){
            localArrayPosition = sampleMap.get(listPosition);
        }
        else {
            Toast.makeText(mContext, "FAILURE", Toast.LENGTH_SHORT).show();
        }
    }

}
