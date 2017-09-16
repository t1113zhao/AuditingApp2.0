package com.example.administrator.financialauditingapppro.MainDesk.Profile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.financialauditingapppro.Login.LoginActivity;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.Twin;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 6/8/2017.
 */

public class MainProfileAdapter extends BaseAdapter implements  View.OnClickListener {

    int[] iconIds = new int[]{
            R.drawable.user_start_levle,
            R.drawable.user_roles,
            R.drawable.icon_lock
    };
    Activity mContext;
    ArrayList<String> categoryList;
    BeanUserInfo beanUserInfo ;
    private static LayoutInflater inflater = null;

    public MainProfileAdapter(Activity activity, ArrayList<String> list,BeanUserInfo info){
        mContext = activity;
        categoryList = list;
        beanUserInfo = info;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return categoryList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class Holder{
        TextView pFLItemLeftTV;
        TextView pFLItemRightTV;

        ImageView listItemIcon;
        ImageView expandIcon;

        RatingBar pFLItemRB;

        RelativeLayout pFLItemRL;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder = new Holder();

        View rowView;
        rowView = inflater.inflate(R.layout.layout_profile_list_item,null);

        holder.pFLItemLeftTV = (TextView) rowView.findViewById(R.id.pFLItemLeftTV);
        holder.pFLItemRightTV = (TextView) rowView.findViewById(R.id.pFLItemRightTV);
        holder.listItemIcon = (ImageView) rowView.findViewById(R.id.pFLItemIconIV);
        holder.expandIcon = (ImageView) rowView.findViewById(R.id.pFLItemExpandIV);

        holder.pFLItemRB = (RatingBar) rowView.findViewById(R.id.pFLItemRB);
        holder.pFLItemRL = (RelativeLayout) rowView.findViewById(R.id.pFLItemRL);

        holder.pFLItemLeftTV.setText(categoryList.get(position));

        holder.listItemIcon.setImageResource(iconIds[position]);

        switch (position){
            case 0:
                holder.pFLItemRightTV.setVisibility(View.GONE);
                holder.expandIcon.setVisibility(View.GONE);
                holder.pFLItemRL.setOnClickListener(null);
//                holder.listItemIcon
                if (beanUserInfo != null)
                    holder.pFLItemRB.setRating(beanUserInfo.stars);
                break;
            case 1:
                holder.expandIcon.setVisibility(View.GONE);
                holder.pFLItemRB.setVisibility(View.GONE);
                holder.pFLItemRL.setOnClickListener(null);
                if (beanUserInfo != null)
                    holder.pFLItemRightTV.setText(beanUserInfo.rolename);
                break;
            case 2:
                holder.pFLItemRB.setVisibility(View.GONE);
                holder.pFLItemRightTV.setVisibility(View.GONE);
                holder.pFLItemRL.setOnClickListener(this);
                break;
        }

        return rowView;

    }


    @Override
    public void onClick(View v) {

        SharedPreferencesUtil.saveUserLoginState(mContext,false);
        Intent intent = new Intent(mContext, LoginActivity.class);
        SharedPreferencesUtil.clearSp(mContext);
        mContext.startActivity(intent);
        mContext.finish();
    }

}
