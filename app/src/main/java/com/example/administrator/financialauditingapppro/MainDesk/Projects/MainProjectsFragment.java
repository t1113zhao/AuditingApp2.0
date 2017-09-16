package com.example.administrator.financialauditingapppro.MainDesk.Projects;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.financialauditingapppro.MainDesk.MainFragmentPagerAdapter;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanProjectFragment;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfoFromSharedPref;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.example.administrator.financialauditingapppro.net.StaticConstants;
import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/7/2017.
 */

public class MainProjectsFragment extends android.support.v4.app.Fragment implements BeanUserInfoFromSharedPref, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    ArrayList<BeanProjectFragment.ProjectInformation> beanProjectFragments;
    BeanUserInfo beanUserInfo;
    RadioGroup projectsRadioGroup;
    RadioButton projectsAddButton;
    RadioButton projectsConstructionButton;
    RadioButton projectsCompletedButton;
    ImageView projectsSearch;

    int pageIndex;
    int pageSize;
    int needPage;

    final int DEFAULT_PAGE_SIZE = 30;
    final int DEFAULT_PAGE_INDEX =1;

   int TIMEOUTMS = StaticConstants.TIMEOUTMS;
    ViewPager projectsViewPager;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projects,null);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
        beanUserInfo = getBeanUserInfo();
        setIds();
        setBackgroundResources();
        setValues();

        ArrayList<Fragment> fragments = new ArrayList<>();
        ProjectAddFragment add = new ProjectAddFragment();
        ProjectConstructionFragment construct = new ProjectConstructionFragment();
        ProjectCompleteFragment complete = new ProjectCompleteFragment();

        add.setUrl(getURL(1));
        construct.setUrl(getURL(2));
        complete.setUrl(getURL(3));

        add.setUserStore(SharedPreferencesUtil.PROJECT_ADD_INFO);
        construct.setUserStore(SharedPreferencesUtil.PROJECT_CONSTRUCT_INFO);
        complete.setUserStore(SharedPreferencesUtil.PROJECT_COMPLETE_INFO);

        fragments.add(add);
        fragments.add(construct);
        fragments.add(complete);
        projectsRadioGroup.setOnCheckedChangeListener(this);
        projectsViewPager.setAdapter(new MainFragmentPagerAdapter(getFragmentManager(),fragments));
        projectsViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                changeColors();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        int i = projectsViewPager.getCurrentItem();
        projectsSearch.setOnClickListener(this);
    }

    private void setBackgroundResources() {
        projectsAddButton.setBackgroundResource(R.drawable.x_radiobutton_color_selector);
        projectsConstructionButton.setBackgroundResource(R.drawable.x_radiobutton_color_selector);
        projectsCompletedButton.setBackgroundResource(R.drawable.x_radiobutton_color_selector);
    }

    private void setIds() {
        projectsSearch = (ImageView) getView().findViewById(R.id.projectsSearch);
        projectsRadioGroup = (RadioGroup) getView().findViewById(R.id.projectsRadioGroup);
        projectsViewPager = (ViewPager) getView().findViewById(R.id.projectsViewPager);
        projectsAddButton = (RadioButton) getView().findViewById(R.id.projectsAddButton);
        projectsConstructionButton = (RadioButton) getView().findViewById(R.id.projectsConstructionButton);
        projectsCompletedButton = (RadioButton) getView().findViewById(R.id.projectsCompletedButton);
    }

    private void setValues() {
        pageIndex =DEFAULT_PAGE_INDEX;
        pageSize = DEFAULT_PAGE_SIZE;
        needPage =DEFAULT_PAGE_INDEX;
    }

    private String getURL(int page) {
        String url = "http://test9.525j.com.cn/app/comapi/v1.0/com.getprojectlist/";
        url += beanUserInfo.foremanid + "/" + page+"?";
        url += "pageindex=" + pageIndex + "&pagesize=" + pageSize + "&needpage=" + needPage;
        return url;
    }

    @Override
    public BeanUserInfo getBeanUserInfo() {
        BeanUserInfo bean=new Gson().fromJson(SharedPreferencesUtil.getJsonString(getContext(), SharedPreferencesUtil.USER_INFO),BeanUserInfo.class);
        return bean;
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.projectsAddButton:
                projectsViewPager.setCurrentItem(0);
                break;
            case R.id.projectsConstructionButton:
                projectsViewPager.setCurrentItem(1);
                break;
            case R.id.projectsCompletedButton:
                projectsViewPager.setCurrentItem(2);
                break;
        }


//        changeColors();
    }

    private void changeColors() {
        int state = projectsViewPager.getCurrentItem();
        if (state ==0){
            projectsAddButton.setTextColor(Color.WHITE);
            projectsAddButton.setBackgroundColor(Color.RED);

            projectsConstructionButton.setTextColor(Color.BLACK);
            projectsConstructionButton.setBackgroundColor(Color.WHITE);

            projectsCompletedButton.setTextColor(Color.BLACK);
            projectsCompletedButton.setBackgroundColor(Color.WHITE);
        }
        else if (state ==1){

            projectsAddButton.setTextColor(Color.BLACK);
            projectsAddButton.setBackgroundColor(Color.WHITE);

            projectsConstructionButton.setTextColor(Color.WHITE);
            projectsConstructionButton.setBackgroundColor(Color.RED);

            projectsCompletedButton.setTextColor(Color.BLACK);
            projectsCompletedButton.setBackgroundColor(Color.WHITE);
        }
        else if (state ==2){
            projectsAddButton.setTextColor(Color.BLACK);
            projectsAddButton.setBackgroundColor(Color.WHITE);

            projectsConstructionButton.setTextColor(Color.BLACK);
            projectsConstructionButton.setBackgroundColor(Color.WHITE);

            projectsCompletedButton.setTextColor(Color.WHITE);
            projectsCompletedButton.setBackgroundColor(Color.RED);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.projectsSearch:
                Toast.makeText(getActivity(), "this button doesnt do anything yet", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
