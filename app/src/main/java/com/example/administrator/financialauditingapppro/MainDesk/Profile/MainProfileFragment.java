package com.example.administrator.financialauditingapppro.MainDesk.Profile;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfoFromSharedPref;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/7/2017.
 */

public class MainProfileFragment extends android.support.v4.app.Fragment implements BeanUserInfoFromSharedPref{

    BeanUserInfo beanUserInfo;

    ImageView profileForemanIV;

    ListView profileListLV;

    TextView profileForemanPhoneTV;

    View parentView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile,null);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        parentView = view;
        init();
    }

    private void init() {
        beanUserInfo = getBeanUserInfo();
        setIDs();

        ArrayList<String> categories = new ArrayList<>();
        categories.add("Rating");
        categories.add("RoleName");
        categories.add("Sign Out");

        profileListLV.setAdapter(new MainProfileAdapter(this.getActivity(),categories,beanUserInfo));
            profileForemanPhoneTV.setText(beanUserInfo.phone);
//        }
        profileForemanPhoneTV.setText(beanUserInfo.telephone);
        if (!TextUtils.isEmpty(beanUserInfo.foremanpic)){
            profileForemanIV.setImageURI(Uri.parse(beanUserInfo.foremanpic));
        }


    }

    private void setIDs() {
        profileForemanPhoneTV = (TextView) parentView.findViewById(R.id.profileForemanPhoneTV);
        profileListLV = (ListView) parentView.findViewById(R.id.profileListLV);
        profileForemanIV = (ImageView) parentView.findViewById(R.id.profileForemanIV);
    }

    @Override
    public BeanUserInfo getBeanUserInfo() {
        BeanUserInfo bean=new Gson().fromJson(SharedPreferencesUtil.getJsonString(getContext(), SharedPreferencesUtil.USER_INFO),BeanUserInfo.class);
        return bean;
    }
}
