package com.example.administrator.financialauditingapppro.MainDesk.Projects;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.financialauditingapppro.MainDesk.Default_Request;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBase;
import com.example.administrator.financialauditingapppro.net.Beans.BeanProjectFragment;
import com.example.administrator.financialauditingapppro.net.SharedPreferencesUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import static com.example.administrator.financialauditingapppro.net.StaticConstants.TIMEOUTMS;

/**
 * Created by Administrator on 6/13/2017.
 */

public class ProjectsBaseFragment extends Fragment {

    BeanProjectFragment projectFragmentBean;
    String url;
    String userStore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return null;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getInfo();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public BeanProjectFragment getProjectFragmentBean(String sharedPreferenceURL) {
        BeanProjectFragment bean=new Gson().fromJson(SharedPreferencesUtil.getJsonString(getContext(), sharedPreferenceURL),BeanProjectFragment.class);
        return bean;
    }

    public void postRequest(){

    }

    public void getInfo(){
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                BeanBase<BeanProjectFragment> projectFragmentBeanBase=new Gson().fromJson(response,new TypeToken<BeanBase<BeanProjectFragment>>(){}.getType());

                String embnbbn = new Gson().toJson(projectFragmentBeanBase);
                Log.d("BEAN",embnbbn);
                if(projectFragmentBeanBase.code.equals("200")){

                    if (projectFragmentBeanBase.data != null){
                        projectFragmentBean = projectFragmentBeanBase.data;
                        boolean saveSuccess = SharedPreferencesUtil.saveInSharedPreferences(getContext(),projectFragmentBean,userStore);
                        if(saveSuccess){
                            postRequest();
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), ""+ projectFragmentBeanBase.msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };
        Default_Request benchProjectRequest = new Default_Request(url, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
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

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUserStore(String userStore) {
        this.userStore = userStore;
    }
}
