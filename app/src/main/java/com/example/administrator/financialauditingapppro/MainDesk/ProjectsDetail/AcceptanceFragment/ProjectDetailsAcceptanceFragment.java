package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.AcceptanceFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.ProjectDetailsBaseFragment;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;

/**
 * Created by Administrator on 6/16/2017.
 */

public class ProjectDetailsAcceptanceFragment extends ProjectDetailsBaseFragment {


    ListView pDAF_LV;
    BeanUserInfo beanUserInfo;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projects_details_acceptance,null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        setIDs();
        getInfo();
    }

    private void setIDs() {
    }

    private void getInfo() {
        
    }


}
