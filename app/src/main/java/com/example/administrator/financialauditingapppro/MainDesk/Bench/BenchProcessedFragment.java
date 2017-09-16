package com.example.administrator.financialauditingapppro.MainDesk.Bench;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBench;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.Beans.ProjectNotice;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/9/2017.
 */

public class BenchProcessedFragment extends BenchBaseFragment {

    ArrayList<ProjectNotice> projectNotices;
    BeanUserInfo beanUserInfo;

    BeanBench beanBench;
    ListView bFProcessedLV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bench_processed,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        beanUserInfo = getBeanUserInfo();
        bFProcessedLV = (ListView) getView().findViewById(R.id.bFProcessedLV);
        beanBench = getBeanBench();
        if (beanBench.projectnotices != null){
            projectNotices = beanBench.projectnotices;
        }

        bFProcessedLV.setAdapter(new BenchProcessedAdapter(getContext(),projectNotices));

    }
}
