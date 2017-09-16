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

import java.util.ArrayList;

/**
 * Created by Administrator on 6/9/2017.
 */

public class BenchPendingFragment extends BenchBaseFragment {

    ArrayList<BeanBench.UploadPicNotice> picNotices;

    BeanUserInfo beanUserInfo;
    BeanBench beanBench;

    ListView bFPendingLV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bench_pending,null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        beanUserInfo = getBeanUserInfo();
        beanBench = getBeanBench();

        if (beanBench.picnotice != null){
            picNotices = beanBench.picnotice;
        }
        bFPendingLV = (ListView) getView().findViewById(R.id.bFPendingLV);
        bFPendingLV.setAdapter(new BenchPendingAdapter(getContext(),picNotices));
    }
}
