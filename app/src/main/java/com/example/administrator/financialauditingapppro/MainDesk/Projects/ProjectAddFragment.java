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

public class ProjectAddFragment extends ProjectsBaseFragment {


//    BeanProjectFragment beanProjectFragment;
    ArrayList<BeanProjectFragment.ProjectInformation> projectInformations;

    ListView projectsAddLV;

    String url;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projects_add,null);
    }

    @Override
    public void postRequest() {
        super.postRequest();
//        projectFragmentBean = getProjectFragmentBean(SharedPreferencesUtil.PROJECT_ADD_INFO);
        projectInformations = projectFragmentBean.pagedata;
        projectsAddLV = (ListView) getView().findViewById(R.id.projectsAddLV);
        projectsAddLV.setAdapter(new ProjectAdapter(this.getContext(),projectInformations));
    }
}
