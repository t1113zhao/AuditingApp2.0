package com.example.administrator.financialauditingapppro.MainDesk.ProjectsDetail.AcceptanceFragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
import com.example.administrator.financialauditingapppro.R;
import com.example.administrator.financialauditingapppro.net.Beans.BeanBase;
import com.example.administrator.financialauditingapppro.net.Beans.BeanPDAcceptance;
import com.example.administrator.financialauditingapppro.net.ProjectURLS;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DecimalFormat;

import static com.example.administrator.financialauditingapppro.net.DateUtil.parseDateTime;
import static com.example.administrator.financialauditingapppro.net.DateUtil.parseTimeToDate;
import static com.example.administrator.financialauditingapppro.net.StaticConstants.TIMEOUTMS;

/**
 * Created by Administrator on 6/16/2017.
 */

public class AcceptanceFragment extends ProjectDetailsBaseFragment implements HRG_ChangeButtonListener {

    BeanPDAcceptance pdAcceptance;
    HorizontalScrollRadioGroup pDAF_HRG;
    int currentSelected;
    ListView acceptanceLV;
    LinearLayout acceptanceInfoLL;
    RelativeLayout bottomRL;
    TextView acceptanceDateTV;
    TextView acceptanceStatusTV;
    TextView complete;
    TextView amountRequestedValue;




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
        pDAF_HRG = new HorizontalScrollRadioGroup(this.getContext());
        setIDs();
        beanUserInfo = getBeanUserInfo();
        beanProjectDetails = getBeanProjectDetails();
        currentSelected = pDAF_HRG.getCurrentSelected();
        pDAF_HRG.setHRG_ChangeButtonListener(this);
        pDAF_HRG.setVisibility(View.VISIBLE);
        getInfo();
    }

    private void setIDs() {
        pDAF_HRG = (HorizontalScrollRadioGroup) getView().findViewById(R.id.pDAF_HRG);
        acceptanceInfoLL = (LinearLayout) getView().findViewById(R.id.acceptanceInfoLL);
        acceptanceLV = (ListView) getView() .findViewById(R.id.acceptanceLV);
        bottomRL = (RelativeLayout) getView().findViewById(R.id.bottomRL);
        amountRequestedValue = (TextView) getView().findViewById(R.id.amountRequestedValue);
        complete = (TextView) getView().findViewById(R.id.complete);
        acceptanceDateTV = (TextView) getView().findViewById(R.id.acceptanceDateTV);
        acceptanceStatusTV = (TextView) getView().findViewById(R.id.acceptanceStatusTV);

    }

    private void setUrl() {
        ProjectURLS u = new ProjectURLS();
        url = u.BASEURL + u.APP_API_EXTENSION + u.VERSION_NUMBER + "/"+ u.PROJECYTPROGRESSCHECK;
        url += beanUserInfo.foremanid+"/"+beanProjectDetails.id+"/"+ (pDAF_HRG.getCurrentSelected()+1) ;
    }

    private void getInfo() {
        setUrl();

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                BeanBase<BeanPDAcceptance> beans=new Gson().fromJson(response,new TypeToken<BeanBase<BeanPDAcceptance>>(){}.getType());

                String embnbbn = new Gson().toJson(beans);
                Log.d("BEAN",embnbbn);
                if(beans.code.equals("200")){

                    if (beans.data != null){
                        pdAcceptance = beans.data;
                        postRequest();
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

    private void postRequest() {
        acceptanceLV.setVisibility(View.GONE);
        bottomRL.setVisibility(View.GONE);
        complete.setVisibility(View.VISIBLE);
        if (pdAcceptance.projectstagestate.ischeck ==1){
            acceptanceLV.setVisibility(View.VISIBLE);
            bottomRL.setVisibility(View.VISIBLE);
            complete.setVisibility(View.GONE);
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        String request = decimalFormat.format(beanProjectDetails.requestmoney[pDAF_HRG.getCurrentSelected()]);
        amountRequestedValue.setText(request);

        String dafaq = parseTimeToDate(pdAcceptance.projectstagestate.starttime);

        acceptanceDateTV.setText(dafaq);
        String status= "Pending Payment";
        if (pdAcceptance.projectstagestate.ispay <1){
            status = "Pending Eval";
        }
        acceptanceStatusTV.setText(status);

        acceptanceLV.setAdapter(new AcceptenceAdapter(this.getContext(), pdAcceptance));

    }


    @Override
    public void onChangeButton() {
        if(currentSelected != pDAF_HRG.getCurrentSelected()){
            currentSelected = pDAF_HRG.getCurrentSelected();
            getInfo();
        }
    }


}
