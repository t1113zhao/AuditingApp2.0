package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.financialauditingapppro.net.Beans.BeanProjectDetails;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfoFromSharedPref;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.google.gson.Gson;

/**
 * Created by Administrator on 6/16/2017.
 */

public class ProjectDetailsBaseFragment extends Fragment implements BeanUserInfoFromSharedPref {


    protected BeanUserInfo beanUserInfo;
    protected BeanProjectDetails beanProjectDetails;
    public String url;

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public BeanUserInfo getBeanUserInfo() {
        BeanUserInfo bean=new Gson().fromJson(SharedPreferencesUtil.getJsonString(getContext(), SharedPreferencesUtil.USER_INFO),BeanUserInfo.class);
        return bean;
    }

    public BeanProjectDetails getBeanProjectDetails(){
        BeanProjectDetails bean=new Gson().fromJson(SharedPreferencesUtil.getJsonString(getContext(), SharedPreferencesUtil.PROJECT_DETAILS),BeanProjectDetails.class);
        return bean;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
