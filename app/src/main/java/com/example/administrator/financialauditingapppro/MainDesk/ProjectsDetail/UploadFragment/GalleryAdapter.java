package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.ImageGenerals.ImageRequest;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfoFromSharedPref;
import com.example.administrator.financialauditingapppro.net.Beans.ProgressPicList;
import com.example.administrator.financialauditingapppro.net.ProjectURLS;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.json.JSONStringer;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/22/2017.
 */

//TODO fill this out once taking images has been figured out.

class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryHolder> implements BeanUserInfoFromSharedPref{

    Context mContext;
    Activity activity;
    ArrayList<ProgressPicList> picLists;
    ArrayList<Bitmap> images;
    boolean delete;
    GalleryDeletedListener galleryDeletedListener;

    public GalleryAdapter(Context context, ArrayList<ProgressPicList> progressPicLists, boolean b) {
        this.mContext = context;
        this.picLists = progressPicLists;
        this.delete = b;
    }

    public class GalleryHolder extends RecyclerView.ViewHolder{
        public ImageView galleryIV;

        public GalleryHolder(View itemView) {
            super(itemView);
            galleryIV = (ImageView) itemView.findViewById(R.id.galleryIV);
        }

    }
    @Override
    public GalleryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View accountView = inflater.inflate(R.layout.layout_gallery_item,parent,false);
        GalleryHolder galleryHolder = new GalleryHolder(accountView);

        return galleryHolder;
    }

    @Override
    public void onBindViewHolder(GalleryHolder galleryHolder, int position) {
        ProgressPicList progressPic = picLists.get(position);

        final ImageView galleryIV = galleryHolder.galleryIV;
        galleryIV.setImageResource(R.drawable.finvepointstar_default);
//        switch (position){
////            case 0: galleryIV.setImageResource(R.drawable.icon_lock); break;
////            case 1: galleryIV.setImageResource(R.drawable.icon_arrowback); break;
////            case 2: galleryIV.setImageResource(R.drawable.icon_user); break;
////            case 3: galleryIV.setImageResource(R.drawable.icon_redarrowwitharc); break;
//        }

//        galleryIV.setBackgroundResource( R.drawable.finvepointstar_default);
        galleryIV.setTag(position);


        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));

        galleryIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "this is "+ delete, Toast.LENGTH_SHORT).show();
                if (!delete){
                    final int pos = (int)galleryIV.getTag();
                    ProgressPicList pic = picLists.get(pos);
                    BeanUserInfo beanUserInfo = getBeanUserInfo();

                    final String[] urlParams = new String[]{
                            ProjectURLS.APP + ProjectURLS.ASSISTANT_EXTENSION,
                            ProjectURLS.VERSION_NUMBER,
                            ProjectURLS.DELETEPICTURE_EXTENSION
                    };
                    JSONStringer jsonString = new JSONStringer();
                    try {
                        jsonString.object().key("customerid").value(beanUserInfo.customerid)
                                .key("progressid").value(pic.progressid)
                                .key("stageid").value(pic.stageid)
                                .key("picid").value(pic.id).endObject();
                    } catch (Exception e) {
                    }

                    Response.Listener<String> stringListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            int k =0;
                            picLists.remove(pos);
                            notifyItemRemoved(pos);
                            notifyItemRangeChanged(pos, picLists.size());
                            postResponse();
                        }
                    };
                    Response.ErrorListener errorListener = new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                            Toast.makeText(mContext, "FailureToDelete", Toast.LENGTH_SHORT).show();
                        }
                    };

                    ImageRequest request = ImageRequest.imageRequestPost(jsonString.toString(), null,stringListener, errorListener,urlParams);
                    RequestQueue queue = Volley.newRequestQueue(mContext);
                    queue.add(request);

                }
            }
        });

        if (picLists.isEmpty() || progressPic == null){
            return;
        }

        imageLoader.loadImage(progressPic.picurl, new SimpleImageLoadingListener(){
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                super.onLoadingComplete(imageUri, view, loadedImage);
                galleryIV.setImageBitmap(loadedImage);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                super.onLoadingFailed(imageUri, view, failReason);
                Toast.makeText(mContext, "Image failed to load", Toast.LENGTH_SHORT).show();

                galleryIV.setImageResource(R.drawable.finvepointstar_default);
            }
        });

    }

    private void postResponse() {
        this.galleryDeletedListener.onGalleryDelete();
    }

    private void postLoad() {
    }

    @Override
    public int getItemCount() {
        return picLists.size();
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public BeanUserInfo getBeanUserInfo() {
        BeanUserInfo bean=new Gson().fromJson(SharedPreferencesUtil.getJsonString(mContext, SharedPreferencesUtil.USER_INFO),BeanUserInfo.class);
        return bean;
    }

    public void setGalleryDeletedListener (GalleryDeletedListener listener){
        this.galleryDeletedListener = listener;
    }
}
