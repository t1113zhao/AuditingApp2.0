package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.PictureDisplayGallery;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.example.administrator.financialauditingapppro.net.Beans.ProgressPicList;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/22/2017.
 */

//TODO fill this out once taking images has been figured out.

class PictureGalleryListAdapter extends RecyclerView.Adapter {

    Context mContext;
    ArrayList<ProgressPicList> picLists;

    public PictureGalleryListAdapter(Context context, ArrayList<ProgressPicList> progressPicLists) {
        this.mContext = context;
        this.picLists = progressPicLists;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
