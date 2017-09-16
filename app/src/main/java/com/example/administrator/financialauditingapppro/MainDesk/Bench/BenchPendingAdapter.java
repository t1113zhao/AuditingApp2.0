package com.example.administrator.financialauditingapppro.MainDesk.Bench;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.financialauditingapppro.MainDesk.MainActivity;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.ProjectDetailsActivity;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBench;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.DateUtil;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 6/9/2017.
 */

public class BenchPendingAdapter extends BaseAdapter implements View.OnClickListener{


    ArrayList<BeanBench.UploadPicNotice> picNotices;
    BeanBench.UploadPicNotice information;
    Context mContext;
    private static LayoutInflater inflater = null;

    public BenchPendingAdapter(Context context, ArrayList<BeanBench.UploadPicNotice> picNotices){
        this.picNotices = picNotices;
        this.mContext = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return picNotices.size();
    }

    @Override
    public BeanBench.UploadPicNotice getItem(int position) {
        return picNotices.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position ;
    }



    public class Holder{
        TextView pendingListDateTV;
        TextView pendingListAddressTV;
        TextView pendingListStageTV;
        TextView pendingListMessage;
        TextView uploadPhotoTV;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Holder holder = new Holder();
        View pendingListItem;
        BeanBench.UploadPicNotice uploadPicNotice = picNotices.get(position);
        information = uploadPicNotice;

        pendingListItem = inflater.inflate(R.layout.layout_pending_list_item,null);

        holder.pendingListDateTV =  (TextView) pendingListItem.findViewById(R.id.pendingListDateTV);
        holder.pendingListAddressTV =  (TextView) pendingListItem.findViewById(R.id.pendingListAddressTV);
        holder.pendingListStageTV =  (TextView) pendingListItem.findViewById(R.id.pendingListStageTV);
        holder.pendingListMessage =  (TextView) pendingListItem.findViewById(R.id.pendingListMessage);
        holder.uploadPhotoTV =  (TextView) pendingListItem.findViewById(R.id.uploadPhotoTV);

        String date = DateUtil.getDateToDay( DateUtil.parseDateTime(uploadPicNotice.noticetime).getTime());

        holder.pendingListDateTV.setText(date+"");
        holder.pendingListAddressTV.setText(uploadPicNotice.address);
        holder.pendingListStageTV.setText(uploadPicNotice.stagename);
        holder.uploadPhotoTV.setTag(uploadPicNotice.projectid);
        holder.uploadPhotoTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ProjectDetailsActivity.class);
                intent.putExtra("ID",holder.uploadPhotoTV.getTag().toString());
//        intent.putExtra("SENTFROMBENCH", true);
//        intent.putExtra("PROJECT",information );
//        BeanUserInfo bean = new Gson().fromJson(SharedPreferencesUtil.getJsonString(mContext, SharedPreferencesUtil.USER_INFO),BeanUserInfo.class);
//        intent.putExtra("USERID",bean.foremanid);
                mContext.startActivity(intent);
            }
        });
        holder.pendingListMessage.setText(uploadPicNotice.msg);
        return pendingListItem;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(mContext, ProjectDetailsActivity.class);
//        intent.putExtra("ID",)
//        intent.putExtra("SENTFROMBENCH", true);
//        intent.putExtra("PROJECT",information );
//        BeanUserInfo bean = new Gson().fromJson(SharedPreferencesUtil.getJsonString(mContext, SharedPreferencesUtil.USER_INFO),BeanUserInfo.class);
//        intent.putExtra("USERID",bean.foremanid);
        mContext.startActivity(intent);
    }
}
