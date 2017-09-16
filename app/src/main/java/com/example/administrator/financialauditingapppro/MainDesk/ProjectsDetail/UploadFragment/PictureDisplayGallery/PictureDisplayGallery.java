package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.PictureDisplayGallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.ProgressPicList;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/21/2017.
 */

public class PictureDisplayGallery extends HorizontalScrollView{

    ArrayList<ProgressPicList> progressPicLists;
    RecyclerView pictureDisplayGalleryList;
    boolean addOrDelete;

    public PictureDisplayGallery(Context context) {
        super(context);
        setHorizontalScrollBarEnabled(false);
        init();
    }
    public PictureDisplayGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);
        init();
    }

    public PictureDisplayGallery(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setHorizontalScrollBarEnabled(false);
        init();
    }


    private void init() {

        LayoutInflater.from(getContext()).inflate(R.layout.layout_picture_display_gallery, this);
        pictureDisplayGalleryList = (RecyclerView) findViewById(R.id.pictureDisplayGalleryList);
        pictureDisplayGalleryList.setAdapter(new PictureGalleryListAdapter(getContext(),progressPicLists));
    }

    public void setProgressPicLists(ArrayList<ProgressPicList> progressPicLists){
        this.progressPicLists = progressPicLists;
    }

    public ArrayList<ProgressPicList> getProgressPicLists(){
        return progressPicLists;
    }


    public void setAddOrDelete(boolean addOrDelete) {
        this.addOrDelete = addOrDelete;
    }

     public boolean getAddOrDelete(){
         return addOrDelete;
     }
}
