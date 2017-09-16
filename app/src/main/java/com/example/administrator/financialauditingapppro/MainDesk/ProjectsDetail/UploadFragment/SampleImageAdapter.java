package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
 * Created by Administrator on 6/21/2017.
 */

public class SampleImageAdapter extends ImageAdapter {

    public SampleImageAdapter(Context mContext, ArrayList<BeanPDUploadListItem.Faqentity> faqentities, BeanPDUploadListItem pdUploadListItem){
        this.mContext = mContext;
        this.faqentities = faqentities;
        this.listItem = pdUploadListItem;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        uploadOrSample = StaticConstants.SAMPLE;
    }

    @Override
    public int getCount() {
        return faqentities.size();
    }

    @Override
    public Object getItem(int position) {
        return faqentities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return faqentities.get(position).id;
    }



    public class Holder{
        public TextView sampleTitleTV;
        public TextView sampleDescriptionTV;

        public ImageView sampleImageIV;
        public ImageView takeImageIV;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View samplePictureListItem;
        BeanPDUploadListItem.Faqentity faqentity = faqentities.get(position);

        samplePictureListItem = inflater.inflate(R.layout.layout_sample_picture_list_item,null);

        holder.sampleTitleTV =(TextView) samplePictureListItem.findViewById(R.id.sampleTitleTV);
        holder.sampleDescriptionTV =(TextView) samplePictureListItem.findViewById(R.id.sampleDescriptionTV);

        holder.sampleImageIV =(ImageView) samplePictureListItem.findViewById(R.id.sampleImageIV);
        holder.takeImageIV =(ImageView) samplePictureListItem.findViewById(R.id.takeImageIV);

        holder.sampleTitleTV.setText(faqentity.title);
        holder.sampleDescriptionTV.setText("Description:" + "\n"+faqentity.descriptions);

        holder.takeImageIV.setImageResource(R.drawable.icon_camera);
        holder.sampleImageIV.setImageResource(R.drawable.finvepointstar_default);

        holder.takeImageIV.setTag(faqentity.id);

        sampleMap.put(faqentity.id, position);
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(mContext));

        if (faqentity != null){
            imageLoader.loadImage(faqentity.picurl,new SimpleImageLoadingListener(){
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    super.onLoadingComplete(imageUri, view, loadedImage);
                    holder.sampleImageIV.setImageBitmap(loadedImage);
                }

                @Override
                public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    super.onLoadingFailed(imageUri, view, failReason);
                    Toast.makeText(mContext, "Image failed to load", Toast.LENGTH_SHORT).show();
                }
            });

        }

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (v.getId()){
                    case R.id.takeImageIV:
                        int i = (int) v.getTag();

                        getPictures(i);
                        break;
                }
            }
        };
        holder.takeImageIV.setOnClickListener(clickListener);
        return samplePictureListItem;
    }

    @Override
    protected void getPictures(int id) {
        super.getPictures(id);
        int u =0;
    }

    public void changeIV(int arrayPosition){
        Holder holder = new Holder();

    }

}
