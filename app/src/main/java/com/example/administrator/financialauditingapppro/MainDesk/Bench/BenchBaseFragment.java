package com.example.administrator.financialauditingapppro.MainDesk.Bench;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.financialauditingapppro.MainDesk.Bench.BenchPendingAdapter;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBench;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBenchFromSharedPref;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfoFromSharedPref;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/13/2017.
 */

public class BenchBaseFragment extends Fragment implements BeanUserInfoFromSharedPref, BeanBenchFromSharedPref {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public BeanUserInfo getBeanUserInfo() {
        BeanUserInfo bean=new Gson().fromJson(SharedPreferencesUtil.getJsonString(getContext(), SharedPreferencesUtil.USER_INFO),BeanUserInfo.class);
        return bean;
    }

    @Override
    public BeanBench getBeanBench() {
        BeanBench bean=new Gson().fromJson(SharedPreferencesUtil.getJsonString(getContext(), SharedPreferencesUtil.BENCH_INFO),BeanBench.class);
        return bean;
    }
}
