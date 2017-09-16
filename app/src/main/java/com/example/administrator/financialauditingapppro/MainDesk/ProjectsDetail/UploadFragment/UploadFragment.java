package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.example.administrator.financialauditingapppro.MainDesk.Default_Request;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.HRG_ChangeButtonListener;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.HorizontalScrollRadioGroup;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.ProjectDetailsBaseFragment;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.ImageGenerals.ImageActivity;
import com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.UploadFragment.ImageGenerals.ImageAdapter;
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBase;
import com.example.administrator.financialauditingapppro.net.Beans.BeanPDUploadListItem;
import com.example.administrator.financialauditingapppro.net.ProjectURLS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import static com.example.administrator.financialauditingapppro.net.StaticConstants.TIMEOUTMS;

/**
 * Created by Administrator on 6/16/2017.
 */

public class UploadFragment extends ProjectDetailsBaseFragment implements HRG_ChangeButtonListener {


    ImageActivity imageActivity;
    ImageAdapter adapter;
    ListView pDUFLV;
    HorizontalScrollRadioGroup pDUF_HRG;
    int currentSelected =0;
    ArrayList<BeanPDUploadListItem> uploadListItems;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_projects_details_upload,null);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }


    private void init() {
        pDUF_HRG = new HorizontalScrollRadioGroup(this.getContext());
        setIDs();
        beanUserInfo = getBeanUserInfo();
        beanProjectDetails = getBeanProjectDetails();
        getInfo();

        currentSelected = pDUF_HRG.getCurrentSelected();
        pDUF_HRG.setHRG_ChangeButtonListener(this);
        pDUF_HRG.setVisibility(View.VISIBLE);
    }

    private void setIDs() {
        pDUFLV = (ListView) getView().findViewById(R.id.pDUFLV);
        pDUF_HRG = (HorizontalScrollRadioGroup) getView().findViewById(R.id.pDUF_HRG);
    }

    @Override
    public void onChangeButton() {
        if(currentSelected != pDUF_HRG.getCurrentSelected()){
            currentSelected = pDUF_HRG.getCurrentSelected();
            getInfo();
        }
    }

    private void setUrl() {
        ProjectURLS u = new ProjectURLS();
        url = u.BASEURL + u.APP_API_EXTENSION + u.VERSION_NUMBER +"/" + u.PROJECTPICTURE_EXTENSION;
        url += beanProjectDetails.id+"/"+ (pDUF_HRG.getCurrentSelected()+1) + "?customerid=" + beanProjectDetails.corpid;
    }

    private void getInfo() {
        setUrl();
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                BeanBase<ArrayList<BeanPDUploadListItem>> beans=new Gson().fromJson(response,new TypeToken<BeanBase<ArrayList<BeanPDUploadListItem>>>(){}.getType());

                String embnbbn = new Gson().toJson(beans);
                Log.d("BEAN",embnbbn);
                if(beans.code.equals("200")){

                    if (beans.data != null){
                        uploadListItems = beans.data;
                        repaginate();
                    }
                    else{
                        Toast.makeText(getActivity(), ""+ beans.msg, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        };

        Default_Request request = new Default_Request(url, listener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "TIMEOUT ERROR", Toast.LENGTH_SHORT).show();
                error.printStackTrace();
            }
        });

        request.setRetryPolicy(
                new DefaultRetryPolicy(
                        TIMEOUTMS,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(request);
    }


    private void repaginate() {
        adapter =  new UploadAdapter(this.getActivity(),uploadListItems);
        adapter.mActivity = imageActivity;
        pDUFLV.setAdapter(adapter);
        pDUFLV.setVisibility(View.VISIBLE);
    }



    public ImageAdapter getAdapter() {
        return adapter;
    }

    public BeanPDUploadListItem getCurrentSelectedBeanPD(){
        int index = adapter.getLocalArrayPosition();
        return uploadListItems.get(index);
    }

    public void setImageActivity(ImageActivity activity){
        this.imageActivity = activity;
    }
}
