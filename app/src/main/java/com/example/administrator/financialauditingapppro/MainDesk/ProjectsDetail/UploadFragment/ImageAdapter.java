package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment;

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
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Administrator on 6/22/2017.
 */

public class ImageAdapter extends BaseAdapter {
    Context mContext;
    ImageActivity mActivity;
    Uri outPutfileUri;
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

        File file =createFile();
        String filePath = file.getAbsolutePath();
        mActivity.setFilePath(filePath);
        mCurrentPhotoPath = filePath;
        Uri uri = FileProvider.getUriForFile(mActivity,mActivity.getApplicationContext().getPackageName() + "FinancialAppPro.fileprovider",file);
        takePic.putExtra(MediaStore.EXTRA_OUTPUT, uri);

        mActivity.startActivityForResult(takePic,
                StaticConstants.REQUEST_IMAGE_CAPTURE);
//            ((Activity) mContext).startActivityForResult(takePic, StaticConstants.REQUEST_IMAGE_CAPTURE);
//        if (takePic.resolveActivity(mContext.getPackageManager())!= null){
//            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//            String imageFileName = "JPEG_" + timeStamp + "_" + ".jpg";
//            File photoFile = createFile();
//            String filePath;
//            outPutfileUri = Uri.fromFile(photoFile);
//            if (photoFile != null) {
////                takePic.putExtra(MediaStore.EXTRA_OUTPUT, outPutfileUri);
//
//            }
//        }
    }
    private File createFile(){
        File file = null;
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            String saveDir = "Reee";
            File dir = new File(saveDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            file = new File(saveDir, java.lang.System.currentTimeMillis()+".jpg");
            file.delete();
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                    return file;
                }
            }
        }
        return file;
    }

//    private File createImageFile() throws IOException {
        // Create an image file name


//        File storageDir = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
//        File image = File.createTempFile(
//                imageFileName,  /* prefix */
//                ".jpg",         /* suffix */
//                storageDir      /* directory */
//        );

        // Save a file: path for use with ACTION_VIEW intents
//        mCurrentPhotoPath = image.getAbsolutePath();
//        return image;
//    }

    private void choosePicture() {
        Intent choosePic = new Intent();
        updateLocalArrayPosition();
        choosePic.setAction(Intent.ACTION_PICK);
        mActivity.startActivityForResult(choosePic,
                StaticConstants.REQUEST_IMAGE_CAPTURE);
//        ((Activity) mContext).startActivityForResult(Intent.createChooser(choosePic,"Choose Picture"), StaticConstants.REQUEST_IMAGE_FILE);
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

    public int getLocalArrayPosition() {
        return localArrayPosition;
    }

    public Uri getOutPutfileUri(){
        return outPutfileUri;
    }

}
