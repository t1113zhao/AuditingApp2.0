package com.example.administrator.financialauditingapppro.MainDesk.Projects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanProjectFragment;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/13/2017.
 */

public class ProjectConstructionFragment extends ProjectsBaseFragment {

//    BeanProjectFragment beanProjectFragment;
    ArrayList<BeanProjectFragment.ProjectInformation> projectInformations;

    ListView projectsConstructionLV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projects_construction,null);
    }

//    @Override
//    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        init();
//    }

    public void postRequest() {
//        beanProjectFragment = getProjectFragmentBean(SharedPreferencesUtil.PROJECT_CONSTRUCT_INFO);
        projectInformations = projectFragmentBean.pagedata;
        projectsConstructionLV = (ListView) getView().findViewById(R.id.projectsConstructionLV);
        projectsConstructionLV.setAdapter(new ProjectAdapter(this.getContext(),projectInformations));
    }
}
