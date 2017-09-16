package com.example.administrator.financialauditingapppro.MainDesk.Bench;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.financialauditingapppro.MainDesk.Default_Request;
import com.example.administrator.financialauditingapppro.MainDesk.MainFragmentPagerAdapter;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBase;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBench;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfo;
import com.example.administrator.financialauditingapppro.net.Beans.BeanUserInfoFromSharedPref;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.example.administrator.financialauditingapppro.net.StaticConstants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

/**
 * Created by Administrator on 6/7/2017.
 */

public class MainBenchFragment extends Fragment implements BeanUserInfoFromSharedPref, View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    BeanUserInfo beanUserInfo;
    BeanBench beanBench;
    ImageView bFSearch;
    RadioGroup bFRadioGroup;

    RadioButton bFPendingButton;
    RadioButton bFProcessedButton;
    TextView bFMessage;

    ViewPager bFViewPager;
    private final int TIMEOUTMS = StaticConstants.TIMEOUTMS;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bench,null);
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
        setIDs();
        setBackgroundResources();
        getListInfo();


    }

    private void setBackgroundResources() {
        bFPendingButton.setBackgroundResource(R.drawable.x_radiobutton_color_selector);
        bFProcessedButton.setBackgroundResource(R.drawable.x_radiobutton_color_selector);
    }

    private void postRequest(){
        ArrayList<Fragment> fragments = new ArrayList<>();
        BenchPendingFragment pending = new BenchPendingFragment();
        BenchProcessedFragment processed = new BenchProcessedFragment();
        fragments.add(pending);
        fragments.add(processed);
        bFRadioGroup.setOnCheckedChangeListener(this);
        bFViewPager.setAdapter(new MainFragmentPagerAdapter(getFragmentManager(),fragments));
        bFViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                changeColors();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        int i = bFViewPager.getCurrentItem();
        bFSearch.setOnClickListener(this);
    }

    private void setIDs() {
        bFSearch = (ImageView) getView().findViewById(R.id.bFSearch);
        bFRadioGroup = (RadioGroup) getView().findViewById(R.id.bFRadioGroup);
        bFViewPager = (ViewPager) getView().findViewById(R.id.bFViewPager);
        bFPendingButton = (RadioButton) getView().findViewById(R.id.bFPendingButton);
        bFProcessedButton = (RadioButton) getView().findViewById(R.id.bFProcessedButton);
        bFMessage = (TextView) getView().findViewById(R.id.bFMessage);
    }

    @Override
    public BeanUserInfo getBeanUserInfo() {
        BeanUserInfo bean=new Gson().fromJson(SharedPreferencesUtil.getJsonString(getContext(), SharedPreferencesUtil.USER_INFO),BeanUserInfo.class);
        return bean;
    }

    private void getListInfo() {
        int i =0;
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                BeanBase<BeanBench> beanBenchBeanBase=new Gson().fromJson(response,new TypeToken<BeanBase<BeanBench>>(){}.getType());

                String embnbbn = new Gson().toJson(beanBenchBeanBase);
                Log.d("BEAN",embnbbn);
//                Toast.makeText(LoginActivity.this, "successful login", Toast.LENGTH_SHORT).show();

                if(beanBenchBeanBase.code.equals("200")){

                    if (beanBenchBeanBase.data != null){
                        beanBench = beanBenchBeanBase.data;
                        boolean saveSuccess = SharedPreferencesUtil.saveInSharedPreferences(getContext(),beanBench,SharedPreferencesUtil.BENCH_INFO);

                        if (saveSuccess){
                            Log.d("BENCH_INFO", "Sucess");
                            postRequest();
                        }
                        else{

                            Log.d("BENCH_INFO", "Failure");
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        };

        String benchURL = getURL();

        Default_Request benchProjectRequest = new Default_Request(benchURL, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Log.d("LOGIN Response","WHY CANT I LOGGGINNNNNNN");
                Toast.makeText(getActivity(), "TIMEOUT ERROR", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        benchProjectRequest.setRetryPolicy(
                new DefaultRetryPolicy(
                        TIMEOUTMS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(benchProjectRequest);


    }

    private String getURL() {
//        String url = ProjectURLS.BASEURL + ProjectURLS.ASSISTANT_EXTENSION + ProjectURLS.VERSION_NUMBER + ProjectURLS.ASSISTANT_EXTENSION;
        String url = "http://test9.525j.com.cn/app/assistantapi/v1.0/assistant.projectprogressstagenoticelist/"+beanUserInfo.foremanid + "/0?needcache=1";
        return url;
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getActivity(), "THis button doesnt do anything yet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.bFPendingButton:
                bFViewPager.setCurrentItem(0);
                break;
            case R.id.bFProcessedButton:
                bFViewPager.setCurrentItem(1);
                break;
        }

//        changeColors();
    }


    private void changeColors() {
        int state = bFViewPager.getCurrentItem();
        if (state ==0){
            bFPendingButton.setTextColor(Color.WHITE);
            bFPendingButton.setBackgroundColor(Color.RED);

            bFProcessedButton.setTextColor(Color.BLACK);
            bFProcessedButton.setBackgroundColor(Color.WHITE);
            int i = beanBench.picnotice.size();
            bFMessage.setText("You have " + i + " Pictures to upload");
        }
        else if (state ==1){
            bFProcessedButton.setTextColor(Color.WHITE);
            bFProcessedButton.setBackgroundColor(Color.RED);

            bFPendingButton.setTextColor(Color.BLACK);
            bFPendingButton.setBackgroundColor(Color.WHITE);
            int i = beanBench.projectnotices.size();
            bFMessage.setText("You have " + i + " ProjectNotices processing");
        }
    }
}
