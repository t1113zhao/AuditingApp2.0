package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.ImageGenerals.ImageAdapter;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanPDUploadListItem;
import com.example.administrator.financialauditingapppro.net.StaticConstants;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/19/2017.
 */

class UploadAdapter extends ImageAdapter {

//    PictureDisplayGallery displayGallery;

    public UploadAdapter(Context context, ArrayList<BeanPDUploadListItem> uploadListItems){
        this.uploadListItems = uploadListItems;
        this.mContext = context;
//        displayGallery = new PictureDisplayGallery(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        uploadOrSample = StaticConstants.UPLOAD;
    }

    @Override
    public int getCount() {
        return uploadListItems.size();
    }

    @Override
    public Object getItem(int position) {
        return uploadListItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return uploadListItems.get(position).id;
    }



    public class Holder {
        boolean editOrDelete = true;
        String TEXT_TRUE ="Add Pictures";
        String TEXT_FALSE ="Delete Pictures";

        RecyclerView displayGallery;

        TextView uploadFragmentLI_stageTitleTV;
        TextView uploadFragmentLI_editDeleteTV;
        ImageView uploadFragmentLI_viewIV;
        ImageView uploadFragmentLI_takePictureIV;

        LinearLayout upload_list_item;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View pDUFListItem;
        int i =position;
        listItem = uploadListItems.get(position);
        final GalleryAdapter galleryAdapter =new GalleryAdapter(mActivity,listItem.progresspiclist,holder.editOrDelete);



        pDUFListItem = inflater.inflate(R.layout.layout_upload_fragment_list_item,null);

        holder.uploadFragmentLI_stageTitleTV =  (TextView) pDUFListItem.findViewById(R.id.uploadFragmentLI_stageTitleTV);
        holder.uploadFragmentLI_editDeleteTV =  (TextView) pDUFListItem.findViewById(R.id.uploadFragmentLI_editDeleteTV);;
        holder.uploadFragmentLI_viewIV =  (ImageView) pDUFListItem.findViewById(R.id.uploadFragmentLI_viewIV);
        holder.uploadFragmentLI_takePictureIV =  (ImageView) pDUFListItem.findViewById(R.id.uploadFragmentLI_takePictureIV);
        holder.displayGallery = (RecyclerView) pDUFListItem.findViewById(R.id.displayGallery);

        holder.upload_list_item =  (LinearLayout) pDUFListItem.findViewById(R.id.upload_list_item);

        int galleryWidth = listItem.progresspiclist.size() * 60 *2;
        holder.displayGallery.setHasFixedSize(true);
        holder.displayGallery.setAdapter(galleryAdapter);
        holder.displayGallery.setLayoutManager(new LinearLayoutManager(mActivity, LinearLayoutManager.HORIZONTAL,false));
        holder.displayGallery.getLayoutParams().width = galleryWidth;
        holder.displayGallery.setNestedScrollingEnabled(false);

        holder.uploadFragmentLI_stageTitleTV.setText(listItem.stagetitle + ":" + listItem.progresspiclist.size());
        holder.uploadFragmentLI_editDeleteTV.setText(holder.TEXT_TRUE);

        holder.uploadFragmentLI_editDeleteTV.setTag(listItem.stageid);
        holder.uploadFragmentLI_viewIV.setTag(listItem.stageid);
        holder.uploadFragmentLI_takePictureIV.setTag(listItem.stageid);

        uploadMap.put(listItem.stageid,position);

        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));

        if (listItem.faqentity != null){
            imageLoader.loadImage(listItem.faqentity.picurl,new SimpleImageLoadingListener(){
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    holder.uploadFragmentLI_viewIV.setImageBitmap(loadedImage);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    super.onLoadingFailed(imageUri, view, failReason);
                    Toast.makeText(mContext, "Image failed to load", Toast.LENGTH_SHORT).show();
                }
            });

        }

        galleryAdapter.setGalleryDeletedListener(new GalleryDeletedListener() {
            @Override
            public void onGalleryDelete() {
                holder.uploadFragmentLI_stageTitleTV.setText(listItem.stagetitle + ":" + galleryAdapter.getItemCount());
            }
        });

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentClickedListItem =0;
                View grandparent = (View) v.getParent().getParent();
//                currentClickedListItem = ((int)grandparent.getTag());
                currentClickedListItem =(int) v.getTag();
                switch (v.getId()){

                    case R.id.uploadFragmentLI_editDeleteTV:
                        if (holder.editOrDelete)holder.uploadFragmentLI_editDeleteTV.setText(holder.TEXT_FALSE);
                        else holder.uploadFragmentLI_editDeleteTV.setText(holder.TEXT_TRUE);
                        holder.editOrDelete = !holder.editOrDelete;
                        galleryAdapter.setDelete(holder.editOrDelete);

//                        TODO This has to do alot more than just change the letters. It has to allow the fucking pictures to be deleted
                        break;
                    case R.id.uploadFragmentLI_viewIV:
                        Intent viewPictures = new Intent(mContext,SamplePictureActivity.class);
                        viewPictures.putExtra("IMAGE_VIEW", currentClickedListItem);
                        viewPictures.putExtra("BEAN", uploadListItems.get(uploadMap.get(currentClickedListItem)));
                           mContext.startActivity(viewPictures);
                        break;
                    case R.id.uploadFragmentLI_takePictureIV:
                        getPictures(currentClickedListItem);
                        break;
                }
            }
        };

        holder.uploadFragmentLI_editDeleteTV.setOnClickListener(clickListener);
        holder.uploadFragmentLI_viewIV.setOnClickListener(clickListener);
        holder.uploadFragmentLI_takePictureIV.setOnClickListener(clickListener);

        return pDUFListItem;
    }

    @Override
    protected void getPictures(int id) {
        super.getPictures(id);
    }
}
